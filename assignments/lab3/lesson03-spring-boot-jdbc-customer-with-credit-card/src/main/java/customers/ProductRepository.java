package customers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE FROM product", namedParameters);
        jdbcTemplate.update("DELETE FROM supplier", namedParameters);
    }
	
	public void save(Product product) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("productnumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO product VALUES ( :productnumber, :name, :price)", namedParameters);
        
     // save creditcard
        Map<String,Object> namedParametersSup = new HashMap<String,Object>();
        namedParametersSup.put("productnumber", product.getProductNumber());
        namedParametersSup.put("name", product.getSupplier().getName());
        namedParametersSup.put("phone", product.getSupplier().getPhone());
        jdbcTemplate.update("INSERT INTO supplier VALUES ( :name, :phone, :productnumber)", namedParametersSup);
    }
	
	public Product findByProductNumber(int productNumber) {
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("productNumber", productNumber);
		Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
						+ "productNumber =:productNumber", namedParameters, 
						(rs, rowNum) -> new Product(rs.getInt("productNumber"),
								rs.getString("name"),
								rs.getInt("price")));
		Supplier supplier = getSupplierForProduct(product.getProductNumber());
		product.setSupplier(supplier);
		return product;
	}
	
	public List<Product> getAllProducts() {
		List<Product> products = jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getInt("price")));
		for (Product product: products) {
			Supplier supplier = getSupplierForProduct(product.getProductNumber());
			product.setSupplier(supplier);
		}
        return products;	
	}
	
	public List<Product> findByProductName(String name) {
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("name", name);
		List<Product> products = jdbcTemplate.query("SELECT * FROM product WHERE "
						+ "name =:name", namedParameters,
	                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
	                        rs.getString("name"),
	                        rs.getInt("price")));
		for (Product product: products) {
			Supplier supplier = getSupplierForProduct(product.getProductNumber());
			product.setSupplier(supplier);
		}
        return products;
	}
	
	public void removeProduct(int productNumber) {
		Map<String,Object> namedParameters = new HashMap<String,Object>();
		namedParameters.put("productNumber", productNumber);
        jdbcTemplate.update("DELETE FROM product WHERE productNumber =:productNumber", namedParameters);
	}

	Supplier getSupplierForProduct(int productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Supplier supplier = jdbcTemplate.queryForObject("SELECT * FROM supplier WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Supplier( rs.getString("name"),
                        rs.getString("phone")));

        return supplier;
    }
}
