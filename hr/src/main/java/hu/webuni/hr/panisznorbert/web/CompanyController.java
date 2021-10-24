package hu.webuni.hr.panisznorbert.web;

import com.fasterxml.jackson.annotation.JsonView;
import hu.webuni.hr.panisznorbert.dto.CompanyDto;
import hu.webuni.hr.panisznorbert.dto.EmployeeDto;
import hu.webuni.hr.panisznorbert.dto.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();


	@JsonView(View.BaseData.class)
	@GetMapping(params = "full=false")
	public List<CompanyDto> getCompanies(@RequestParam boolean full) {
			
		return new ArrayList<>(companies.values());
	}
	
	@GetMapping(params = "full=true")
	public List<CompanyDto> getCompaniesWithEmployees(@RequestParam boolean full) {
			
		return new ArrayList<>(companies.values());
	}
	

	private CompanyDto createCompanyWithoutEmployees(CompanyDto c) {
		return new CompanyDto(c.getId(),c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		CompanyDto companyDto = companies.get(id);
		if(companyDto != null) {
			return ResponseEntity.ok((full == null || !full) ? createCompanyWithoutEmployees(companyDto) : companyDto);
		}
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		companies.put(companyDto.getId(), companyDto);
		return companyDto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if(!companies.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		
		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}
	
	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto){
		CompanyDto companyDto = getCompanyOrNotFound(companyId);
		companyDto.getEmployees().add(employeeDto);
		return companyDto;
	}
	
	@DeleteMapping("/{companyId}/employees/{empId}")
	public CompanyDto deleteEmployee(@PathVariable long companyId, @PathVariable long empId) {
		CompanyDto companyDto = getCompanyOrNotFound(companyId);
		companyDto.getEmployees().removeIf(emp -> emp.getId() == empId);
		return companyDto;
	}
	
	@PutMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployees){
		CompanyDto companyDto = getCompanyOrNotFound(companyId);
		companyDto.setEmployees(newEmployees);
		return companyDto;
	}
	

	private CompanyDto getCompanyOrNotFound(long companyId) {
		CompanyDto companyDto = companies.get(companyId);
		if(companyDto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return companyDto;
	}
}
