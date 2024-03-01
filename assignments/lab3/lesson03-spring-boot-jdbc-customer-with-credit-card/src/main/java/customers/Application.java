package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		System.out.println(customerRepository.getCustomer(101));
		System.out.println(customerRepository.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.getAllCustomers());
		
		// Product
		System.out.println("-----------Products----------------");
		productRepository.clearDB();
		Product product = new Product(1001, "Laptop", 1000);
		product.setSupplier(new Supplier("Supplier 1", "+14598304830"));
		productRepository.save(product);
		Product product1 = new Product(1002, "Tablet", 1000);
		product1.setSupplier(new Supplier("Supplier 2", "+14548300285"));
		productRepository.save(product1);
		Product product2 = new Product(1003, "Laptop", 1000);
		product2.setSupplier(new Supplier("Supplier 3", "+14083341482"));
		productRepository.save(product2);
		System.out.println("-----------findByProductNumber()----------------");
		System.out.println(productRepository.findByProductNumber(1001));
		System.out.println("-----------getAllProducts()----------------");
		System.out.println(productRepository.getAllProducts());
		System.out.println("-----------findByProductName()----------------");
		System.out.println(productRepository.findByProductName("Laptop"));
		productRepository.removeProduct(1001);
	}

}
