package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Repository.AddressRepository;
import Repository.CustomerRepository;
import Repository.OrderRepository;
import Repository.ProductRepository;
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
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired 
	AddressRepository addressRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Order o1 = new Order("234743", "12/10/06", "open");
		
		CD cd = new CD();
		cd.setName("Hibernate 3");
		cd.setDescription("Good book on Hibernate");
		cd.setPrice(3.50);
		cd.setArtist("Artist1");
		OrderLine orderLine = new OrderLine(2, cd);
		o1.addOrderLine(orderLine);
		
		CD cd2 = new CD();
		cd2.setName("Hibernate 3");
		cd2.setDescription("Good book on Hibernate");
		cd2.setPrice(35.50);
		cd2.setArtist("Artist2");
		orderLine = new OrderLine(3, cd2);
		o1.addOrderLine(orderLine);
		
		CD cd3 = new CD();
		cd3.setName("Harry Potter");
		cd3.setDescription("Harry Potter");
		cd3.setPrice(9.50);
		cd3.setArtist("Artist3");
		orderLine = new OrderLine(18, cd3);
		o1.addOrderLine(orderLine);

		DVD dvd = new DVD();
		dvd.setName("The best of Queen");
		dvd.setDescription("Album from 1995");
		dvd.setPrice(12.98);
		dvd.setGenre("Genre1");
		orderLine = new OrderLine(4, dvd);
		o1.addOrderLine(orderLine);
		
		Book book = new Book();
		book.setName("Harry Porrter");
		book.setDescription("Album from 1990");
		book.setPrice(194.56);
		book.setIsbn("ISBN1");
		orderLine = new OrderLine(6, book);
		o1.addOrderLine(orderLine);


		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"New york", "43221", "USA");
		c1.addOrder(o1);
		o1.setCustomer(c1);
		
		customerRepository.save(c1);
		
		// Give all customers
//		for (Customer customer: customerRepository.findAllCustomers()) {
//			for (Order order: customer.getTheOrders()) {
//				printOrder(order);
//			}
//		}
		customerRepository.findAllCustomers().forEach(System.out::println);
		// Give all CD’s from U2 with a price smaller than 10 euro
		productRepository.findCDByArtistAndWithPriceSmallerThan("U2", 10).forEach(System.out::println);
		// Give all customers from the USA
		customerRepository.findByCountry("USA").forEach(System.out::println);
		// Give all CD’s from a certain artist
		productRepository.findByArtist("Artist1").forEach(System.out::println);
		// Give the ordernumbers of all orders with status ‘closed’ 
		orderRepository.findOrderNumbersByStatus("closed").forEach(System.out::println);
		// Give the first and lastnames of all customers who live in Amsterdam
		customerRepository.findCustomersByCity("Amsterdam").forEach(c -> System.out.println(c.getFirstName() + " " + c.getLastName()));
		// Give the ordernumbers of all orders from customers who live in a certain city (city is a parameter)
		orderRepository.findOrderNumbersByCity("New york").forEach(System.out::println);
		// Give all CD’s from a certain artist with a price bigger than a certain amount (artist and amount are parameter2)
		productRepository.findCDByPriceAndArtist("Artist2", 5).forEach(System.out::println);
		// Give all addresses in Amsterdam
		addressRepository.findAddressByCity("Amsterdam").forEach(System.out::println);
		// Give all CD’s from U2
		productRepository.findByArtistU2();

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
