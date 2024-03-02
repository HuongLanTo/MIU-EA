package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	public List<Employee> findByDepartmentId(int id);
}
