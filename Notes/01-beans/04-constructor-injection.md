## Development Process - Constructor Injection 
1. Define the dependency interface and class 
2. Create a constructor in your class for injections 
3. Configure the dependency injection in Spring config file

Read implementation online. 
Example code : 

1. Define an interface: 
interfaceA.java 
```
package com.springdemo; 

public interface interfaceA{
    public String method1(); 
}
```

classB.java: 
```
package com.springdemo; 
public class classB implements interfaceA{
    @Override
    public String method1(){
        return "Hello from classB"; 
    }
}
```

interfaceC.java: //Notice how it does not implement interfaceA
```
package com.springdemo; 

public interface interfaceC{
    public String method2(); //defined here 
    public String method1(); //defined in interfaceA 
}
```

Step 2 : Create a constructor in your class for injections 
classD.java: 
```
package com.springdemo; 

public class classD implements interfaceC{
    //define a private field for the dependency
    private interfaceA interfaceA_member; 

    //define a constructor for dependency injection 
    public classD(interfaceA interfaceA_member){
        this.interfaceA_member = interfaceA_member; 
    }

    @Override 
    public String method1(){
        return interfaceA_member.method1(); 
    }

}
```

Step 3: Configure the dependency injection in Spring config file 

<beans ...>
    <!-- define the dependency-->
    <bean id="interfaceAId"
        class="com.springdemo.classB">
    </bean>
    <bean id="classDId"
        class="com.springdemo.classD" >
        <!-- set up the constructor injection here -->
        <constructor-arg ref="interfaceAId" />
    </bean>
</beans>

main class: 

```
package com.springdemo; 
import org.springframework.context.support.ClassPathXmlApplicationContext; 

public class Application{
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
        
        //retrieve bean from spring container 
        interfaceC interfaceC_Obj = context.getBean("classDId", interfaceC.class); 

        //call methods on the bean
        System.out.println(interfaceC_Obj.method1()); 

        //close the context 
        context.close(); 
    }
}
```