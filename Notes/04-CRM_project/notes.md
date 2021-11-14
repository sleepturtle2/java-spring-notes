## CRM App (Customer Relationship Management)
Features: 

- Set up database dev environment 
- List Customers 
- Add a new Customer 
- Update a Customer 
- Delete a Customer 

(01-create-user.sql userId: springstudent, password: springstudent)

Basic Dev Setup: 
configure web.xml and spring config, copy jstl libs, spring jars, hibernate jars

Configuration for Spring+Hibernate: 
1. Define database dataSource / connection pool 
2. Setup Hibernate session factory
3. Setup Hibernate transaction manager 
4. Enable configuration of transactional annotations

Our project will contain a Customer Data Access Object. This is responsible for interfacing with the database. This is a common design pattern: Data Access Object(DAO). It is like a helper class or a utility class for talking to the database. Our DAO will make use of the Hibernate API for accessing the data and sending it back, here the CustomerController. For Hibernate, our DAO needs a Hibernate SessionFactory. The SessionFactory needs a Data Source. The data source defines database connection info. 

Roadmap for List Customers: 
1. Create Customer.java
2. Create CustomerDAO.java and CustomerDAOImpl.java. Inject the SessionFactory
```
public interface CustomerDAO{
    public List<Customer> getCustomers(); 
}
```
```
@Repository
public class CustomerDAOImp implements CustomerDAO {
    @Autowired
    private SessionFactory sessionFactory; //will refer the bean in the xml file

    @Transactional
    public List<Customer> getCustomers(){

    }
}
```
3. Create CustomerController.java
4. Create JSP page: list-customers.jsp

Note: Spring provides a special annotation called @Transactional annotation. Automatically begin and end a transaction for your Hibernate code. No need to  do it explicitly in your code
Earlier: 
```
//start transaction 
session.beginTransaction(); 

//Do Hibernate stuff here 

//commit transaction 
session.getTransaction().commit(); 
```
Use of Transactional: 
```
@Transactional
public List<Customer> getCustomers(){
    //get the current hibernate session 
    Session currentSession = sessionFactory.getCurrentSession(); 

    //create a query 
    Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class); 

    //get the result list 
    List<Customer> customers = theQuery.getResultList(); 

    return customers; 
}
```

## Specialized Annotation for DAOs
Spring provides the @Repository annotation (inherits from @Component). 
- Applied to DAO implementations 
- Spring will automatically register the DAO implementation, thanks to component scanning 
- Spring also provides translation of any JDBC related exceptions 
