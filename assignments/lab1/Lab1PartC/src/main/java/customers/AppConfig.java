package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	public CustomerService customerService() {
		CustomerServiceImpl customerService = new CustomerServiceImpl(); 
		customerService.setCustomerRepository(customerRepository());
		customerService.setEmailSender(emailSender());
		return customerService;
	}
	
	@Bean
	public EmailSender emailSender() {
		return new EmailSenderImpl(logger());
	}
	
	@Bean
	public CustomerRepository customerRepository() {
		return new CustomerRepositoryImpl(logger());
	}
	
	@Bean
	public Logger logger() {
		return new LoggerImpl();
	}
}
