package hu.webuni.hr.panisznorbert.web;

import hu.webuni.hr.panisznorbert.dto.CompanyDto;
import hu.webuni.hr.panisznorbert.dto.EmployeeDto;
import hu.webuni.hr.panisznorbert.mapper.CompanyMapper;
import hu.webuni.hr.panisznorbert.mapper.EmployeeMapper;
import hu.webuni.hr.panisznorbert.model.Company;
import hu.webuni.hr.panisznorbert.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private CompanyMapper companyMapper;
	private CompanyService companyService;
	private EmployeeMapper employeeMapper;

	public CompanyController(CompanyMapper companyMapper, CompanyService companyService, EmployeeMapper employeeMapper) {
		super();
		this.companyMapper = companyMapper;
		this.companyService = companyService;
		this.employeeMapper = employeeMapper;
	}


	@GetMapping
	public List<CompanyDto> getCompanies(@RequestParam(required = false) Boolean full) {
		List<Company> companies = companyService.findAll();
		return full != null && full ?
				companyMapper.companysToDtos(companies)
				: companyMapper.companysToDtosWithoutEmployeees(companies);
	}


	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable long id, @RequestParam(required = false) Boolean full) {
		Company company = companyService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return full != null && full ?
				companyMapper.companyToDto(company)
				: companyMapper.companyToDtoWithoutEmployeees(company);
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {}

	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto) {
		try {
			return companyMapper
					.companyToDto(companyService.addEmployee(companyId, employeeMapper.dtoToEmployee(employeeDto)));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{companyId}/employees/{empId}")
	public CompanyDto deleteEmployee(@PathVariable long companyId, @PathVariable long empId) {
		return null;
	}

	@PutMapping("/{companyId}/employees")
	public CompanyDto replaceEmployees(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployees) {
		return null;
	}

}
