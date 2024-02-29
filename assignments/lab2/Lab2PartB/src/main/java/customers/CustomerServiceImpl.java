package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private EmailSender emailSender;

	@Autowired
	public void setCustomerDAO(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	@Autowired
	public void setEmailSender(EmailSender emailSender){
		this.emailSender = emailSender;
	}

	public void addCustomer(String name, String email, String street,
			String city, String zip) {
		Customer customer = new Customer(name, email);
		Address address = new Address(street, city, zip);
		customer.setAddress(address);
		customerRepository.save(customer);
		emailSender.sendEmail(email, "Welcome " + name + " as a new customer");
	}
}
