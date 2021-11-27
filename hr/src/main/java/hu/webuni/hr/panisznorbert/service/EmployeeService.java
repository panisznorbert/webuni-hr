package hu.webuni.hr.panisznorbert.service;

import hu.webuni.hr.panisznorbert.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	
	public int getPayRaisePercent(Employee employee);

	public Employee save(Employee employee);

	public Employee update(Employee employee);

	public List<Employee> findAll();

	public Optional<Employee> findById(long id);

	public void delete(long id);
	
}
