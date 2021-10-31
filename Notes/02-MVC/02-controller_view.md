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