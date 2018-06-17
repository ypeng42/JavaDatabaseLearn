package com.yuqing.jdbcLearn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcHelloWorld_Simple_Read {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;database=myTest";

	//  Database credentials
	private static final String USER = "test";
	private static final String PASS = "123";

	public static void main(String[] args) throws ClassNotFoundException {
		// load jdbc driver
		// when the class is loaded, the static method of SQLServerDriver will be called:
		// java.sql.DriverManager.registerDriver(new SQLServerDriver());
		// so later when we call DriverManager.getConnection(), we get driver there
		Class.forName(JDBC_DRIVER);

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name, person_id FROM people")) {

			while (rs.next()) {
				//Retrieve by column name
				String name = rs.getString("name");
				int id = rs.getInt("person_id");

				System.out.print("Name: " + name + ", person_id: " + id);
			}

			/*
			 * Read this about commit and rollback:
			 * https://stackoverflow.com/questions/15031866/jdbc-is-con-rollback-has-effect-only-ifcon-commit-not-succeeded
			 *
			 * This one about auto commit:
			 * https://stackoverflow.com/questions/10457335/commit-or-conn-setautocommittrue
			 *
			 * about transaction:
			 * https://stackoverflow.com/questions/4940648/how-to-start-a-transaction-in-jdbc/4940691
			 *
			 * isolation level:
			 * https://en.wikipedia.org/wiki/Isolation_(database_systems)#Phantom_reads
			 *
			 */
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println("\nGoodbye!");
	}
}
