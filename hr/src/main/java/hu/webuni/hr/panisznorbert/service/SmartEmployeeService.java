package hu.webuni.hr.panisznorbert.service;

import java.time.LocalDateTime;

import hu.webuni.hr.panisznorbert.config.HrConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.panisznorbert.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService{

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
				
		if (LocalDateTime.now().minusYears(config.getSalary().getSmart().getMax_year()).compareTo(employee.getEntry()) >= 0) {
			return config.getSalary().getSmart().getMax_percent();
		}
		
		if (LocalDateTime.now().minusYears(config.getSalary().getSmart().getMedium_year()).compareTo(employee.getEntry()) >= 0) {
			return config.getSalary().getSmart().getMedium_percent();
		}

		
		if (LocalDateTime.now().minusYears(config.getSalary().getSmart().getMinimum_year()).minusMonths(config.getSalary().getSmart().getMinimum_month()).compareTo(employee.getEntry()) >= 0) {
			return config.getSalary().getSmart().getMinimum_percent();
		}
		
		return 0;
	}

}
