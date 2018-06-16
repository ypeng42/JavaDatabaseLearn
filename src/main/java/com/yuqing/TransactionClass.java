package com.yuqing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

public class TransactionClass {
	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired PlatformTransactionManager transactionManager;

	public void insertValue()  {
//		System.out.println("insert data");
//		try(PreparedStatement stmt = con.prepareStatement("insert into people (person_id, name) values (?, ?)"))
//		{
//			stmt.setInt(1, 4);
//			stmt.setString(2, "asjkdhadsjkqhweqkewhkashdkahd");
//			stmt.addBatch();
//			stmt.setInt(1, 5);
//			stmt.setString(2, "tttqqq");
//			stmt.addBatch();
//			stmt.executeBatch();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
		jdbcTemplate.execute("insert into people (person_id, name) values (4, 'asjkdhadsjkqhweqkewhkashdkahd')");
		jdbcTemplate.execute("insert into people (person_id, name) values (5, 'tttqqq')");
		System.out.println("insert is done");
	}

	public void truncateValue() {
//		throw new RuntimeException("asdasd");
		jdbcTemplate.execute("alter table people alter column name varchar(7)");
		System.out.println("truncate data");
//		try (PreparedStatement statement = con.prepareStatement("alter table people alter column name varchar(7)");) {
//			statement.execute();
//		} catch	(SQLException e) {
//			e.printStackTrace();
//		}
	}

	public void demo() throws SQLException {
//		Connection con = jdbcTemplate.getDataSource().getConnection();
//		con.setAutoCommit(true);
		insertValue();
		try {
			truncateValue();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//		con.rollback();
		System.out.println("Finish");
	}

	public void direct() {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);

		transactionTemplate.execute(transactionStatus -> {
			try {
				demo();
			} catch (RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}
	// DB will rollback the whole transaction !
//	SET IMPLICIT_TRANSACTIONS ON
//	insert into people (person_id, name) values (4, 'asjkdhadsjkqhweqkewhkashdkahd'), (5, 'tttqqq')
//	begin try
//		--alter table people alter column name varchar(7)
//
//	insert into people (person_id, name) values ('a', 'asjkdhadsjkqhweqkewhkashdkahd')
//	end try
//	begin catch
//	select 1
//	end catch
//	COMMIT TRANSACTION
}
