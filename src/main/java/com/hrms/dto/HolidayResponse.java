package com.hrms.dto;

public class HolidayResponse {

    private Long id;
    private String name;
    private String date;      
    private String category;
    private String season;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String getCategory() {
        return category;
    }
    public String getSeason() {
        return season;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setSeason(String season) {
        this.season = season;
    }
}
