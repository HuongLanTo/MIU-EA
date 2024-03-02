package Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Product;

public interface ProductRepositorry extends JpaRepository<Product, Integer> {

}
