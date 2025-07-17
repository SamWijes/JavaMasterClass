package dev.jdbcChallenges;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Order {
	public static void main(String[] args) {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("storefront");

		try {
			dataSource.setContinueBatchOnError(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}


		try (Connection conn = dataSource.getConnection(System.getenv("MYSQL_USER"),
				System.getenv("MYSQL_PASS")))
		{
			conn.setAutoCommit(false);
			String queryAlt = """
					  ALTER TABLE order_details
					  ADD COLUMN quantity INT;
					""";
			PreparedStatement ps= conn.prepareStatement(queryAlt);
//			int changes=ps.executeUpdate();
//			System.out.println(changes +"changes made");
			addOrderFromFile(conn);


		} catch (SQLException e) {

			throw new RuntimeException(e);
		}


	}

	private static void addOrderFromFile(Connection connection) throws SQLException {
		List<String> orderRec=null;

		try {
			orderRec = Files.readAllLines(Path.of("Orders.csv"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		int orderId=-1;
		String lastOder=null;
		String insertOrder="INSERT INTO storefront.order (order_date) VALUES (?)";
		String insertOrderDetail="INSERT INTO storefront.order_details(item_description,order_id,quantity) VALUES (? , ?, ?)"; //item des,orderid,quantity

		try(PreparedStatement psOrder=connection.prepareStatement(insertOrder,Statement.RETURN_GENERATED_KEYS);
			PreparedStatement psOrderDet=connection.prepareStatement(insertOrderDetail,Statement.RETURN_GENERATED_KEYS) )
		{
			connection.setAutoCommit(false);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			for (String order : orderRec) {
				String [] lastLine= order.split(",");
				if(lastLine[0].equals("order")){
					var dateTime= LocalDateTime.parse(lastLine[1].trim(),formatter);
					psOrder.setTimestamp(1,Timestamp.valueOf(dateTime));
					psOrder.execute();
					var rs=psOrder.getGeneratedKeys();
					rs.next();
					orderId=rs.getInt(1);

				} else if (lastLine[0].equals("item")) {
					psOrderDet.setString(1,lastLine[2]);
					psOrderDet.setInt(2,orderId);
					psOrderDet.setInt(3,Integer.parseInt(lastLine[1].trim()));
					psOrderDet.execute();

				}

			}
			connection.commit();

		} catch (SQLException e) {
			connection.rollback();
			throw new RuntimeException(e);
		}finally {
			connection.setAutoCommit(true);
		}



	}
}
