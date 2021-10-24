package hu.webuni.hr.panisznorbert.web;

import hu.webuni.hr.panisznorbert.dto.EmployeeDto;
import hu.webuni.hr.panisznorbert.model.Employee;
import hu.webuni.hr.panisznorbert.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private Map<Long, EmployeeDto> employees = new HashMap<>();

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
        return minSalary == null ?
                new ArrayList<>(employees.values()):
                employees.values().stream()
                        .filter(e -> e.getSalary() > minSalary)
                        .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
        EmployeeDto employeeDto = employees.get(id);
        if(employeeDto != null)
            return ResponseEntity.ok(employeeDto);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        employees.put(employeeDto.getId(), employeeDto);
        return employeeDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        if(!employees.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        employeeDto.setId(id);
        employees.put(id, employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employees.remove(id);
    }

    @PostMapping("/payRaise")
    public int getPayRaise(@RequestBody Employee employee) {
        return employeeService.getPayRaisePercent(employee);
    }
}
