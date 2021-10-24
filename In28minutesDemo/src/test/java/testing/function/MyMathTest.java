package testing.function;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyMathTest {

    //will be run before every test
    @Before
    public void before(){
        System.out.println("Before");
    }
    //run after every test
    @After
    public void after(){
        System.out.println("After");
    }

    //class level methods, hence always static
    @BeforeClass
    public static void beforeClass(){
        System.out.println("Run before class");
    }
    //class level method, hence always static
    @AfterClass
    public static void afterClass(){
        System.out.println("Run after class");
    }
    //MyMath method sum
    //1, 2, 3 => 6
    @Test
    public void sum_with_3_numbers(){
        MyMath myMath = new MyMath();
        int result = myMath.sum(new int[]{1,2,3});

        assertEquals(6, result);
    }

    @Test
    public void assertMethods(){
        assertEquals(1,1);
        assertTrue(true);
        assertFalse(false);
        int a[] = {1,2,3};
        assertArrayEquals(new int[]{1,2,3}, a);
    }
    @Test
    public void test(){
        //fail("Not yet implemented");
    }
}
