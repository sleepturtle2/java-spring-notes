import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xml.XMLPersonDao;


public class XmlContextApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(XmlContextApplication.class);
    public static void main(String args[])
    {
        try(ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml")){

            XMLPersonDao personDao = applicationContext.getBean(XMLPersonDao.class);
            LOGGER.info("Beans loaded -> {} {}",
                    (Object)applicationContext.getBeanDefinitionNames(), applicationContext.getBeanDefinitionCount());
            System.out.println(personDao.getXmlJdbcConnection());
        }
    }
}