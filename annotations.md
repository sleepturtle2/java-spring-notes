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


Component Annotations 

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
