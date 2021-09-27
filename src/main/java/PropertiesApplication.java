import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import properties.SomeExternalProperties;
//import properties.SomeExternalProperties;


//import value from app.properties
@PropertySource("classpath:app.properties")
public class PropertiesApplication {
   // private static Logger LOGGER = LoggerFactory.getLogger(PropertiesApplication.class);
    public static void main(String args[])
    {
        try(AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PropertiesApplication.class)){

             SomeExternalProperties someExternalProperties = applicationContext.getBean(SomeExternalProperties.class);

            System.out.println(someExternalProperties);
        }
    }
}