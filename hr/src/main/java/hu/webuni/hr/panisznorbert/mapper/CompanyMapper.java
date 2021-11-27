package hu.webuni.hr.panisznorbert.mapper;

import hu.webuni.hr.panisznorbert.dto.CompanyDto;
import hu.webuni.hr.panisznorbert.dto.EmployeeDto;
import hu.webuni.hr.panisznorbert.model.Company;
import hu.webuni.hr.panisznorbert.model.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	
	List<CompanyDto> companysToDtos(List<Company> companys);

	CompanyDto companyToDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);

	List<Company> dtosToCompanys(List<CompanyDto> companies);
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "title", source = "post")
	@Mapping(target = "entryDate", source = "entry")
	EmployeeDto employeeToDto(Employee employee);
	
	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companyToDtoWithoutEmployeees(Company company);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companysToDtosWithoutEmployeees(List<Company> companys);

}
