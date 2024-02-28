package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	ProductRepository productRepository;

	EmailSender emailSender;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Autowired
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}
	
	public void addProduct(String name, String description, String email) {
		Product product = new Product(name, description, email);
		productRepository.save(product);
		emailSender.sendEmail(email, "Product: " + name + " is a new product");
	}
		
		
}
