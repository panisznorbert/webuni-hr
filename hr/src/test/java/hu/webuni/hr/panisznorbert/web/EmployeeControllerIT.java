package hu.webuni.hr.panisznorbert.web;

import static org.assertj.core.api.Assertions.*;
import hu.webuni.hr.panisznorbert.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

    private static final String BASE_URI="/api/employees";

    @Autowired
    WebTestClient webTestClient;

    //érvényes employee input
    @Test
    void testThatCreatedEmployeeIsListed() throws Exception{

        List<EmployeeDto> employeesBefore = getAllEmployees();
        EmployeeDto newEmployee = new EmployeeDto(1L, "Dolgozo5", "fejleszto", 250000, LocalDateTime.of(2010, 12, 05, 8, 0, 0));
        createEmployee(newEmployee);

        List<EmployeeDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter.subList(0, employeesBefore
                .size()))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(employeesBefore);

        assertThat(employeesAfter.get(employeesAfter
                .size()-1))
                .usingRecursiveComparison()
                .isEqualTo(newEmployee);

    }

    //érvénytelen employee input
    @Test
    void testThatWrongCreatedEmployeeIsListed() throws Exception{

        List<EmployeeDto> employeesBefore = getAllEmployees();
        EmployeeDto newEmployee = new EmployeeDto(2L, "", "fejleszto", 250000, LocalDateTime.of(2022, 12, 05, 8, 0, 0));
        createWrongEmployee(newEmployee);

        List<EmployeeDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);

    }

    //érvényes employee módosítás
    @Test
    void testThatModifiedEmployeeIsListed() throws Exception{

        EmployeeDto newEmployee = new EmployeeDto(1L, "Dolgozo5", "fejleszto", 200000, LocalDateTime.of(2022, 12, 05, 8, 0, 0));
        createEmployee(newEmployee);

        List<EmployeeDto> employeesBefore = getAllEmployees();
        EmployeeDto modifyEmployee = new EmployeeDto(1L, "Dolgozo5", "fejleszto", 280000, LocalDateTime.of(2022, 12, 05, 8, 0, 0));
        modifyEmployee(modifyEmployee);

        List<EmployeeDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
        assertThat(employeesAfter.get(employeesAfter.size()-1))
                .usingRecursiveComparison()
                .isEqualTo(newEmployee);
    }


    //érvénytelen employee módosítás
    @Test
    void testThatWrongModifiedEmployeeIsListed() throws Exception{

        EmployeeDto newEmployee = new EmployeeDto(1L, "Dolgozo5", "fejleszto", 200000, LocalDateTime.of(2022, 12, 05, 8, 0, 0));
        createEmployee(newEmployee);

        List<EmployeeDto> employeesBefore = getAllEmployees();
        EmployeeDto modifyEmployee = new EmployeeDto(1L, "Dolgozo5", "fejleszto", 280000, LocalDateTime.of(2002, 12, 05, 8, 0, 0));
        modifyEmployee(modifyEmployee);

        List<EmployeeDto> employeesAfter = getAllEmployees();

        assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
        assertThat(employeesAfter.get(employeesAfter.size()-1))
                .usingRecursiveComparison()
                .isEqualTo(newEmployee);
    }

    private void modifyEmployee(EmployeeDto employee) {
        String path = BASE_URI + "/" + employee.getId();
        webTestClient
                .put()
                .uri(path)
                .bodyValue(employee)
                .exchange();
    }

    private void createWrongEmployee(EmployeeDto newEmployee) {
        webTestClient
                .post()
                .uri(BASE_URI)
                .bodyValue(newEmployee)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    private void createEmployee(EmployeeDto newEmployee) {
        webTestClient
                .post()
                .uri(BASE_URI)
                .bodyValue(newEmployee)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeDtos = webTestClient
                .get()
                .uri(BASE_URI)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(EmployeeDto.class)
                .returnResult().getResponseBody();

        Collections.sort(employeeDtos, (a1, a2) -> Long.compare(a1.getId(), a2.getId()));

        return employeeDtos;
    }

}
