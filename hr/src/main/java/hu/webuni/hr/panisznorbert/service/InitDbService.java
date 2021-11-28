package hu.webuni.hr.panisznorbert.service;

import hu.webuni.hr.panisznorbert.model.Company;
import hu.webuni.hr.panisznorbert.model.Employee;
import hu.webuni.hr.panisznorbert.repository.CompanyRepository;
import hu.webuni.hr.panisznorbert.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InitDbService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public void clearDB(){

        if(employeeRepository != null){
            employeeRepository.deleteAll();
        }

        if(companyRepository != null){
            companyRepository.deleteAll();
        }
    }

    @Transactional
    public void insertTestData(){

        Company company1 = companyRepository.save(new Company(null, 1, "ceg1", "cim1", null));

        Employee employee1 = employeeRepository.save(new Employee(null, "", "igazgato", 1250000, LocalDateTime.of(2010, 12, 05, 8, 0, 0)));
        Employee employee2 = employeeRepository.save(new Employee(null, "", "fejleszto", 450000, LocalDateTime.of(2011, 12, 05, 8, 0, 0)));
        Employee employee3 = employeeRepository.save(new Employee(null, "", "tesztelő", 400000, LocalDateTime.of(2012, 12, 05, 8, 0, 0)));

        company1.addEmployee(employee1);
        company1.addEmployee(employee2);
        company1.addEmployee(employee3);


        Company company2 = companyRepository.save(new Company(null, 2, "ceg2", "cim2", null));

        Employee employee4 = employeeRepository.save(new Employee(null, "", "igazgato", 800000, LocalDateTime.of(2010, 12, 05, 8, 0, 0)));
        Employee employee5 = employeeRepository.save(new Employee(null, "", "fejleszto", 450000, LocalDateTime.of(2011, 12, 05, 8, 0, 0)));
        Employee employee6 = employeeRepository.save(new Employee(null, "", "tesztelő", 400000, LocalDateTime.of(2012, 12, 05, 8, 0, 0)));

        company2.addEmployee(employee4);
        company2.addEmployee(employee5);
        company2.addEmployee(employee6);
    }
}
