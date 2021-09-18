package cdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CdiExample {

    @Autowired
    CdiDao cdiDao;

    public CdiDao getCdiDao() {
        return cdiDao;
    }

    public void setCdiDao(CdiDao cdiDao) {
        this.cdiDao = cdiDao;
    }
}
