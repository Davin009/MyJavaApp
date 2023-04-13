package com.employee.controller;

import com.employee.exception.DataNotFoundException;
import com.employee.model.EmployeeRepository;
import com.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<EmployeeRepository> getEmployeeById(@PathVariable("id") Long id) throws DataNotFoundException {
        try {
            EmployeeRepository entity = employeeServices.getEmployeeById(id);
            return new ResponseEntity<EmployeeRepository>(entity, new HttpHeaders(), HttpStatus.OK);
        }
        catch (DataNotFoundException e) {
            throw new DataNotFoundException("User data Not found in our Record!!");
        }
    }
    @PostMapping("/employee")
    public ResponseEntity<EmployeeRepository> createOrUpdateEmployee(@RequestBody EmployeeRepository employeeRepository)
            throws DataNotFoundException {
        try {
            EmployeeRepository updated = employeeServices.createOrUpdateEmployee(employeeRepository);
            return new ResponseEntity<EmployeeRepository>(updated, new HttpHeaders(), HttpStatus.OK);
        }
         catch (DataNotFoundException e) {
            throw new DataNotFoundException("User data Not found in our Record!!");
        }

    }
    @PutMapping("/employee")
    public ResponseEntity<EmployeeRepository> updateEmployee(@RequestBody EmployeeRepository employeeRepository) throws DataNotFoundException {
        try {
            EmployeeRepository updatedEmployee = employeeServices.updateEmployee(employeeRepository);
            return new ResponseEntity<>(updatedEmployee, new HttpHeaders(), HttpStatus.OK);
        }
        catch(DataNotFoundException e) {
            throw new DataNotFoundException("User data Not found in our Record!!");
        }
    }

    @PreAuthorize("HasRole('EMPLOYER')")
    @DeleteMapping("/{id}")
    public String deleteEmployeeByEmpId(@PathVariable("id") Long empId) throws DataNotFoundException {
        try {
            employeeServices.deleteEmployeeByEmpId(empId);
            return "Deleted Record";
        }
        catch (DataNotFoundException e){
            throw new DataNotFoundException("User data Not found in our Record!!");
        }
    }
}