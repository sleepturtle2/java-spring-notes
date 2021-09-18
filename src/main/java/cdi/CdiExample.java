package cdi;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CdiExample {

    @Inject
    CdiDao cdiDao;

    public CdiDao getCdiDao() {
        return cdiDao;
    }

    public void setCdiDao(CdiDao cdiDao) {
        this.cdiDao = cdiDao;
    }
}
