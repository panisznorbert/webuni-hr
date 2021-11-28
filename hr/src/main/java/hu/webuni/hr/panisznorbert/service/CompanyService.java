package hu.webuni.hr.panisznorbert.service;

import hu.webuni.hr.panisznorbert.dto.CompanyDto;
import hu.webuni.hr.panisznorbert.model.Company;
import hu.webuni.hr.panisznorbert.model.Employee;
import hu.webuni.hr.panisznorbert.model.EmployeeAvgSalaryByPost;
import hu.webuni.hr.panisznorbert.repository.CompanyRepository;
import hu.webuni.hr.panisznorbert.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public Company addEmployee(long id, Employee employee) {
        Company company = companyRepository.findById(id).get();
        company.addEmployee(employee);
        employeeRepository.save(employee);
        return company;
    }

    @Transactional
    public Company deleteEmployee(long id, long employeeId) {
        Company company = companyRepository.findById(id).get();
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setCompany(null);
        company.getEmployees().remove(employee);

        return company;
    }


    public Company replaceEmployees(long id, List<Employee> employees) {
        Company company = companyRepository.findById(id).get();
        company.getEmployees().forEach(e ->{
            e.setCompany(null);
        });
        company.getEmployees().clear();

        employees.forEach(e ->{
            employeeRepository.save(e);
            company.addEmployee(e);
        });
        return company;
    }


    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public Company update(Company company) {
        if(company.getId() == null || !companyRepository.existsById(company.getId()))
            return null;
        return companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(long id) {
        return companyRepository.findById(id);
    }

    public void delete(long id) {
        companyRepository.deleteById(id);
    }

    void deleteAll(){ companyRepository.deleteAll();}

    public List<Company> findBySalaryGreaterThan(Integer minSalary){
        return companyRepository.findBySalaryGreaterThan(minSalary);
    }

    public List<Company> countEmployeeGreaterThan(Integer personLimit){
        return companyRepository.countEmployeeGreaterThan(personLimit);
    }

    public List<EmployeeAvgSalaryByPost> findAvgSalariesByPost(long id){
        return companyRepository.findAvgSalariesByPost(id);
    }

}
