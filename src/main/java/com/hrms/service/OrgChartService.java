package com.hrms.service;

import com.hrms.entity.Employee;
import com.hrms.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrgChartService {

    private final EmployeeRepository repo;

    public OrgChartService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Map<String, Object> getOrgChart() {

        List<Employee> employees = repo.findAll();

        Map<Long, List<Employee>> grouped =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                e -> e.getManager() == null
                                        ? 0L
                                        : e.getManager().getId()
                        ));

        return buildTree(grouped, 0L);
    }

    private Map<String, Object> buildTree(Map<Long, List<Employee>> map, Long managerId) {

        Map<String, Object> root = new HashMap<>();
        List<Map<String, Object>> children = new ArrayList<>();

        for (Employee e : map.getOrDefault(managerId, new ArrayList<>())) {

            Map<String, Object> node = new HashMap<>();

            node.put("id", e.getId());
            node.put("name", e.getFirstName() + " " + e.getLastName());
            node.put("role", e.getDesignation());
            node.put("email", e.getEmail());
            node.put("phone", e.getPhone());
            node.put("location", e.getLocation());

            node.put("children",
                    buildTree(map, e.getId()).get("children"));

            children.add(node);
        }

        root.put("children", children);
        return root;
    }
}
