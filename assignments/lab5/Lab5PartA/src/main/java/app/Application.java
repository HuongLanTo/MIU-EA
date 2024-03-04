package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Repository.CustomerRepository;
import domain.Book;
import domain.CD;
import domain.Customer;
import domain.DVD;
import domain.Order;
import domain.OrderLine;
import domain.Product;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	@Autowired
	CustomerRepository customerRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CD cd = new CD();
		cd.setName("Hibernate 3");
		cd.setDescription("Good book on Hibernate");
		cd.setPrice(35.50);
		cd.setArtist("Artist1");
		OrderLine ol1 = new OrderLine(2, cd);

		DVD dvd = new DVD();
		dvd.setName("The best of Queen");
		dvd.setDescription("Album from 1995");
		dvd.setPrice(12.98);
		dvd.setGenre("Genre1");
		OrderLine ol2 = new OrderLine(4, dvd);
		
		Book book = new Book();
		book.setName("Harry Porrter");
		book.setDescription("Album from 1990");
		book.setPrice(194.56);
		book.setIsbn("ISBN1");
		OrderLine ol3 = new OrderLine(6, book);

		Order o1 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"New york", "43221");
		c1.addOrder(o1);
		o1.setCustomer(c1);
		
		customerRepository.save(c1);
		for (Customer customer: customerRepository.findAll()) {
			for (Order order: customer.getTheOrders()) {
				printOrder(order);
			}
		}

	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrderNumber());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstName() + " "
				+ cust.getLastName());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}
	}
}
