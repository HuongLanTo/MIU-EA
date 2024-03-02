package domain;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;

@Entity
public class School {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER)
	@MapKey(name="studentId")
	private Map<Integer, Student> students = new HashMap<Integer, Student>();
	
	public School() {
	}

	public School(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public Map<Integer, Student> getStudents() {
		return students;
	}

	public void setStudents(Map<Integer, Student> students) {
		this.students = students;
	}
	
}
