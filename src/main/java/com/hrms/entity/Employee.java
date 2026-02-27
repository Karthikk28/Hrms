package com.hrms.entity;
 
import jakarta.persistence.*;

import java.time.LocalDate;
 
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name = "employees")

public class Employee {
 
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
 
    @Column(name = "user_id")

    private Long userId;
 
    @Column(name = "employee_code", nullable = false, unique = true)

    private String employeeCode;
 
    private String firstName;

    private String lastName;
 
    @Column(unique = true)

    private String email;

    private String phone;

    private String department;

    private String designation;

    @ManyToOne

    @JoinColumn(name = "manager_id")

    private Employee manager;
 
    

    @JsonFormat(pattern = "yyyy-MM-dd")

    private LocalDate dob;

    private String gender;

    private String location;

    private String currentAddress;
 
    @JsonFormat(pattern = "yyyy-MM-dd")

    private LocalDate joiningDate;
 
    @Column(name = "employment_status")

    private String employmentStatus;

    private String maritalStatus;

    private String bloodGroup;

    @Lob

    @Column(columnDefinition = "LONGTEXT")

    private String profileImage;

    private Double annualCtc;
 
    private String bankName;
 
    private String accountNumber;
 
    private String ifscCode;

    private String nomineeName;
 
    private String nomineeRelationship;
 
    private String nomineeContact;

    @Lob

    @Column(columnDefinition = "LONGTEXT")

    private String aadhaarCard;
 
    @Lob

    @Column(columnDefinition = "LONGTEXT")

    private String panCard;
 
    @Lob

    @Column(columnDefinition = "LONGTEXT")

    private String offerLetter;
 
    @Lob

    @Column(columnDefinition = "LONGTEXT")

    private String bankCheque;

    public String getAadhaarCard() {

		return aadhaarCard;

	}
 
	public void setAadhaarCard(String aadhaarCard) {

		this.aadhaarCard = aadhaarCard;

	}
 
	public String getPanCard() {

		return panCard;

	}
 
	public void setPanCard(String panCard) {

		this.panCard = panCard;

	}
 
	public String getOfferLetter() {

		return offerLetter;

	}
 
	public void setOfferLetter(String offerLetter) {

		this.offerLetter = offerLetter;

	}
 
	public String getBankCheque() {

		return bankCheque;

	}
 
	public void setBankCheque(String bankCheque) {

		this.bankCheque = bankCheque;

	}
 
	public String getNomineeName() {

        return nomineeName;

    }
 
    public void setNomineeName(String nomineeName) {

        this.nomineeName = nomineeName;

    }
 
    public String getNomineeRelationship() {

        return nomineeRelationship;

    }
 
    public void setNomineeRelationship(String nomineeRelationship) {

        this.nomineeRelationship = nomineeRelationship;

    }
 
    public String getNomineeContact() {

        return nomineeContact;

    }
 
    public void setNomineeContact(String nomineeContact) {

        this.nomineeContact = nomineeContact;

    }

    public Double getAnnualCtc() {

        return annualCtc;

    }
 
    public void setAnnualCtc(Double annualCtc) {

        this.annualCtc = annualCtc;

    }
 
    public String getBankName() {

        return bankName;

    }
 
    public void setBankName(String bankName) {

        this.bankName = bankName;

    }
 
    public String getAccountNumber() {

        return accountNumber;

    }
 
    public void setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;

    }
 
    public String getIfscCode() {

        return ifscCode;

    }
 
    public void setIfscCode(String ifscCode) {

        this.ifscCode = ifscCode;

    }
 
    public String getProfileImage() {

		return profileImage;

	}
 
	public void setProfileImage(String profileImage) {

		this.profileImage = profileImage;

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
 
    public String getCurrentAddress() {

        return currentAddress;

    }
 
    public void setCurrentAddress(String currentAddress) {

        this.currentAddress = currentAddress;

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

	public String getPhone() {

		return phone;

	}
 
	public void setPhone(String phone) {

		this.phone = phone;

	}
 
	public String getEmploymentStatus() {

		return employmentStatus;

	}
 
	public void setEmploymentStatus(String employmentStatus) {

		this.employmentStatus = employmentStatus;

	}
 
	public Long getId() {

		return id;

	}
 
	public void setId(Long id) {

		this.id = id;

	}
 
	public Long getUserId() {

		return userId;

	}
 
	public void setUserId(Long userId) {

		this.userId = userId;

	}
 
	public String getEmployeeCode() {

		return employeeCode;

	}
 
	public void setEmployeeCode(String employeeCode) {

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
 
	public Employee getManager() {

	    return manager;

	}
 
	public void setManager(Employee manager) {

	    this.manager = manager;

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
 
 
	public LocalDate getJoiningDate() {

		return joiningDate;

	}
 
	public void setJoiningDate(LocalDate joiningDate) {

		this.joiningDate = joiningDate;

	}
 
	public String  getLocation() {

		return location;

	}

	public void setLocation(String location) {

	    this.location = location;

	}
 
 
    

}
 