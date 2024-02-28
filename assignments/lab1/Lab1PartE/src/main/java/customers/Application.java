package customers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		CustomerService customerService = context.getBean("customerService",
				CustomerService.class);

		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");

		ProductService productService = context.getBean("productService", ProductService.class);
		
		productService.addProduct("Product A", "Description for product A", "fbrown@acme.com");
	}
}

