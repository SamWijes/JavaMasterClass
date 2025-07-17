package dev.mysqlMusic;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
	private final static String CONN_STRING="jdbc:mysql://localhost:3306/music";

	public static void main(String[] args) {

		String username= JOptionPane.showInputDialog(null,"Enter DB Username");

		JPasswordField pf = new JPasswordField();
		int okCxl=JOptionPane.showConfirmDialog(null,pf,"Enter DB Password",JOptionPane.OK_CANCEL_OPTION);

		final char[] password=(okCxl==JOptionPane.OK_OPTION)?pf.getPassword():null;

		var datasource = new MysqlDataSource();
//		datasource.setURL(CONN_STRING);
		datasource.setServerName("localhost");
		datasource.setPort(3306);
		datasource.setDatabaseName("music");
//		try	(Connection connection= DriverManager.getConnection(CONN_STRING,username,String.valueOf(password))){
//			System.out.println("Success! connected to the DB");

		try (Connection connection=datasource.getConnection(username,String.valueOf(password))){
			System.out.println("Success! connected to the DB");
			Arrays.fill(password,' ');
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
