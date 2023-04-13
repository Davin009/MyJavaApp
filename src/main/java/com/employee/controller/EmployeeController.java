package com.employee.controller;
import com.employee.exception.DataNotFoundException;
import com.employee.model.EmployeeRepository;
import com.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.zip.DataFormatException;
//import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("all_employees")
    public ResponseEntity<List<EmployeeRepository>> getAllEmployees() {
        List<EmployeeRepository> list = employeeServices.getAllEmployees();
        return new ResponseEntity<List<EmployeeRepository>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @Autowired
    EmployeeService employeeServices;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeRepository> createOrUpdateEmployee(@RequestBody EmployeeRepository employeeRepository)
            throws DataNotFoundException {
        try {
            EmployeeRepository updated = employeeServices.createOrUpdateEmployee(employeeRepository);
            return new ResponseEntity<EmployeeRepository>(updated, new HttpHeaders(), HttpStatus.OK);
        } catch (DataNotFoundException e) {
           throw new DataNotFoundException("User data Not found !!");
        }
    }


    @PutMapping("/employee")
    public ResponseEntity<EmployeeRepository> updateEmployee(@RequestBody EmployeeRepository employeeRepository) throws DataNotFoundException {
try {
    EmployeeRepository updatedEmployee = employeeServices.updateEmployee(employeeRepository);
    return new ResponseEntity<>(updatedEmployee, new HttpHeaders(), HttpStatus.OK);
}catch(DataNotFoundException e){
    throw new DataNotFoundException("Could not update due to some issue : " + e.getMessage());
}
    }
}

