package com.hr.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EMPLOYEE_NAME", nullable = false, length = 100)
    @NotNull
    private String empName;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DOB")
    private String dob;

    @Column(name = "DOJ")
    private String doj;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "ADHAR_NUM")
    private String adharNum;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "PREVIOUS_COMPANY")
    private String previousCompany;

    @Column(name = "PF_NUMBER")
    private String pfNumber;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "CURRENT_ADDRESS")
    private String currentAddress;

    @Column(name = "PERMANENT_ADDRESS", length = 1000)
    @Size(max = 1000, min = 10)
    private String permanentAddress;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = true;
    
    private String password;
    private String role;

    // Default constructor
    public Employee() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdharNum() {
        return adharNum;
    }

    public void setAdharNum(String adharNum) {
        this.adharNum = adharNum;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPreviousCompany() {
        return previousCompany;
    }

    public void setPreviousCompany(String previousCompany) {
        this.previousCompany = previousCompany;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public void setPfNumber(String pfNumber) {
        this.pfNumber = pfNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    
    
    
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
    public String toString() {
        return "Employee{" +
               "id=" + id +
               ", empName='" + empName + '\'' +
               ", email='" + email + '\'' +
               ", gender='" + gender + '\'' +
               ", dob='" + dob + '\'' +
               ", doj='" + doj + '\'' +
               ", mobile='" + mobile + '\'' +
               ", adharNum='" + adharNum + '\'' +
               ", accountNumber='" + accountNumber + '\'' +
               ", department='" + department + '\'' +
               ", designation='" + designation + '\'' +
               ", previousCompany='" + previousCompany + '\'' +
               ", pfNumber='" + pfNumber + '\'' +
               ", salary=" + salary +
               ", currentAddress='" + currentAddress + '\'' +
               ", permanentAddress='" + permanentAddress + '\'' +
               ", active=" + active +
               '}';
    }
    
}
