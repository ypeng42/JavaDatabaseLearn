package com.learn.jdbc;

import com.learn.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExamples {

	public static void testPhantomRead(int isoLevel) throws SQLException {
		try (Connection conWriter = DBUtils.getConnection(false, isoLevel);
			Connection conReader = DBUtils.getConnection(false, isoLevel)) {
			DBUtils.runSql(conWriter, "insert into SavedData(textData, id) values('data1', 1)");
			conWriter.commit();

			DBUtils.printAllRecords(conReader, "----------- Data before update ------------");

			DBUtils.runSql(conWriter, "insert into SavedData(textData, id) values('data2', 2)");
			conWriter.commit();

			DBUtils.printAllRecords(conReader, "----------- Data after update ------------");
		}
		System.out.println("Done!");
	}

	// read committed/uncommitted allow non-repeatable read, the other 2 don't allow
	//TODO what if iso level is different?? 1 commit 1 uncommit
	public static void testRepeatableRead(int isoLevel) throws SQLException {
		try (Connection conWriter = DBUtils.getConnection(false, isoLevel);
			Connection conReader = DBUtils.getConnection(false, isoLevel)) {
			DBUtils.runSql(conWriter, "insert into SavedData(textData, id) values('data1', 1)");
			conWriter.commit();

			System.out.println("----------- Data before update ------------");
			DBUtils.printRecord(conReader, "select textData from SavedData where id = 1", null);

			DBUtils.runSql(conWriter, "update SavedData set textData = 'data2' where id = 1");
			conWriter.commit();

			System.out.println("----------- Data after update ------------");
			DBUtils.printRecord(conReader, "select textData from SavedData where id = 1", null);
		}
		System.out.println("Done!");
	}

	/**
	 * TODO put this explaination on top? refactor code
	 * Writer's lock doesn't matter!
	 *
	 * Iso level only affects Shared Lock which is reader's lock.
	 *
	 * Writer's lock (X lock) will not be affected by iso level.
	 *
	 * For example, in dirty read's case, since S lock will be acquired (even though release immediately), the X S lock
	 *
	 * incompatibility will cause lock request to wait. (If the mode of the requested lock is not compatible with the existing lock,
	 *  the transaction requesting the new lock waits for the existing lock to be released or for the lock timeout interval to expire)
	 */
	public static void testDirtyRead(int isoLevel, boolean deadLock) throws SQLException {
		try (Connection conWriter = DBUtils.getConnection(false, Connection.TRANSACTION_READ_UNCOMMITTED);
			Connection conReader = DBUtils.getConnection(false, isoLevel)) {
			DBUtils.runSql(conWriter, "insert into SavedData(textData, id) values('data1', 1)");
			DBUtils.runSql(conWriter, "insert into SavedData(textData, id) values('data2', 2)");
			conWriter.commit();

			DBUtils.runSql(conWriter, "update SavedData set textData = 'x' where id = 2");

			if (deadLock) {
				DBUtils.printRecord(conReader, "select textData from SavedData", "Read dirty data");
			} else {
				DBUtils.printRecord(conReader, "select textData from SavedData where id = 1", "Read committed data");
			}
		}
		System.out.println("Done!");
	}

	// This will cause a deadlock! since iso level is TRANSACTION_READ_COMMITTED, there will be a lock on the table when we insert!
	public static void readCommittedDeadLock() throws SQLException {
		try (Connection conWriter = DBUtils.getConnection(false);
			Connection conReader = DBUtils.getConnection(false)) {
			DBUtils.runSql(conWriter, "insert into SavedData(textData) values('data1')");
			DBUtils.printAllRecords(conReader, "----------- Data after insert ------------");

			conWriter.commit();
			DBUtils.printAllRecords(conReader, "----------- Data after commit ------------");
		}
		System.out.println("Done!");
	}

	public static void transactionRollback() throws SQLException {
		Connection conn = DBUtils.getConnection();
		try {
			conn.setAutoCommit(false);
//			conn.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);

			DBUtils.runSql(conn, "insert into SavedData(textData) values('works')");
			DBUtils.runSql(conn, "insert into SavedData(textData) values('not works')");
			conn.commit();
		} catch (SQLException se) {
			DBUtils.printAllRecords(conn, "----------- Data after exception, before roll back ------------");

			// Note: if debug here, and iso level is TRANSACTION_READ_COMMITTED, in UI it will show no record (not committed)
			conn.rollback();

			DBUtils.printAllRecords(conn, "----------- Data after roll back ------------");
		} finally {
			conn.close();
			System.out.println("Done!");
		}
	}

	// set it up use the most simple way, then do a simple read
	public static void helloWorld() throws ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=myTest", "test", "123");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT textData FROM SavedData")) {

			while (rs.next()) {
				System.out.print("Name: " + rs.getString("textData"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println("Done");
	}
}
