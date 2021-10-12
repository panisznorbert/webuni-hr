package hu.webuni.hr.panisznorbert.service;

import hu.webuni.hr.panisznorbert.config.HrConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.panisznorbert.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService{

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {		
		return config.getSalary().getDef().getPercent();
	}

}
