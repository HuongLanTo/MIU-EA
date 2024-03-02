package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import domain.Book;
import domain.Department;
import domain.Employee;
import domain.Flight;
import domain.Passenger;
import domain.Publisher;
import domain.School;
import domain.Student;
import repository.BookRepository;
import repository.DepartmentRepository;
import repository.PassengerRepository;
import repository.PublisherRepository;
import repository.SchoolRepository;
import repository.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Lab4PartAApplication implements CommandLineRunner {
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab4PartAApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// a) Department - Employee
		System.out.println("a) Department - Employee");
		Department department = new Department();
		department.setName("Department 1");
		Employee employee1 = new Employee(001, "Jisoo", department);
		Employee employee2 = new Employee(002, "Jenny", department);
		Employee employee3 = new Employee(003, "Rose", department);
		List<Employee> employees = new ArrayList<>();
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
		department.setEmployees(employees);
		departmentRepository.save(department);
		for (Department dept : departmentRepository.findAll()) {
			System.out.println(dept);
			for (Employee emp : dept.getEmployees()) {
				System.out.println(emp);
			}
		}
		
		// b) Book - Publisher
		System.out.println("b) Book - Publisher");
		Publisher publisher1 = new Publisher("Publisher1");
		Publisher publisher2 = new Publisher("Publisher2");
		Publisher publisher3 = new Publisher("Publisher3");
		publisherRepository.save(publisher1);
		publisherRepository.save(publisher2);
		publisherRepository.save(publisher3);
		Book book1 = new Book("isbn1", "Title1", "Author1", publisher1);
		Book book2 = new Book("isbn2", "Title2", "Author2", publisher1);
		Book book3 = new Book("isbn3", "Title3", "Author3", publisher2);
		Book book4 = new Book("isbn4", "Title4", "Author4", null);
		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		bookRepository.save(book4);
		for (Book book : bookRepository.findAll()) {
			System.out.println(book);
		}
		
		// c) Passenger - Flight
		System.out.println("c) Passenger - Flight");
		Passenger passenger = new Passenger("Lisa");
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight(1, "Location1", "Location2", new Date()));
		flights.add(new Flight(2, "Location2", "Location3", new Date()));
		flights.add(new Flight(3, "Location3", "Location4", new Date()));
		passenger.setFlights(flights);
		passengerRepository.save(passenger);
		for (Passenger pass : passengerRepository.findAll()) {
			System.out.println(pass);
			for (Flight flight: pass.getFlights()) {
				System.out.println(flight);
			}
		}
		
		// d) School - Student
		System.out.println("d) School - Student");
		Student student1 = new Student("First1", "Last1");
		Student student2 = new Student("First2", "Last2");
		Student student3 = new Student("First3", "Last3");
		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);
		School school1 = new School("School1");
		Map<Integer, Student> students = new HashMap<Integer, Student>();
		for (Student student : studentRepository.findAll()) {
			students.put(student.getStudentId(), student);
		}
		school1.setStudents(students);
		schoolRepository.save(school1);
		for (School school : schoolRepository.findAll()) {
			System.out.println(school);
			Map<Integer,Student> studentMap = school.getStudents();
			for(Map.Entry<Integer, Student> studentObj: studentMap.entrySet()) {
				Student student = studentObj.getValue();
				System.out.println(student);
			}
		}
		
	}

}
