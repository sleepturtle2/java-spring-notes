What are Java Annotations? 
- Special labels/markers added to Java classes
- Provide meta-data about the class 
- Processed at compile time or run-time for special processing 

Why Spring Configuration with Annotations? 
- XML configuration can be verbose 
- Configure your Spring beans with Annotations 
- Annotations minimizes the XML configuration 

Scanning for Component Classes: 
- Spring will scan your Java classes for special annotations
- Automatically register the beans in the Spring container 

### Development Process 
1. Enable component scanning in Spring config file 
- Instead of individually listing all the beans in the config file, mention the base package where you want to do a component scan in recursively. 
```
<beans ...>
    <context:component-scan base-package="com.springdemo"/>
</beans>
```
2. Add the @Component Annotation to your Java classes 
```
@Component("tennisCoachBeanID") //this is the bean ID 
public class TennisCoach implements Coach{

}
```
3. Retrieve bean from Spring container 
- Coach theCoach = context.getBean("tennisCoachBeanID", Coach.class); 

#### Default Bean IDs 
Spring also supports Default Bean IDs. Default bean id : the class name, make the first letter lower-case. eg class name = TennisCoach, default bean id = tennisCoach 
Example: 
```
@Component 
public class TennisCoach implements Coach{

}

//get the bean from spring container 
Coach theCoach = context.getBean("tennisCoach", Coach.class)
```
## Component Annotations 

-@Component - Generic Component 
-@Repository - encapsulating storage, retrieval, and search behavior typically from a relational database 
-@Service - Business Service Facade 
-@Controller - Controller in MVC pattern

Except @Component, all others are specific to the layer. @Controller - UI 
layer, @Service - Business Service layer, @Repository - gets data from 
the data layer 

@Service can be replaced with @Component too. So why should we use 
specific annotations. We can identify the annotations and add 
functionality to them. We can categorise our components and apply 
different logic to them. 

## Scope Annotation 
we can explicitly specify the bean scopes (default is singleton). 
```
@Component 
@Scope("singleton")
public class TennisCoach implements Coach{
    ...
}
```
prototype scope: new object for each new request : 
```
@Component 
@Scope("prototype")
```

## Bean Lifecycle methods/Hooks with Annotations 
You can add custom code during bean initialization. Calling custom business logic methods. Setting up handles to resources(db, sockets, file etc). Same for destruction also. 

Development Process: 
1. Define your methods for init and destroy
2. Add annotations: @PostConstruct and @PreDestroy 
Code will execute after constructor and after injection of dependencies. 
```
@Component 
public class TennisCoach implements Coach {
    @PostConstruct
    public void doMyStartupStuff(){ ... }
}
```
Code will execute before bean is destroyed. 
```
@Component 
public class TennisCoach implements Coach {
    @PreDestroy
    public void doMyStartupStuff(){ ... }
}
```

## @Configuration Annotation 
3 ways of configuring the spring container. 
- First one is the Full XML Config, where you'll list each bean in the XML file. 
- Second approach is the XML Component Scan, where we make use of annotations and we scan and look for classes with @Component. 
- Third is the Java Configuration class, where we write Java source code to configure the container. 

Development Process for Java Configuration class: 
1. Create a Java class and annotate as @Configuration
2. Add component-scanning support : @ComponentScan (optional)
```
@Configuration
@ComponentScan("com.springdemo") //put the package to scan here 
public class SportConfig{

}
```
3. Read Spring Java configuration class
```
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class); 
```
4. Retrieve bean from Spring container 
```
Coach theCoach = context.getBean("tennisCoach", Coach.class); 
```

## Defining Beans with Java Code (no XML)
Development Process: 
1. Define method to expose bean
Each bean is defined individually in this config class. No component scan
```
@Configuration
public class SportConfig{
    @Bean
    public Coach swimCoach() //method name is used as a Java bean ID 
    {
        SwimCoach mySwimCoach = new SwimCoach(); 
        return mySwimCoach; 
    }
}
```
2. Inject bean dependencies
```
@Configuration
public class SportConfig{
    @Bean
    public FortuneService happyFortuneService(){
        return new HappyFortuneService(); 
    }

    @Bean
    public Coach swimCoach() //method name is used as a Java bean ID 
    {
        SwimCoach mySwimCoach = new SwimCoach(happyFortuneService); 
        return mySwimCoach; 
    }
}
```
3. Read Spring Java configuration class 
```
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class)
```
4. Retrieve bean from Spring container 
```
Coach theCoach = context.getBean("swimCoach", Coach.class); 
```


## Injecting Values from Properties File: 
Development Process : 
1. Create Properties File. 
File sport.properties : 
```
foo.email=email@mail.com
foo.team=RandomTeam
```
2. Load Properties file in Spring config 
File : SportConfig.java
```
@Configuration
@PropertySource("classpath:sport.properties")
public class SportConfig{

}
```
3. Reference values from Properties File 
File : SwimCoach.java
```
public class SwimCoach implements Coach{
    @Value("${foo.email}")
    private String email; 

    @Value("${foo.team}")
    private String team; 
    ...
}