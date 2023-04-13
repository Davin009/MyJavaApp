package com.employee.services;

import com.employee.exception.DataNotFoundException;
import com.employee.model.EmployeeRepository;
import com.employee. repository.EmployeeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class EmployeeService {

    @Autowired
    EmployeeRepoInterface  employerRepo;
    public List<EmployeeRepository> getAllEmployees()
    {
        List<EmployeeRepository> employeeList =  employerRepo.findAll();
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeRepository>();
        }
    }

    public EmployeeRepository getEmployeeById(Long id) throws DataNotFoundException
    {
        Optional<EmployeeRepository> employee =  employerRepo.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new DataNotFoundException("No employee record exist for given id");
        }
    }

    public EmployeeRepository createOrUpdateEmployee(EmployeeRepository entity) throws DataNotFoundException
    {
        Optional<EmployeeRepository> employee =  employerRepo.findById(entity.getEmpId());

        if(employee.isPresent())
        {
            EmployeeRepository newEntity = employee.get();
            newEntity.setEmp_Name(entity.getEmp_Name());
            newEntity.setEmp_Job(entity.getEmp_Job());
            newEntity.setEmp_Age(entity.getEmp_Age());
            newEntity.setEmp_Salary(entity.getEmp_Salary());
            newEntity =  employerRepo.save(newEntity);
            return newEntity;
        } else {
            entity =  employerRepo.save(entity);
            return entity;
        }
    }

    public void deleteEmployeeByEmpId(Long empId) throws DataNotFoundException {
        Optional<EmployeeRepository> employee =  employerRepo.findById(empId);
        if(employee.isPresent())
        {
            employerRepo.deleteById(empId);
        } else {
            throw new DataNotFoundException("No employee record exist for given id");
        }
    }

    public EmployeeRepository updateEmployee(EmployeeRepository employeeRepository) {

        Optional<EmployeeRepository> employee =  employerRepo.findById(employeeRepository.getEmpId());
            employeeRepository =  employerRepo.save(employeeRepository);
            return employeeRepository;
    }
}
