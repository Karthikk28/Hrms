package com.hrms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer employeeId;

    private boolean emailNotifications = true;
    private String appearance = "light"; 
    private String language = "english";

    public Integer getId() { return id; }
    public Integer getEmployeeId() { return employeeId; }

    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public boolean isEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(boolean emailNotifications) { this.emailNotifications = emailNotifications; }

    public String getAppearance() { return appearance; }
    public void setAppearance(String appearance) { this.appearance = appearance; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
