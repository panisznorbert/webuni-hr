package hu.webuni.hr.panisznorbert.dto;

import java.time.LocalDateTime;

public class EmployeeDto {

    private Long id;
    private String name;
    private String post;
    private int salary;
    private LocalDateTime entry;

    public EmployeeDto() {

    }

    public EmployeeDto(Long id, String name, String post, int salary, LocalDateTime entry) {
        this.id = id;
        this.name = name;
        this.post = post;
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

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", post=" + post + ", salary=" + salary + ", entry=" + entry
                + "]";
    }
}
