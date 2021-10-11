package hu.webuni.hr.panisznorbert.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import hu.webuni.hr.panisznorbert.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService{

	@Override
	public int getPayRaisePercent(Employee employee) {
				
		if (LocalDateTime.now().minusYears(10).compareTo(employee.getEntry()) >= 0) {
			return 10;
		}
		
		if (LocalDateTime.now().minusYears(10).compareTo(employee.getEntry()) < 0 && LocalDateTime.now().minusYears(5).compareTo(employee.getEntry()) >= 0) {
			return 5;
		}
		
		LocalDateTime now = LocalDateTime.now();
		now.minusYears(2);
		now.minusMonths(6);
		
		if (LocalDateTime.now().minusYears(5).compareTo(employee.getEntry()) < 0 && now.compareTo(employee.getEntry()) >= 0) {
			return 2;
		}
		
		return 0;
	}

}
