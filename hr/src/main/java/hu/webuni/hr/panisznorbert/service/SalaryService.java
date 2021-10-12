package hu.webuni.hr.panisznorbert.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.panisznorbert.model.Employee;

@Service
public class SalaryService {

	
	private EmployeeService employeeService;
	
	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	public void setNewSalary(Employee employee) {
		
		employee.setSalary((int) (employee.getSalary() * ( employeeService.getPayRaisePercent(employee) / 100.0 + 100 )));
		
				
	}
}
