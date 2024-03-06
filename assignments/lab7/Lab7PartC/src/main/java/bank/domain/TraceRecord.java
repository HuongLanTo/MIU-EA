package bank.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TraceRecord {
	@Id
	@GeneratedValue
	private long id;
	
	private LocalDate traceDate;
	
	private LocalTime traceTime;
	
	private String result;
	
	public TraceRecord() {
	}

	public TraceRecord(LocalDate traceDate, LocalTime traceTime, String result) {
		super();
		this.traceDate = traceDate;
		this.traceTime = traceTime;
		this.result = result;
	}

	public LocalTime getTraceTime() {
		return traceTime;
	}

	public void setTraceTime(LocalTime traceTime) {
		this.traceTime = traceTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
