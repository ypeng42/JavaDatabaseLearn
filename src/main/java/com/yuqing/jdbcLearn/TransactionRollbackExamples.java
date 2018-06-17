package com.yuqing.jdbcLearn;

import com.yuqing.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionRollbackExamples {
	public static void main(String[] args) throws SQLException {
		Connection conn = DBUtils.getConnection();
		try {
			conn.setAutoCommit(false);
//			conn.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);

			DBUtils.runSql(conn, "insert into SavedData values('works')");
			DBUtils.runSql(conn, "insert into SavedData values('not works')");
			conn.commit();
		} catch (SQLException se) {
			DBUtils.printAllRecords(conn,"----------- Data after exception, before roll back ------------");

			// Note: if you put a debugger here, and set to TRANSACTION_READ_COMMITTED, in UI it will show there is no records since the
			// current transaction is not committed!! set it to TRANSACTION_READ_UNCOMMITTED to see change
			conn.rollback();

			DBUtils.printAllRecords(conn,"----------- Data after roll back ------------");
		} finally {
			conn.close();
			System.out.println("Done!");
		}
	}
}
