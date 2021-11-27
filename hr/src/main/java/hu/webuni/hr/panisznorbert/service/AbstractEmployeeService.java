package hu.webuni.hr.panisznorbert.service;

import hu.webuni.hr.panisznorbert.model.Employee;
import hu.webuni.hr.panisznorbert.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractEmployeeService implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        if(employee.getId() == null || !employeeRepository.existsById(employee.getId()))
            return null;
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

}

