package hu.webuni.hr.panisznorbert.web;

import hu.webuni.hr.panisznorbert.dto.EmployeeDto;
import hu.webuni.hr.panisznorbert.mapper.EmployeeMapper;
import hu.webuni.hr.panisznorbert.model.Employee;
import hu.webuni.hr.panisznorbert.repository.EmployeeRepository;
import hu.webuni.hr.panisznorbert.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;


    @GetMapping
    public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
        List<Employee> employees = null;
        if(minSalary == null) {
            employees = employeeService.findAll();
        } else {
            employees = employeeService.findBySalaryGreaterThan(minSalary);
        }
        return employeeMapper.employeesToDtos(employees);
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable long id) {
        Employee employee = findByIdOrThrow(id);
        return employeeMapper.employeeToDto(employee);
    }

    private Employee findByIdOrThrow(long id) {
        return employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
        employeeDto.setId(id);
        Employee updatedEmployee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
        if(updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employeeMapper.employeeToDto(updatedEmployee));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {

        employeeService.delete(id);
    }

    @PostMapping("/payRaise")
    public int getPayRaise(@RequestBody Employee employee) {

        return employeeService.getPayRaisePercent(employee);
    }

    @GetMapping("/post/{post}")
    public List<EmployeeDto> getByPost(@PathVariable String post) {

        return employeeMapper.employeesToDtos(employeeService.findByPost(post));
    }

    @GetMapping("/name/{name}")
    public List<EmployeeDto> getByStartWithName(@PathVariable String name) {

        return employeeMapper.employeesToDtos(employeeService.findByStartWithName(name));
    }

    @GetMapping("/entry/{entry_min}/{entry_max}")
    public List<EmployeeDto> getByPost(@PathVariable LocalDateTime entry_min, @PathVariable LocalDateTime entry_max) {

        return employeeMapper.employeesToDtos(employeeService.findByEntryBetween(entry_min,entry_max));
    }
}
