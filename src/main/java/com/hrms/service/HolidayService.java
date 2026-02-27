package com.hrms.service;

import com.hrms.dto.HolidayResponse;
import java.util.List;

public interface HolidayService {

    List<HolidayResponse> getUpcomingHolidays();

    List<HolidayResponse> getAllHolidays();
}
