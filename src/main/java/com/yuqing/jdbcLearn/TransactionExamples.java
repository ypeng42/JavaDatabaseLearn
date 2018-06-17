package com.yuqing.jdbcLearn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExamples {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;database=myTest";

	//  Database credentials
	private static final String USER = "test";
	private static final String PASS = "123";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);

		//	If the connection closes without completing commit() it will be rolled back
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		try (Statement stmt = conn.createStatement()) {

			conn.setAutoCommit(false);
			stmt.execute("insert into SavedData values ('first')");
			System.out.println("inserted first");

			stmt.execute("insert into SavedData values ('second')");
			System.out.println("inserted second");

			stmt.execute("alter table SavedData alter column textData varchar(3)");

		} catch (SQLException se) {
			System.out.println("error");
		} finally {
			conn.close();
		}
		System.out.println("Done!");
	}
}
