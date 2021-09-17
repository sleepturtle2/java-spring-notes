package scope;

import org.springframework.stereotype.Component;

@Component
public class JDBCConnection {
    public JDBCConnection(){
        System.out.println("JDBC constructor");
    }
}
