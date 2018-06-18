package com.yuqing.jdbc;

import com.yuqing.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class SimpleWrite {
	public static void main(String[] args) {
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
