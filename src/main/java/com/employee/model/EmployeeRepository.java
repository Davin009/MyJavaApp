package com.employee.model;
import javax.persistence.*;

@Entity
@Table(name="employee_data")
public class EmployeeRepository {
        @Id
        @Column(name="emp_id")
        //@GeneratedValue
        private Long empId;

        @Column(name="emp_name")
        private String empName;

        @Column(name="emp_job")
        private String empJob;

        @Column(name="emp_age")
        private String empAge;

        @Column(name="emp_salary")
        private double empSalary;



    public Long getEmpId() {
        return empId;
    }

    public String getEmp_Name() {
        return empName;
    }

    public String getEmp_Job() {
        return empJob;
    }
    public String getEmp_Age() {
        return empAge;
    }
    public double getEmp_Salary() {
        return empSalary;
    }



    public void setEmpId(Long empId) {
        this.empId = empId;
    }
    public void setEmp_Name(String emp_Name) {
        this.empName = emp_Name;
    }
    public void setEmp_Job(String emp_Job) {
        this.empJob = emp_Job;
    }
    public void setEmp_Age(String emp_Age) {
        this.empAge = emp_Age;
    }
    public void setEmp_Salary(double emp_Salary) {
        this.empSalary = emp_Salary;
    }

    @Override
    public String toString() {
        return "EmployeeRepository{" +
                "empId=" + empId +
                ", emp_Name='" + empName + '\'' +
                ", emp_Job='" + empJob + '\'' +
                ", emp_Age='" + empAge + '\'' +
                ", emp_Salary=" + empSalary +
                '}';
    }

}
