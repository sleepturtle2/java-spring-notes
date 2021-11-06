## What is Hibernate
Hibernate is a framework for persisting or saving Java objects in a database. (Hibernate 5.2 requires Java 8)
## Benefits of Hibernate
- Hibernate handles all of the low-level SQL code 
- Minimizes the amount of JDBC code we have to develop
- Hibernate provides the Object-to-Relational Mapping (ORM)
- Java <-> Hibernate <-> Database Table
## Code Snippets
Save/Retrieve a Java Object with Hibernate : 
```
//create Java Object
Student theStudent = new Student("John", "Doe", "john@luv2code.com"); 

//save it to database
int theId = (Integer) session.save(theStudent); 

//retrieve 
Student myStudent = session.get(Student.class, theId); 
```

Querying for Java Objects: 
```
Query query = session.createQuery("from Student"); 
List<Student> students = query.list(); 
```

## Hibernate and JDBC
Hibernate  uses JDBC for all database communications. Hibernate is another layer of extraction on top of JDBC. When our application uses the Hibernate framework, the app will store and retrieve objects using the Hibernate API. In the background, Hibernate does all of the low level JDBC work, submitting the SQL queries and so on. But it all goes through the standard JDBC API. 

Software Required: 
1. Java IDE
2. Database Server (like SQL workbench)
3. Hibernate JAR files and JDBC Driver

Getting started with Hibernate: (add these to project lib)
1. Hibernate ORM jar files 
2. MySql Java Connector (Connector/J) jar files from http://dev.mysql.com/downloads

Sample Connection Code: 
```
import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&severTimezone=UTC"; 
		String user = "hbstudent"; 
		String pass = "hbstudent"; 
		
		try {
			System.out.println("Connecting to database:  " + jdbcUrl); 
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection successful!"); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

```