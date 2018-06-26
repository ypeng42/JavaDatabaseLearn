package com.learn.hibernate.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {
	protected Long id;
	private String name;
	private Integer age;

	public Student(String name) {
		this.name = name;
	}

	//Each @Entity needs an @Id - this is the primary key in the database
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = Integer.MAX_VALUE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
