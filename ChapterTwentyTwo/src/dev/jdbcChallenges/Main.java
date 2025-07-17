package dev.jdbcChallenges;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	private static String USE_SCHEMA="USE Storefront";

	private static int MYSQL_DB_NOT_FOUND=1049;

	public static void main(String[] args) {
		var dataSource=new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setUser(System.getenv("MYSQL_USER"));
		dataSource.setPassword(System.getenv("MYSQL_PASS"));

		try	(Connection connection= dataSource.getConnection()){
			DatabaseMetaData metaData=connection.getMetaData();
			System.out.println(metaData.getSQLStateType());
			if (!checkSchema(connection)){
				System.out.println("storefront schema doesnt exist");
				setUpSchema(connection);
			}else {
				addOrder(connection);
//				deleteOrder(connection,16);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

	private static boolean checkSchema(Connection connection) throws SQLException{
		try(Statement statement=connection.createStatement()){
			statement.execute(USE_SCHEMA);
		} catch (SQLException e) {
		 	e.printStackTrace();
			System.err.println("SQLState: "+e.getSQLState());
			System.err.println("Error Code: "+e.getErrorCode());
			System.err.println("Message: "+e.getMessage());

			if (connection.getMetaData().getDatabaseProductName().equals("MySQL") &&
					e.getErrorCode() == MYSQL_DB_NOT_FOUND){
				return false;
			}else throw e;
		}
		return true;

	}

	private static void setUpSchema(Connection conn) throws SQLException{
		String createSchema="CREATE SCHEMA storefront";
		String createOrder= """
				CREATE TABLE storefront.order (
				order_id int NOT NULL AUTO_INCREMENT,
				order_date DATETIME NOT NULL,
				PRIMARY KEY (order_id)
				)""";

		String createOrderDetails= """
				CREATE TABLE storefront.order_details(
				order_detail_id INT NOT NULL AUTO_INCREMENT,
				item_description TEXT,
				order_id INT DEFAULT NULL,
				PRIMARY KEY(order_detail_id),
				KEY FK_ORDERID(order_id),
				CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
				REFERENCES storefront.order(order_id) ON DELETE CASCADE
				)""";

		try (Statement statement = conn.createStatement()) {
			statement.execute(createSchema);
			if (checkSchema(conn)) {
				statement.execute(createOrder);
				System.out.println("Successfully Created Order");
				statement.execute(createOrderDetails);
				System.out.println("Successfully Created Order Details");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void addOrder(Connection conn) throws SQLException {
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		var dateTime=LocalDateTime.now().format(dtf);
		String item_desc1="Item is a Fruit";
		String item_desc2="Item is a utensil";
		String query="INSERT INTO storefront.order(order_date) VALUES ('%s')".formatted(dateTime);

		try (Statement statement = conn.createStatement()) {
			conn.setAutoCommit(false);
			statement.execute(query,Statement.RETURN_GENERATED_KEYS);
			var rs=statement.getGeneratedKeys();

			int orderId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
			String queryDetail="INSERT INTO storefront.order_details(item_description,order_id) VALUES ('%s',%d),('%s',%d)"
					.formatted(item_desc1,orderId,item_desc2,orderId);
			System.out.println(rs.getInt(1));
			statement.execute(queryDetail);
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}

		conn.setAutoCommit(true);
	}

	private static void deleteOrder(Connection conn,int orderId) throws SQLException {
		String deleteOrder="DELETE FROM storefront.order WHERE order_id=%d".formatted(orderId);
//		String deleteOrderDetail="DELETE FROM storefront.order_details WHERE order_id=%d".formatted(orderId);
		try (Statement statement = conn.createStatement()) {
			conn.setAutoCommit(false);
//			statement.addBatch(deleteOrder);
//			statement.addBatch(deleteOrderDetail);
//			statement.executeBatch();
			statement.execute(deleteOrder);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}


}
