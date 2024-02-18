package com.jdbc_mapping.contoller;


import com.jdbc_mapping.model.Employee;
import com.jdbc_mapping.repository.EmployeeRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(name="Employee Api")
public class EmployeeController {

	@Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    @Operation(summary="get all employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    @Operation(summary="get one employee")
    public List<Employee> getEmployeeById(@PathVariable Long id) {
        List<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    @PostMapping("/addemployee")
    @Operation(summary="add one employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        int rows = employeeRepository.save(employee);
        if (rows == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/editemployee/{id}")
    @Operation(summary="edit employee")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        List<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        employee.setEmpid(id);
        int rows = employeeRepository.update(employee);
        if (rows == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/remove/{id}")
    @Operation(summary="remove employee")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        List<Employee> employee = employeeRepository.findById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
