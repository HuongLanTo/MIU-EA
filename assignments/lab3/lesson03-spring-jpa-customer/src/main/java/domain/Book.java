package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	
	private String ISBN;
	
	private String author;
	
	private double price;
	
	protected Book() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Book(String title, String isbn, String author, double price) {
		super();
		this.title = title;
		this.ISBN = isbn;
		this.author = author;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return String.format("Book[id=%d, title='%s', ISBN='%s', author='%s', price=%2f]", id, title, ISBN, author, price);
	}
	
}
