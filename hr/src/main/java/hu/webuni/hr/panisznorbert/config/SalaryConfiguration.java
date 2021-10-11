package hu.webuni.hr.panisznorbert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.panisznorbert.service.DefaultEmployeeService;
import hu.webuni.hr.panisznorbert.service.EmployeeService;

@Configuration
@Profile("!smart")
public class SalaryConfiguration {

	@Bean
	public EmployeeService employeeService() {return new DefaultEmployeeService();}
}
