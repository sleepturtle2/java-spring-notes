package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// start a transaction
			session.beginTransaction();
			
			//query students 
			System.out.println("All students: \n");
			List<Student> theStudents = session.createQuery("from Student").list(); 
			displayStudents(theStudents); 
			
			//query students : lastName = 'Doe', firstName = 'Daffy'
			System.out.println("\nStudents with last name Doe\n");
			theStudents = session.createQuery("from Student s where s.lastName='Doe' or firstName='Daffy' ").list(); 
			displayStudents(theStudents); 
			
			//query students : email LIKE '%luv2code.com'
			System.out.println("\nStudents with email ending in 'luv2code'\n");
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").list(); 
			displayStudents(theStudents); 
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
	
	public static void displayStudents(List<Student> theStudents)
	{
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}


