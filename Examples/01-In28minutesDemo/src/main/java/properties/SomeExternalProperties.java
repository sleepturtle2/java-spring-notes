package properties;

import org.springframework.stereotype.Component;

@Component
public class SomeExternalProperties {
    //get this value from property file
   // @Value("${external.service.url}")
    private String url;

    public String returnServiceUrl(){
        return url;
    }
}
