import componentscan.ComponentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("componentscan") //explicitly loads the components from the specified package
public class ComponentScanApplicationCall {
    private static Logger LOGGER = LoggerFactory.getLogger(ComponentScanApplicationCall.class);
    public static void main(String args[]){
        ApplicationContext applicationContext = SpringApplication.run(ComponentScanApplicationCall.class, args);

        ComponentDAO componentDAO = applicationContext.getBean(ComponentDAO.class);
        ComponentDAO componentDAO1 = applicationContext.getBean(ComponentDAO.class);

        //check if both beans are the same
        System.out.println(componentDAO);
        System.out.println(componentDAO1);
        LOGGER.info("{}", componentDAO);
        LOGGER.info("{}", componentDAO.getComponentJDBCConnection());
        LOGGER.info("{}", componentDAO1);
        LOGGER.info("{}", componentDAO1.getComponentJDBCConnection());

        SpringApplication.run(ComponentScanApplicationCall.class, args);
    }

}
