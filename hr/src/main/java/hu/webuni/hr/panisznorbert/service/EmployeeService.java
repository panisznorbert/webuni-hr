package hu.webuni.hr.panisznorbert.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.panisznorbert.model.Employee;

@Service
public interface EmployeeService {
	
	public int getPayRaisePercent(Employee employee);
	
}
