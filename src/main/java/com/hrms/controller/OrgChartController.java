package com.hrms.controller;

import com.hrms.service.OrgChartService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/org-chart")
@CrossOrigin(origins = "http://localhost:3000")
public class OrgChartController {

    private final OrgChartService service;

    public OrgChartController(OrgChartService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, Object> getOrgChart() {
        return service.getOrgChart();
    }
}
