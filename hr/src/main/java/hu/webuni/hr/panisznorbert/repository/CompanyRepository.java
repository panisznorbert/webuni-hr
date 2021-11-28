package hu.webuni.hr.panisznorbert.repository;

import hu.webuni.hr.panisznorbert.model.Company;

import hu.webuni.hr.panisznorbert.model.EmployeeAvgSalaryByPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT DISTINCT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary")
    List<Company> findBySalaryGreaterThan(Integer minSalary);

    @Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :personLimit")
    List<Company> countEmployeeGreaterThan(Integer personLimit);

    @Query("SELECT e.post AS post, avg(e.salary) AS avgSalary "
            + "FROM Company c "
            + "INNER JOIN c.employees e  "
            + "WHERE c.id = :id "
            + "GROUP BY e.post "
            + "ORDER BY avg(e.salary) DESC")
    List<EmployeeAvgSalaryByPost> findAvgSalariesByPost(long id);
}
