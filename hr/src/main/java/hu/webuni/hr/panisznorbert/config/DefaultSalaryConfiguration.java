package hu.webuni.hr.panisznorbert.config;

import hu.webuni.hr.panisznorbert.service.DefaultEmployeeService;
import hu.webuni.hr.panisznorbert.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!smart")
public class DefaultSalaryConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}
}
