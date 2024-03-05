package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query("select o.orderNumber from Order o where o.status = :status")
	List<String> findOrderNumbersByStatus(@Param("status") String status);
	
	@Query("select o.orderNumber from Order o where o.customer.address.city = :city")
	List<String> findOrderNumbersByCity(@Param("city") String city);
}

