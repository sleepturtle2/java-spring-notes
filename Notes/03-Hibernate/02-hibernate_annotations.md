### Hibernate Dev Process (Hibernate <-> JDBC <-> Database)
1. Add Hibernate Configuration file 
2. Annotate Java Class
3. Develop Java Code to perform database operations 

Terminology: 
- Entity: Java class that is mapped to a database table (Java class <-> Hibernate <-> Database Table)

Two Options for Mapping: 
1. XML config file
2. Java Annotations (preferred)

### Java Annotations 
1. Map class to database table
2. Map fields to database columns 

```
@Entity 
@Table(name="student")
public class Student {
    @Id 
    @Column(name="id")
    private int id; 

    @Column(name="first_name")
    private String firstName; 
 }
```

## CRUD Hibernate Operations 

## Creating Objects with Hibernate
Performing database operations using Java Objects: 
Class   -   Description
- SessionFactory : Reads the hibernate config file, Creates Session objects, Heavy-weight object, Only create once in your app 
- Session - Wraps a JDBC connection, Main object used to save/retrieve objects, Short-lived object, Retrieved from SessionFactory

Code example: 
```
public static void main(String args[]){
    SessionFactory factory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Student.class)
                            .buildSessionFactory(); 
    Session session = factory.getCurrentSession(); 

    try{
        //This is how you save a Java Object: 

        //create a student object 
			
		Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com"); 
			
		//start a transaction 
		session.beginTransaction(); 
			
		//save the student object 
		System.out.println("Savinf the student..");
		session.save(tempStudent); 
			
		//commit transaction 
		session.getTransaction().commit(); 
			
    }
    finally{
        factory.close(); 
    }
}
```
- Create a SessionFactory once and use the factory object to create multiple sessions. 

## Hibernate and Primary Keys
Primary Key: uniquely identifies each row in a table, cannot contain null values
```
@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
private int id; 
```
ID Generation Strategies: GenerationType._____
- AUTO : Pick an appropriate strategy for the particular database 
- IDENTITY : Assign primary keys using database identity column (most commonly used)
- SEQUENCE : Assign primary keys using a database sequence 
- TABLE : Assign primary keys using an underlying database table to ensure uniqueness

We can generate our own Custom generation strategy: 
- Create implementation of org.hibernate.id.IdentifierGenerator
- Override the method: public Serializable generate(...)

Note: 
1. How to change the Auto Increment Values?
```
alter table hb_student_tracker.student auto_increment=1000
```
2. How to RESET the AUTO_INCREMENT value to 1?
```
truncate hb_student_tracker.student
```

## Reading objects with Hibernate
Retrieve/read from database using primary key : 
```
Student myStudent = session.get(Student.class, theStudent.getId())
```

## Querying Objects with Hibernate
Hibernate has its own query language called Hibernate Query Language(HQL). Example, for retrieving all students. 
```
List<Student> theStudents = session.createQuery("from Student").getResultList();
```
Retrieve students with last name 'Doe': 
```
List<Student> theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy' ").getResultList(); 
```
Using LIKE predicate: 
```
List<Student> theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList(); 
```

## Updating Objects with Hibernate
```
int studentId = 1; 
Student myStudent = session.get(Student.class, studentId); 
//update first name to 'Scooby'
myStudent.setFirstName("Scooby"); 
//commit the transaction
session.getTransaction().commit(); 
```
No need to save or commit because the Student Object is a persistence object.  
Update email for all students: 
```
session.createQuery("update Student set email='foo@gmail.com'").executeUpdate()
```


## Deleting Objects with Hibernate
```
int studentId = 1; 
Student myStudent = session.get(Student.class, studentId); 
//delete the Student
session.delete(myStudent); 
//commit the transaction
session.getTransaction().commit(); 
```
Another way of deleting: 
```
session.createQuery("delete from Student where id=2").executeUpdate(); 
```
