package cdi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CdiApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(CdiApplication.class);
    public static void main(String args[]){
        ApplicationContext applicationContext = SpringApplication.run(CdiApplication.class, args);

        CdiExample cdiExample = applicationContext.getBean(CdiExample.class);


        //check if both beans are the same
        System.out.println(cdiExample);

        LOGGER.info("{}", cdiExample);
        LOGGER.info("{}", cdiExample.getCdiDao());


        SpringApplication.run(CdiApplication.class, args);
    }

}
