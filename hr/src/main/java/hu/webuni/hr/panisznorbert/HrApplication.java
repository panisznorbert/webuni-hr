package hu.webuni.hr.panisznorbert;

import java.time.LocalDateTime;

import hu.webuni.hr.panisznorbert.config.HrConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.panisznorbert.model.Employee;
import hu.webuni.hr.panisznorbert.service.SalaryService;
import hu.webuni.hr.panisznorbert.config.HrConfigProperties.Smart;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	@Autowired
	HrConfigProperties config;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Smart smartConfig = config.getSalary().getSmart();
		for (Double limit :
				smartConfig.getLimits().keySet()
			) {

			int origSalary = 100;
			LocalDateTime limitDay = LocalDateTime.now().minusDays((long)(limit*365));
			Employee e1 = new Employee(1L, "Dolgozo 1", "fejlesztő", origSalary, limitDay.plusDays(1));
			Employee e2 = new Employee(2L, "Dolgozo 2r", "projektmenedzser", origSalary, limitDay.minusDays(1));

			salaryService.setNewSalary(e1);
			salaryService.setNewSalary(e2);

			System.out.format("1 nappal a %.2f éves határ előtt az új fizetés %d%n", limit, e1.getSalary());
			System.out.format("1 nappal a %.2f éves határ után az új fizetés %d%n", limit, e2.getSalary());
		}

	}
	

}
