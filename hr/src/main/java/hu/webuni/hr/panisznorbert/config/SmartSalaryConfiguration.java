package hu.webuni.hr.panisznorbert.config;

import hu.webuni.hr.panisznorbert.service.SmartEmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.panisznorbert.service.EmployeeService;

@Configuration
@Profile("smart")
public class SmartSalaryConfiguration {

	@Bean
	public EmployeeService employeeService() {return new SmartEmployeeService();}
}
