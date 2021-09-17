package basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class In28minutesApplication {

	public static void main(String[] args) {
		//this object can be made using the @Component and then using the SpringContext
		//BinarySearchImplementation binarySearch = new BinarySearchImplementation(new QuickSort());
		//Application Context
		ApplicationContext applicationContext = SpringApplication.run(In28minutesApplication.class, args);
		BinarySearchImplementation binarySearch = applicationContext.getBean(BinarySearchImplementation.class);
		BinarySearchImplementation binarySearch1 = applicationContext.getBean(BinarySearchImplementation.class);

		//print both to verify that they are copies of the same bean. not different
		System.out.println(binarySearch);
		System.out.println(binarySearch1);
		int result = binarySearch.binarySearch(new int[] {12,4,6,1}, 4);
		if(result == -1)
			System.out.println("Element not found");
		else
			System.out.println("Found at index " + result);
		SpringApplication.run(In28minutesApplication.class, args);
	}

}
