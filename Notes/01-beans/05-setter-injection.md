## Development Process for Setter and Literal Value Injection 

1. Create setter method(s) in your class for injections 
2. Configure the dependency injection in your Spring config file 

Note: 
Rule for setting the property name in the config file: 
setter method name : setBestAthlete, property name="bestAthlete"
setter method name: setFortuneTeller, property name="fortuneTeller"


Step 1: 
interfaceA.class : 
```
public interface interfaceA{
    public String method1(); 
}
```

interfaceB.class : 
```
public interface interfaceB{
    public String method1(); //defined in interfaceA 
    public String method2(); 
}
```

classC: 
```
public class classC implements interfaceA{

    @Override 
    public String method1(){
        return "Hello from class C"; 
    }
}
```

classD: 
```
public class classD implements interfaceB{
    //private fields for injecting Literal Values 
    private String emailAddress; 
    private String team; 


    private interfaceA interfaceA_member; 
    
    
    
    
    //setter methods for injecting literal values
    
    public void setEmailAddress(String emailAddress){...}

    public void setTeam(String team) {...}

    //getter methods for the same 
    public String getEmailAddress(){
        return emailAddress; 
    }

    public String getTeam(){
        return team; 
    }
    
    //setter method for setter injection 
    public void setMethod1(interfaceA interfaceA_member){
        this.interfaceA_member = interfaceA_member; 
    }

    @Override
    public String method1(){
        return interfaceA_member.method1(); 
    }
}
```


applicationContext.xml configuration: 
```
<!-- define the dependency-->
<bean id="classC_Id"
    class="com.springdemo.classC">
</bean>
<bean id="classD"
    class="com.springdemo.classD">

    <!-- set up setter injection-->
    <property name="method1" ref="classC_Id"/>

    <!-- inject literal values -->   
    //based on the name spring will call setEmailAddress
	<property name="emailAddress" value="email@address.com" />
    //spring will call setTeam
    <property name="team" value="RandomTeam" />
</bean>
```

main file: 
```
public static void main(String[] args){
    
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")

    //retrieve bean from spring container 
    interfaceC interfaceC_Obj = context.getBean("classDId", interfaceC.class); 

    //call methods on the bean
    System.out.println(interfaceC_Obj.method1()); 

    //call getter methods to get the literal values 
    System.out.println(interfaceC_Obj.getEmailAddress()); 
    System.out.println(interfaceC_Obj.getTeam()); 

    //close the context 
    context.close(); 
}
```

## Injecting Values from a properties file 

#### Development process 
1. Create Properties File 
2. Load Properties File in Spring config File 
3. Reference Values from Properties File 

Step 1 : (in the src directory)
'sport.properties' file : 

Step 2 : 
Load properties file in Spring config file: 

<context: property-placeholder Location="classpath:sport.properties"/>

Step 3 : 
Referencing values from Properties File 

<property name="emailAddress" value="${foo.email}"/>
<property name="team" value="${foo.team}"/>