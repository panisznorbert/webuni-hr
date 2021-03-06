package hu.webuni.hr.panisznorbert.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String post;
	private int salary;
	private LocalDateTime entry;

	@ManyToOne
	private Company company;

	public Employee(){}

	public Employee(Long id, String name, String post, int salary, LocalDateTime entry) {
		this.id = id;
		this.name = name;
		this.post = post;
		this.salary = salary;
		this.entry = entry;
	}

	public Employee(int salary, LocalDateTime entry) {
		this.salary = salary;
		this.entry = entry;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getEntry() {
		return entry;
	}

	public void setEntry(LocalDateTime entry) {
		this.entry = entry;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", post=" + post + ", salary=" + salary + ", entry=" + entry
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
