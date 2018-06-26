package com.learn.jdbcTemplate;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateUpdate {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate_1");

		jdbcTemplate.execute("insert into people (person_id, name) values (4, 'asjkdhadsjkqhweqkewhkashdkahd')"); //auto-commit is true
		jdbcTemplate.execute("insert into people (person_id, name) values (5, 'tttqqq')"); //auto-commit is true
		jdbcTemplate.execute("alter table people alter column name varchar(7)"); //auto-commit is true

	}
}
