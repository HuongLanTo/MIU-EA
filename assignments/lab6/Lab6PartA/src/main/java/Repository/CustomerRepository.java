package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.CD;
import domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("select c from Customer c")
	List<Customer> findAllCustomers();
	
	List<Customer> findByCountry(String country);
	
	@Query("select c from Customer c where c.address.city = :city")
	List<Customer> findCustomersByCity(@Param("city") String city);
}
