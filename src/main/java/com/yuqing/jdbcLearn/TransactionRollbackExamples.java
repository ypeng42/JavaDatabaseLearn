package com.yuqing.jdbcLearn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;

public class TransactionRollbackExamples {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;database=myTest";

	//  Database credentials
	private static final String USER = "test";
	private static final String PASS = "123";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);

		//	If the connection closes without completing commit() it will be rolled back
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		PreparedStatement insertOne = null;
		PreparedStatement insertTwo = null;

		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
			insertOne = conn.prepareStatement("insert into SavedData(textData) values(?)");
			insertTwo = conn.prepareStatement("insert into SavedData(intData) values(?)");

			insertTwo.setInt(1, 3);
			insertTwo.execute();
//			conn.commit();

			insertOne.setString(1, "ajkshdajdshjkashd");
			insertOne.execute();
			conn.commit();

		} catch (SQLException se) {
			System.out.println("before roll back, go to db and check");
			// when isolation level is set to read_committed, rollback doesn't matter too much, but when it's set to TRANSACTION_READ_UNCOMMITTED, it's
			// necessary!
			conn.rollback();
			System.out.println("error");
		} finally {
			insertOne.close();
			insertTwo.close();
			conn.close();
		}
		System.out.println("Done!");
	}
}
