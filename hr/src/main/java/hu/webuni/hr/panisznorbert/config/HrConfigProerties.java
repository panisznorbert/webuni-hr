package hu.webuni.hr.panisznorbert.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProerties {

	
	
	public static class Salary{
	
		
	}
	
	public static class Default{
		
		
	}
	
	public static class Smart{
		
	}
}
