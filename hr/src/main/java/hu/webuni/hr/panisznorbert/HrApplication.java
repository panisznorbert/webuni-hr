package hu.webuni.hr.panisznorbert;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.panisznorbert.model.Employee;
import hu.webuni.hr.panisznorbert.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	private SalaryService salaryService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Employee dolgozo1 = new Employee(12345678910L, "Norbi", "fejleszto", 10000, LocalDateTime.now().minusYears(12));
		
		Employee dolgozo2 = new Employee(12345678910L, "Norbi", "fejleszto", 10000, LocalDateTime.now().minusYears(9));
		
		Employee dolgozo3 = new Employee(12345678910L, "Norbi", "fejleszto", 10000, LocalDateTime.now().minusYears(2));
		
		System.out.println(salaryService.getNewSalary(dolgozo1));
		System.out.println(salaryService.getNewSalary(dolgozo2));
		System.out.println(salaryService.getNewSalary(dolgozo3));
	}
}
