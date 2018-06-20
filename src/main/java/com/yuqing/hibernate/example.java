package com.yuqing.hibernate;

import com.yuqing.DBUtils;
import com.yuqing.hibernate.mapping.Student;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//TODO rename this
public class example {

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public void save() {
		Session session = DBUtils.getSession(false);

		Student student = new Student();
		student.setName("yuqing4");

		session.save(student);

		read(); //can't directly call method! transaction annotation won't work this way!

		throw new RuntimeException("asdads");

//		System.out.println("done!");
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public void read() {
		Session session = DBUtils.getSession(false);

		Student s2 = session.get(Student.class, 1l); //TODO id will change!!!
		if (s2 != null) {
			System.out.println(s2.getName());
		} else {
			System.out.println("student doesn't exist!");
		}
	}

}
