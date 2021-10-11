package hu.webuni.hr.panisznorbert.service;

import org.springframework.beans.factory.annotation.Autowired;

import hu.webuni.hr.panisznorbert.model.Employee;

public class SalaryService {

	@Autowired
	EmployeeService employeeService;
	
	public int getNewSalary(Employee employee) {
		
		return (int) (employee.getSalary() * ( employeeService.getPayRaisePercent(employee) / 100.0 + 100 ));
				
	}
}
