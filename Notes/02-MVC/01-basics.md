### What is Spring MVC 
- Framework for building web applications in Java 
- Based on Model-View-Controller design pattern
- Leverage features of the Core Spring Framework (IoC, DI)

### Benefits 
- Spring way of building web app UIs in Java 
- Leverage a set of reusable UI components 
- Help manage application state for web requests 
- Process form data: validation, conversion etc 
- Flexible configuration for the view layer

## MVC Front Controller 
- Front controller known as DispatcherServlet 
    - Part of the Spring Framework 
    - Already developed by Spring Dev Team 
- We will create 
    - Model objects (M)
    - View templates (V)
    - Controller classes (C)

### Controller 
- Code created by developer 
- Contains business logic
    - Handle the request 
    - Store / retrieve data(db, web service ...)
    - Place data in model
- Send to appropriate view template 

### Model 
- Contains your data 
- Store/retrieve data via backend systems 
    - database, web service, etc 
    - Use Spring bean if you like 
- Place your data in the model 
    - Data can be any Java object/collection

### View Template 
- Spring MVC is flexible
    Supports many view templates 
- Most common is JSP + JSTL 
- Developer creates a page 
    - Displays data 