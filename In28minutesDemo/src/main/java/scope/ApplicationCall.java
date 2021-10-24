package scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApplicationCall {
    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationCall.class);
    public static void main(String args[]){
        ApplicationContext applicationContext = SpringApplication.run(ApplicationCall.class, args);

        PersonDAO personDAO = applicationContext.getBean(PersonDAO.class);
        PersonDAO personDAO1 = applicationContext.getBean(PersonDAO.class);

        //check if both beans are the same
        System.out.println(personDAO);
        System.out.println(personDAO1);
        LOGGER.info("{}", personDAO);
        LOGGER.info("{}", personDAO.getJdbcConnection());
        LOGGER.info("{}", personDAO1);
        LOGGER.info("{}", personDAO1.getJdbcConnection());

        SpringApplication.run(ApplicationCall.class, args);
    }

}
