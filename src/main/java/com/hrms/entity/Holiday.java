package com.hrms.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@Entity
@Table(name = "holidays")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date")      
    private LocalDate date;

    private String category;    
    private String season;

    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }
    public String getSeason() { return season; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setCategory(String category) { this.category = category; }
    public void setSeason(String season) { this.season = season; }
}
