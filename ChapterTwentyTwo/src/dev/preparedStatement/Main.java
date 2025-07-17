package dev.preparedStatement;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

public class Main {
	private static String ARTIST_INSERT =
			"INSERT INTO music.artists (artist_name) VALUES (?)";
	private static String ALBUM_INSERT =
			"INSERT INTO music.albums (artist_id, album_name) VALUES (?, ?)";
	private static String SONG_INSERT =
			"INSERT INTO music.songs (album_id, track_number, song_title) " +
					"VALUES (?, ?, ?)";

	public static void main(String[] args) {

		var dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
//		dataSource.setUser("MYSQL_USER");
//		dataSource.setPassword("MYSQL_PASS");
		dataSource.setDatabaseName("music");
		try {
			dataSource.setContinueBatchOnError(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"),
				System.getenv("MYSQL_PASS")))
		{
			addDataFromFile(connection);
			//prepared statement
			String sql = "SELECT * FROM music.albumview WHERE artist_name=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "Bob Dylan");
			var rs = ps.executeQuery();
			printRecords(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static boolean printRecords(ResultSet resultSet) throws SQLException {
		boolean foundData = false;

		var meta = resultSet.getMetaData();
		System.out.println("====================");
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
		}
		System.out.println();
		while (resultSet.next()) {
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				System.out.printf("%-15s", resultSet.getString(i));
			}
			System.out.println();
			foundData = true;
		}

		return foundData;
	}

	private static int addArtist(PreparedStatement ps, Connection conn
			, String artistName) throws SQLException {
		int artistId = -1;
		ps.setString(1, artistName);
		int insertedCount=ps.executeUpdate();
		if (insertedCount > 0) {
			ResultSet generatedKeys=ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				artistId=generatedKeys.getInt(1);
				System.out.println("AUTO_INCREMENT ID: "+artistId);
			}
		}
		return artistId;
	}

	private static int addAlbum(PreparedStatement ps, Connection conn,int artistId
			, String albumName) throws SQLException {
		int albumId = -1;
		ps.setInt(1, artistId);
		ps.setString(2, albumName);
		int insertedCount=ps.executeUpdate();
		if (insertedCount > 0) {
			ResultSet generatedKeys=ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				albumId=generatedKeys.getInt(1);
				System.out.println("AUTO_INCREMENT ID: "+albumId);
			}
		}
		return albumId;
	}

//	private static int addSong(PreparedStatement ps, Connection conn,int albumId
//			, int trackNo,String songTitle) throws SQLException {
////album_id, track_number, song_title)
//
//		int songId=-1;
//		ps.setInt(1, albumId);
//		ps.setInt(2, trackNo);
//		ps.setString(3,songTitle);
//		int insertedCount= ps.executeUpdate();
//		if (insertedCount > 0) {
//			ResultSet result=ps.getGeneratedKeys();
//			if (result.next()) {
//				songId=result.getInt(1);
//				System.out.println("AUTO_INCREMENT ID: "+songId);
//			}
//		}
//		return songId;
//	}

//	batching up
	private static void addSong(PreparedStatement ps, Connection conn,int albumId
			, int trackNo,String songTitle) throws SQLException {
//album_id, track_number, song_title)

		int songId=-1;
		ps.setInt(1, albumId);
		ps.setInt(2, trackNo);
		ps.setString(3,songTitle);
		ps.addBatch();

	}

	private static void addDataFromFile(Connection connection) throws SQLException {
		List<String> records=null;
		try {
			records= Files.readAllLines(Path.of("NewAlbums.csv"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String lastAlbum=null;
		String lastArtist=null;
		int artistId = -1;
		int albumId = -1;

		try (PreparedStatement psArtist = connection.prepareStatement(ARTIST_INSERT, Statement.RETURN_GENERATED_KEYS);
			 PreparedStatement psAlbum = connection.prepareStatement(ALBUM_INSERT, Statement.RETURN_GENERATED_KEYS);
			 PreparedStatement psSong = connection.prepareStatement(SONG_INSERT, Statement.RETURN_GENERATED_KEYS))
		{
			connection.setAutoCommit(false);
			for (String record : records) {
				String[] columns=record.split(",");
				if (lastArtist == null || !lastArtist.equals(columns[0])) {
					lastArtist = columns[0];
					artistId=addArtist(psArtist,connection,lastArtist);
				}
				if (lastAlbum == null || !lastAlbum.equals(columns[1])) {
					lastAlbum = columns[1];
					albumId=addAlbum(psAlbum,connection,artistId,lastAlbum);
				}
				addSong(psSong, connection, albumId, Integer.parseInt(columns[2]), columns[3]);
			}
			//batch execution added below
			int[] inserts=psSong.executeBatch();
			int totalInsets= Arrays.stream(inserts).sum();
			System.out.printf("%d song records added %n",inserts.length);

			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			connection.rollback();
			throw new RuntimeException(e);
		}

	}

}
