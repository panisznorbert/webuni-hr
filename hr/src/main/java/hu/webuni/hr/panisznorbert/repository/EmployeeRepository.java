package hu.webuni.hr.panisznorbert.repository;

import hu.webuni.hr.panisznorbert.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByPost(String title);
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	List<Employee> findByEntryBetween(LocalDateTime from, LocalDateTime to);
	List<Employee> findBySalaryGreaterThan(Integer minSalary);
	
}
