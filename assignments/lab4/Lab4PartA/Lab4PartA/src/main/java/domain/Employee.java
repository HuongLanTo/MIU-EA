package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
	@GeneratedValue
	@Id
	private int id;
	private int employeeNumber;
	private String name;
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	public Employee() {
	}
	
	public Employee(int employeeNumber, String name, Department department) {
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeNumber=" + employeeNumber + ", name=" + name + "]";
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
