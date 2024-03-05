package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name="Product.findByArtist", query="select c from CD c where c.artist = :artist")
public class CD extends Product {
	private String artist;
	
	public CD() {	
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}
