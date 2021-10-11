package hu.webuni.hr.panisznorbert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.panisznorbert.service.EmployeeService;
import hu.webuni.hr.panisznorbert.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartSlarayConfiguration {
	
	//@Bean
	//public EmployeeService employeeService() {return new SmartEmployeeService();}

}
