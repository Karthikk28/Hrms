package com.hrms.controller;

import com.hrms.dto.HolidayResponse;
import com.hrms.service.HolidayService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees/{employeeId}/leaves")
@CrossOrigin
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/holidays")
    public List<HolidayResponse> getUpcomingHolidays(
            @PathVariable Long employeeId
    ) {
        return holidayService.getUpcomingHolidays();
    }

    @GetMapping("/holidays/all")
    public List<HolidayResponse> getAllHolidays(
            @PathVariable Long employeeId
    ) {
        return holidayService.getAllHolidays();
    }
}
