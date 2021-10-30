
## What is Spring AutoWiring?
- For dependency injection, Spring can use auto wiring. 
- Spring will look for a class that matches the property (matches by type: class or interface)
- Spring will inject it automatically, hence it is autowired 
- Spring will scan @Components and look for a particular implementation, and if found, will inject it 

## Autowiring Injection Types 
- Constructor Injection 
- Setter Injection 
- Field Injection

## Development Process - Constructor Injection 
1. Define the dependency interface and class 
File : FortuneService.java: 
```
public interface FortuneService{
    public String getFortune(); 
}
```
File : HappyFortuneService.java: 
```
@Component 
public class HappyFortuneService implements FortuneService{
    public String getFortune(){
        return "Today is your lucky day!"; 
    }
}
```
2. Create a constructor in your class for injections 
File: TennisCoach.java: 
```
@Component
public class TennisCoach implements Coach{
    private FortuneService fortuneService; 

    public TennisCoach(FortuneService theFortuneService){
        fortuneService = theFortuneService; 
    }
    ...
}
```
3. Configure the dependency injection with @Autowired Annotation 
File: TennisCoach.java: 
```
@Component
public class TennisCoach implements Coach{
    private FortuneService fortuneService; 
    @Autowired //injection
    public TennisCoach(FortuneService theFortuneService){
        fortuneService = theFortuneService; 
    }
    ...
}
```

## Types of Dependency Injection : 
1. Constructor injection : shown above 
2. Setter injection : use the @Autowired annotation on the setter method : 
```
@Autowired 
public void setFortuneService(FortuneService fortuneService){
    this.fortuneService = fortuneService; 
}
```
3. Method Injection (inject dependencies by calling any method of a class): 
```
@Autowired 
public void doSomeCrazyStuff(FortuneService fortuneService){
    this.fortuneService = fortuneService; 
}
```
4. Field Injections 
Inject dependencies by setting field values on your class directly(even private fields, accomplished by using Java Reflection). 
Development Process: 
1. Configure the dependency injection with Autowired Annotation
- Applied directly to the field 
- No need for setter methods 
```
public class TennisCoach implements Coach{
    @Autowired
    private FortuneService fortuneService; 

    public TennisCoach(){}

    //no need for setter methods
    ...
}
```
#### Which injection type should you use? 
Choose a style, and stay consistent in your project. You get the same functionality regardless of your choice. 

## Qualifiers for Dependency Injection 
What if there are multiple implementations of an interface. Which one gets injected during autowiring? 
Error caused : NoUniqueBeanDefinitionException
Solution : Be specific in your annotation! 
```
@Component 
public class TennisCoach implements Coach{
    @Autowired
    @Qualifier("happyFortuneService")
    private FortuneService fortuneService; 
    ...
}
```
