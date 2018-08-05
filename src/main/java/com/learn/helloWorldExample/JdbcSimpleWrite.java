package com.learn.helloWorldExample;

import com.learn.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcSimpleWrite {
	public static void main(String[] args) {
		// getConnectionFromDriver() -> DriverManager.getConnection
		try (Connection conn = DBUtils.getConnection();) {
			conn.setAutoCommit(false);

			DBUtils.runSql(conn, "insert into SavedData values ('123')");

			conn.commit(); // if don't call this, insert is not committed
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println("Done!");
	}
}
