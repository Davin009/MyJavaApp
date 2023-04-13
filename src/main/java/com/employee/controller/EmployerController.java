package com.employee.controller;

import com.employee.model.EmployeeRepository;
import com.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8085/employer/all_employees

@RestController
@RequestMapping("employer")
public class EmployerController {

    @Autowired
    EmployeeService employeeServices;

    @GetMapping("all_employees")
    public ResponseEntity<List<EmployeeRepository>> getAllEmployees() {
        List<EmployeeRepository> list = employeeServices.getAllEmployees();
        return new ResponseEntity<List<EmployeeRepository>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("all_employees/{id}")
    public ResponseEntity<EmployeeRepository> getEmployeeById(@PathVariable("id") Long id) throws Exception {
            EmployeeRepository entity = employeeServices.getEmployeeById(id);
            return new ResponseEntity<EmployeeRepository>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("/employee")
    public ResponseEntity<EmployeeRepository> createOrUpdateEmployee(@RequestBody EmployeeRepository employeeRepository)
            throws Exception {
        EmployeeRepository updated = employeeServices.createOrUpdateEmployee(employeeRepository);
        return new ResponseEntity<EmployeeRepository>(updated, new HttpHeaders(), HttpStatus.OK);

    }
    @PutMapping("/employee")
    public ResponseEntity<EmployeeRepository> updateEmployee(@RequestBody EmployeeRepository employeeRepository) throws Exception {
            EmployeeRepository updatedEmployee = employeeServices.updateEmployee(employeeRepository);
            return new ResponseEntity<>(updatedEmployee, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public String deleteEmployeeByEmpId(@PathVariable("id") Long empId) {
            employeeServices.deleteEmployeeByEmpId(empId);
            return "Deleted Record";
    }
}