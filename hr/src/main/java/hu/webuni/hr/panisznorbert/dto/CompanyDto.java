package hu.webuni.hr.panisznorbert.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;


public class CompanyDto {
	
	@JsonView(View.BaseData.class)
	private Long id;
	@JsonView(View.BaseData.class)
	private int registrationNumber;
	@JsonView(View.BaseData.class)
	private String name;
	@JsonView(View.BaseData.class)
	private String address;
	
	List<EmployeeDto> employees = new ArrayList<>();
	
	public CompanyDto() {}

	public CompanyDto(Long id, int registrationNumber, String name, String adress, List<EmployeeDto> employees) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = adress;
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
}
