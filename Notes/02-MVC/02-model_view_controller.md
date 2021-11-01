## Controller Development Process 
1. Create Controller class 
- Annotate class with @Controller. @Controller inherits from @Component and supports component scanning 

2. Define Controller method 

3. Add Request Mapping to Controller Method 

4. Return view name 
```
@Controller
public class HomeController{ 
    @RequestMapping("/")
    public String showMyPage(){
        return "main-menu"; //this is the view name 
    }
}
```
5. Develop View Page 
//create /WEB-INF/view/main-menu.jsp
```
<html>
<body>
<h2>Spring MVC Demo - Home Page</h2>
</body>
</html>
```

## Model 
- The Model is a container for your application data 
- In your Controller 
    - You can put anything in the model
    - strings, objects, info from database, etc
- Your View page (JSP) can access data from the model

In the Controller, use HttpServletRequest request to store the form-data/request. Our data can be accessed from Model model. 
Example: 
```
@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		//read the request parameter from the HTML form
		String theName = request.getParameter("studentName"); 
				
		//convert the data to all caps 
		theName = theName.toUpperCase(); 
		//create the message 
		String result = "YO" + theName; 
		//add message to the model 
		model.addAttribute("message", result); 
		
		return "helloworld";
	}
```

# @RequestParam Annotation
Instead of using HttpServletRequest , using @RequestParam reads the parameter from the request and binds it to the variable given. 

# @RequestMapping Annotation 
Adding RequestMappings to Controller: 
- Serves as parent mapping for controller 
- All request mappings on methods in the controller are relative 
- Similar to folder directory structures
```
@RequestMapping("/funny")
public class exampleController(){
    @RequestMapping("/showForm") //   /funny/showForm
    public String showForm() {...}

    @RequestMapping("/processForm") // /funny/processForm
    public String process(HttpServletRequest request, Model model) { ... }
}
```