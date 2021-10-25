## Development Process 
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
    private interfaceA interfaceA_member; 
    //setter method
    public void setMethod1(interfaceA interfaceA_member){
        this.interfaceA_member = interfaceA_member; 
    }
    @Override
    public String method1(){
        return interfaceA_member.method1(); 
    }
}
```