package hu.webuni.hr.panisznorbert.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Company {

	@Id
	@GeneratedValue
	private Long id;
	private int registrationNumber;
	private String name;
	private String address;

	@OneToMany(mappedBy = "company")
	private List<Employee> employees;

	@ManyToOne
	private CompanyType companyType;

	public Company() {}

	public Company(Long id, int registrationNumber, String name, String adress, List<Employee> employees) {
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		if(this.employees == null)
			this.employees = new ArrayList<>();

		this.employees.add(employee);
		employee.setCompany(this);
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
}
