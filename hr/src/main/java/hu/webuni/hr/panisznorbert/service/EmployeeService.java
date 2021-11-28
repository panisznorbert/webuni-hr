package hu.webuni.hr.panisznorbert.service;

import hu.webuni.hr.panisznorbert.dto.EmployeeDto;
import hu.webuni.hr.panisznorbert.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	
	int getPayRaisePercent(Employee employee);

	Employee save(Employee employee);

	Employee update(Employee employee);

	List<Employee> findAll();

	Optional<Employee> findById(long id);

	void delete(long id);

	List<Employee> findByPost(String post);

	List<Employee> findByStartWithName(String name);

	List<Employee> findByEntryBetween(LocalDateTime entry_min, LocalDateTime entry_max);

	List<Employee> findBySalaryGreaterThan(Integer minSalary);

	
}
