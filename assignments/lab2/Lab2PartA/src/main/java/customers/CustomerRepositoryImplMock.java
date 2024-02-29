package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Profile("test")
@Repository
public class CustomerRepositoryImplMock implements CustomerRepository {
	private Logger logger;
	
	@Autowired
	public CustomerRepositoryImplMock(Logger logger) {
		this.logger=logger;
	}

	public void save(Customer customer) {
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("---Mock:--- CustomerRepository: saving customer "+customer.getName());
		logger.log("---Mock:--- Customer is saved in the DB: "+ customer.getName() );
	}
}
