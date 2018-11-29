package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
								
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save java object
			
			//start a transaction
			session.beginTransaction();
			
			//query student
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			
			//display the students
			displayStudents(theStudents);
			
			//query student: lastName='Paul'
			theStudents = session.createQuery("from Student s where s.lastName='Paul'").getResultList();
			
			//display the students
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			//query students: lastName='Doe' OR firstName='Daffy'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
			displayStudents(theStudents);
			
			//query students where email LIKE '@bb.com'
			theStudents = session.createQuery("from Student s where" +
					" s.email LIKE '%bb.com'").getResultList();
			System.out.println("\n\nStudents who have email ends with bb.com");
			displayStudents(theStudents);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}
}
