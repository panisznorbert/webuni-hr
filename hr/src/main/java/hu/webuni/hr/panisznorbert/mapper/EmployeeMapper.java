package hu.webuni.hr.panisznorbert.mapper;

import hu.webuni.hr.panisznorbert.dto.EmployeeDto;
import hu.webuni.hr.panisznorbert.model.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeDto> employeesToDtos(List<Employee> employees);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "post")
    @Mapping(target = "entryDate", source = "entry")
    EmployeeDto employeeToDto(Employee employee);

    @InheritInverseConfiguration
    Employee dtoToEmployee(EmployeeDto employeeDto);

    List<Employee> dtosToEmployees(List<EmployeeDto> employees);

}