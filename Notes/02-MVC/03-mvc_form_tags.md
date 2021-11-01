## Spring MVC Form Tags 
- Spring MVC Form Tags are the building blocks for a web page 
- Form tags are configurable and reusable for a web page 

## Data Binding 
- Spring MVC Form Tags can make use of data binding 
- Automatically setting/retrieving data from a Java object/bean

## Form Tags list 
- Form tags will generate HTML for us : 
form:form - main form container 
form:input - text field
form:textarea - multi-line text field 
form:checkbox - check box 
form:radiobutton - radio button 
form:select - drop down list 

### Form Tag for Text Field 
Showing Form : In your Spring Controller, 
- Before you show the form, you must add a model attribute
- This is a bean that will hold form data for the data binding
```
@RequestMapping("/showForm")
public String showForm(Model theModel){
    theModel.addAttribute("student", new Student()); 
    return "student-form"; 
}
```
Setting up HTML Form Data Binding: 
```
<form:form action="processForm" modelAttribute="student">
    First name : <form:input path="firstName" /> //when form is loaded, Spring MVC will call: student.getFirstName()
    Last name : <form:input path="lastName"/>
    <input type="submit" value="Submit"/>
</form:form>
```

When form is submitted, it calls the setter methods
Handling form submission in the Controller: 
```
@RequestMapping("/processForm")
public String processForm(@ModelAttribute("student") Student theStudent){
    System.out.println("theStudent: " + theStudent.getLastName()); 
    return "student-confirmation"; 
}
```
Confirmation Page: 
```
<html>
<body>
The student is confirmed: ${student.firstName} ${student.lastName}
</body>
</html>
```


## Spring form:select Tag
```
<form:select path="country">
    <form:option value="Brazil" label="Brazil"/>
    <form:option value="France" label="France"/>
    <form:option value="Germany" label="Germany"/>
    <form:option value="India" label="India"/>
```

## Spring form:radiobutton Tag
```
Java<form:radiobutton path="favoriteLanguage" value="Java"/>
C#<form:radiobutton path="favoriteLanguage" value="C#"/>
PHP<form:radiobutton path="favoriteLanguage" value="PHP"/>
Ruby<form:radiobutton path="favoriteLanguage" value="Ruby"/>
```

## Spring form:checkbox Tag 
```
Linux <form:checkbox path="operatingSystems" value="Linux"/>
Mac OS<form:checkbox path="operatingSystems" value="Mac OS"/>
MS Windows <form:checkbox path="operatingSystems" value="MS Windows"/>
```

# Form Validation: 

Java's Standard Bean Validation API : 
- Java's standard Bean Validation API (JSR-303/309)
- Only a specification...vendor independent...portable, BUT we still need an implementation

Hibernate : 
- Hibernate started as an ORM project
- But in recent years, they have expanded into other areas
- They have a fully compliant JSR-303/309 implementation
    - Not tied to ORM or database work...separate project

Development Process: 
1. Download Validation JAR files from Hibernate website (http://www.hibernate.org/validator)
2. Add JAR files to project 

### Make Certain Fields Required 
Development Process: 
1. Add Validation rule to Customer class
```
public class Customer{
    private String firstName; 

    @NotNull(message="is required") //error message if validation fails 
    @Size(min=1, message="is required")
    private String lastName; 

    //getter/setter methods 
}
```
2. Display error messages on HTML form
File: customer-form.jsp
```
Last name(*) : <form:input path="lastName" />
<form:errors path="lastName" cssClass="error"/>
```
3. Perform validation in the Controller class
- @Valid - Performs validation rules on Customer object 
- BindingResult - Results of validation placed in BindingResult
```
@RequestMapping("/processForm")
public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult){
    if(theBindingResult.hasErrors()){
        return "customer-form"; 
    }
    else{
        return "customer-confirmation"; 
    }
}
```
4. Update confirmation page
```
<body>
    ${customer.firstName} ${customer.lastName}
<body>
```

When performing Spring MVC validation, the location of the BindingResult parameter is very important. In the method signature, the BindingResult parameter must appear immediately after the model attribute. 

If you place it in any other location, Spring MVC validation will not work as desired. In fact, your validation rules will be ignored.

        @RequestMapping("/processForm")
        public String processForm(
                @Valid @ModelAttribute("customer") Customer theCustomer,
                BindingResult theBindingResult) {
            ...            
        }
Here is the relevant section from the Spring Reference Manual

---

Defining @RequestMapping methods

@RequestMapping handler methods have a flexible signature and can choose from a range of supported controller method arguments and return values.
...

The Errors or BindingResult parameters have to follow the model object that is being bound immediately ...

Source: https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-methods

## @InitBinder
- Works as a preprocessor
- It will pre-process each web request to our controller 
- Method annotated with @InitBinder is executed
Example: Using @InitBinder to trim white space from incoming requests 
```
@InitBinder
public void initBinder(WebDataBinder dataBinder){
    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); 
    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor); 
}
```
## @Min and @Max
```
public class Customer {
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private int freePasses; 
    // getter / setter methods
}
```

## Validating Regex Patterns (@Pattern annotation)
```
public class Customer{

    @Pattern(regexp="^[a-zA-Z0-9]{5}", message="only 5 chars/digits")
    private String postalCode; 
}
```

## Giving Custom Error Messages during Form Validation 