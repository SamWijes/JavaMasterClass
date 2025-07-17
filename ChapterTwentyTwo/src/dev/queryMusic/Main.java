package dev.queryMusic;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {

		Properties prop = new Properties();
		try {
			prop.load(Files.newInputStream(Path.of("music.properties"), StandardOpenOption.READ));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		String albumName="Tapestry";
//		String query="SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);
//		String query="SELECT * FROM music.albumview WHERE album_name='Tapestry'";
		String query="SELECT * FROM music.artists limit 10";

//		String query = """
//            WITH RankedRows AS (
//                                SELECT *,
//                                ROW_NUMBER() OVER (ORDER BY artist_id) AS row_num
//                                FROM music.artists
//                            )
//                            SELECT *
//                                FROM RankedRows
//                            WHERE row_num <= 10""";

		var dataSource = new MysqlDataSource();
		dataSource.setServerName(prop.getProperty("serverName"));
		dataSource.setPort(Integer.parseInt(prop.getProperty("port")));
		dataSource.setDatabaseName(prop.getProperty("databaseName"));

		try (var connection = dataSource.getConnection(prop.getProperty("user"),
				System.getenv("MYSQL_PASS"));
			 Statement statement=connection.createStatement();
		) {
			ResultSet resultSet=statement.executeQuery(query);

			var meta = resultSet.getMetaData();
//			for (int i = 1; i <= meta.getColumnCount(); i++) {
//				System.out.printf("%d %s %s %n",
//						i,
//						meta.getColumnName(i),
//						meta.getColumnTypeName(i)
//				);
//			}
			System.out.println("==========================");
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				System.out.printf("%-15s",meta.getColumnName(i).toUpperCase());
			}
			System.out.println();

			while (resultSet.next()){
//				System.out.printf("%d %s %s %n",
//						resultSet.getInt("track_number"),
//						resultSet.getString("artist_name"),
//						resultSet.getString("song_title"));

				for (int i = 1; i <= meta.getColumnCount(); i++) {
					System.out.printf("%-15s",resultSet.getString(i));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}



	}

}
