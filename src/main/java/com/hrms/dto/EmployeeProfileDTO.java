package com.hrms.dto;

import java.time.LocalDate;

import com.hrms.entity.Employee;

public class EmployeeProfileDTO {
    private Long id;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private String designation;
    private String location;
    private String currentAddress;
    private LocalDate joiningDate;
    private LocalDate dob;
    private String gender;
    private String maritalStatus;
    private String bloodGroup;
    private String profileImage;
    private Employee manager;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmployeeCode() {
		return employeeCode;
	}
 void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
		public String getLastName() {
		return lastName;
	}
		public void setLastName(String lastName) {
		this.lastName = lastName;
	}
		public String getEmail() {
		return email;
	}
		public void setEmail(String email) {
		this.email = email;
	}
		public String getPhone() {
		return phone;
	}
		public void setPhone(String phone) {
		this.phone = phone;
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
		public String getLocation() {
		return location;
	}
		public void setLocation(String location) {
		this.location = location;
	}
		public String getCurrentAddress() {
		return currentAddress;
	}
		public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
		public LocalDate getJoiningDate() {
		return joiningDate;
	}
		public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
		public LocalDate getDob() {
		return dob;
	}
		public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public String getGender() {
		return gender;
	}
		public void setGender(String gender) {
		this.gender = gender;
	}
		public String getMaritalStatus() {
		return maritalStatus;
	}
		public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
		public String getBloodGroup() {
		return bloodGroup;
	}
		public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
		public String getProfileImage() {
		return profileImage;
	}
		public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
		public Employee getManager() {
		return manager;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}
}