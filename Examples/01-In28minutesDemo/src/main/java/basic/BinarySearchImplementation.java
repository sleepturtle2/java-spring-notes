package basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BinarySearchImplementation {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    @Qualifier("quick")
    private SortAlgorithm sortAlgorithm;

    public BinarySearchImplementation(SortAlgorithm sortAlgorithm)
    {
        super();
        this.sortAlgorithm = sortAlgorithm;
    }
    private int binSearchIndex(int a[], int el)
    {
        //guaranteed to be sorted
        int l = 0, r=a.length - 1;
        while(l<=r)
        {
            int m = l + (r-l)/2;
            if(a[m] == el)
                return m;
            if(a[m] < el)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }


    int binarySearch(int a[], int element)
    {
        //sort array

        int sorted_array[] = sortAlgorithm.sort(a);
        //print array
        for(int el : sorted_array)
            System.out.print(el + " ");
        System.out.println();

        return binSearchIndex(sorted_array, element);
    }

    @PostConstruct
    public void postConstruct(){
        logger.info("postConstruct called");
    }

    @PreDestroy
    public void preDestroy(){
        logger.info("preDestroy called");
    }
}
