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
