package domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Flight {
	@Id
	@GeneratedValue
	private int id;
	private int flightNumber;
	@Column(name="fromlocation")
	private String from;
	@Column(name="tolocation")
	private String to;
	private Date date;
	
	public Flight() {
	}

	public Flight(int flightNumber, String from, String to, Date date) {
		this.flightNumber = flightNumber;
		this.from = from;
		this.to = to;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightNumber=" + flightNumber + ", from=" + from + ", to=" + to + ", date="
				+ date + "]";
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}
	
}
