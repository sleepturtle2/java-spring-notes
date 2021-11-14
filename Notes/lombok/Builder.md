Lombok @Builder Annotation - Builder Pattern

User.java:

```
package com.qa.builder;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    String name;
    int age;
}
```
******

TestUser.java:

```
package com.qa.builder;

public class TestUser {
    public static void main(String args[] args){
        User u1 = User.builder()
                    .name("Tom")
                    .age(25)
                    .build();
        
        User u2 = User.builder().name("Naveen").build(); 

        User u3 = User.builder().build(); 
        
        System.out.println(u1.getName() + " " + u1.getAge()); 
        System.out.println(u2.getName() + " " + u2.getAge()); 
        System.out.printlnt(u3.getName() + " " + u3.build()); ; 
    }
}
```
output : Tom 25
Naveen 0 
null 0 

******


******
******
NOW WITH THE BUILDER ANNOTATION
******
******

Person.java: 
```
package com.qa.builder; 


@Getter
public class Person {
    String name; 
    int age; 
    bool isActive;
    String role; 

    @Builder
    public Person(String name, int age){
        this.name = name; 
        this.age = age; 
    }
}
```

TestUser.java: 
```
package com.qa.builder;

public class TestUser {
    public static void main(String args[] args){
        User u1 = User.builder()
                    .name("Tom")
                    .age(25)
                    .build();
        
        User u2 = User.builder().name("Naveen").build(); 

        User u3 = User.builder().build(); 
        
        System.out.println(u1.getName() + " " + u1.getAge()); 
        System.out.println(u2.getName() + " " + u2.getAge()); 
        System.out.printlnt(u3.getName() + " " + u3.build()); 

        /*
        In this step, under available methods Person.builder() will have default methods for only age and name here, despite there being other class variables ( The Object methods are there cause Person extends Object [ Object is extended by default ] ). 
        */

        //Person class 
        Person p1 = Person.builder().name("Peter").age(30).build(); 
        
        
        System.out.println(p1.getName() + " " + p1.getAge() + p1.getRole() + " " + p1.getIsActive()); 
    }
}
```
output : 
Tom 25 
Naveen 0 
null 0 
Peter 30 null false 


******
******
BuILDER ANNOTATION ON METHOD
******
******

Customer.class: 
```
package com.qa.builder; 

public class Customer{
    String name; 
    int age; 
    boolean isActive; 
    String city; 


    public Customer(String name, int age, boolean isActive, String city){
        this.name = name; 
        this.age = age; 
        this.isActive = isActive; 
        this.city = city; 
    }

    /*
    Here give the parameters you want the instance to have equivalent to the .methods() we chain to the builder() method in Builder annotation
    */
    @Getter
    @Builder
    public static Customer createInstance( String name, int age ){
        return new Customer(name, age, true, "London" ); 
    }
}
```

TestUser.java: 
```
    Customer c1 = Customer.builder().name("Lisa").age(40).build();

    sysout(c1.getName() + " " + c1.getAge() + " " + c1.getCity() + " " + c1.isActive());  
```
output: Lisa 40 London true

********

****
