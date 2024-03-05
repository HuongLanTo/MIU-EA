package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.CD;
import domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select c from CD c where c.price < :price and c.artist = :artist")
	List<CD> findCDByArtistAndWithPriceSmallerThan(@Param("artist") String artist, @Param("price") double price);
	
	List<CD> findByArtist(String artist);
	
	@Query("select c from CD c where c.price > :price and c.artist = :artist")
	List<CD> findCDByPriceAndArtist( @Param("artist") String artist, @Param("price") double price);
	
	@Query(value = "select c from CD c where c.artist = 'U2'", nativeQuery = true)
	List<CD> findByArtistU2();
}
