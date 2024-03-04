package domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Appointment {
	@Id
	@GeneratedValue
	private int id;

	private String appDate;

	@Embedded
	private Patient patient;

	@Embedded
	private Payment payment;

	@Embedded
	private Doctor doctor;

	public Appointment() {
	}

	public Appointment(String appDate, Patient patient, Payment payment,
					   Doctor doctor) {
		this.appDate = appDate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", appDate=" + appDate + ", patient=" + patient + ", payment=" + payment
				+ ", doctor=" + doctor + "]";
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
