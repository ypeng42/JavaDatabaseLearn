package com.yuqing.jdbcLearn;

import java.sql.Connection;

public class Runner {
	public static void main(String[] args) throws Exception {
//		JdbcExamples.transactionRollback();

		// Great summary -- https://stackoverflow.com/questions/4034976/difference-between-read-commit-and-repeatable-read

		// don't allow dirty read, this will cause deadlock
//		JdbcExamples.testDirtyRead(Connection.TRANSACTION_READ_COMMITTED, false);

		// dirty read
//		JdbcExamples.testDirtyRead(Connection.TRANSACTION_READ_UNCOMMITTED);

		// repeatable read is higher than dirty read
		//non-repeatable read
//		JdbcExamples.testRepeatableRead(Connection.TRANSACTION_READ_COMMITTED);

		//repeatable read (this will result in deadlock, can't update once it's read by another transaction)
//		JdbcExamples.testRepeatableRead(Connection.TRANSACTION_REPEATABLE_READ);

		//phantom read
//		JdbcExamples.testPhantomRead(Connection.TRANSACTION_REPEATABLE_READ);

		// this will result in deadlock! can't insert, it's locked
		JdbcExamples.testPhantomRead(Connection.TRANSACTION_SERIALIZABLE);
	}
}
