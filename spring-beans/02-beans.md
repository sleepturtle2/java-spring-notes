## Configure your Spring Beans 
config file : applicationContext.xml 

//example with id of myCoach, and fully qualified classPath as shown 
<beans ...>
    <bean id = "myCoach"
        class="com.springdemo.BaseballCoach">
    </bean>
</beans>

## Create a Spring Container 
(Spring Container is generically known as ApplicationContext)

ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 

## Retrieve Beans from Spring Container 
//example 
Coach theCoach = context.getBean("myCoach", Coach.class); 
//myCoach is the id of the bean 
//Coach.class is the interface 

example code : 
```
import org.springframework.context.support.ClassPathXmlApplicationContext; 
	// load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
				
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// close the context
		context.close();
```

example applicationContext.xml file : 
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:context="http://www.springframework.org/schema/context"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">
  
  <context:annotation-config/>
  <context:component-scan base-package="com.baeldung.applicationcontext"/>

</beans>
```

## Bean Scopes 
Scope refers to the lifecycle of a bean. Default scope of a bean is a Singleton.
What is a Singleton? 
- Spring Container creates only one instance of the bean, by default. It is cached in memory. All request for the bean will return a SHARED reference to the SAME bean. 

Example: let there be two variable declarations for a bean myCoach : 
Coach theCoach = context.getBean("myCoach", Coach.class); 
and 
Coach alphaCoach = context.getBean("myCoach", Coach.class); 

Both theCoach and alphaCoach will be references to the same bean basically. The object references point to the same area in memory. Thus the singleton bean is a default and the best use case for this is a stateless bean, ie where you dont need to maintain any state. If you need to explicitly specify, then write scope="singleton" in the bean definition. 

Other bean scopes: 
singleton - Create a single shared instance of the bean. Default scope 
prototype - Creates a new bean instance for each container request 
request - Scoped to an HTTP web request. Only used for web apps 
session - Scoped to an HTTP web session. Only used for web apps 
global-session - Scoped to a global HTTP web session. Only used for web apps. 

The prototype scope used for maintaining stateful data. 

## Bean Lifecycle 
Container started -> Bean instantiated -> Dependencies injected -> 
Internal Spring Processing -> Your Custom Init method -> Bean is ready for use -> 
Container is shut down 