package com.hrms.dto;

import java.util.ArrayList;
import java.util.List;

public class OrgNodeResponse {

    private Long id;
    private String name;
    private String role;
    private List<OrgNodeResponse> children = new ArrayList<>();

    public OrgNodeResponse() {}

    public OrgNodeResponse(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public List<OrgNodeResponse> getChildren() { return children; }

    public void setChildren(List<OrgNodeResponse> children) {
        this.children = children;
    }
}
