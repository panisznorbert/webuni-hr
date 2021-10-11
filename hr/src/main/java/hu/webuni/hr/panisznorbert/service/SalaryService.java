package hu.webuni.hr.panisznorbert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.panisznorbert.model.Employee;

@Service
public class SalaryService {

	
	private EmployeeService employeeService;
	
	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public int getNewSalary(Employee employee) {
		
		return (int) (employee.getSalary() * ( employeeService.getPayRaisePercent(employee) / 100.0 + 100 ));
				
	}
}
