package com.hrms.service;

import com.hrms.dto.HolidayResponse;
import com.hrms.entity.Holiday;
import com.hrms.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;
    private final DateTimeFormatter df = DateTimeFormatter.ISO_DATE;

    public HolidayServiceImpl(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @Override
    public List<HolidayResponse> getUpcomingHolidays() {
        return holidayRepository
                .findByDateAfterOrderByDateAsc(LocalDate.now().minusDays(1))
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<HolidayResponse> getAllHolidays() {
        return holidayRepository
                .findAllByOrderByDateAsc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HolidayResponse mapToResponse(Holiday h) {
        HolidayResponse r = new HolidayResponse();
        r.setId(h.getId());
        r.setName(h.getName());
        r.setDate(h.getDate() != null ? h.getDate().format(df) : null);
        r.setCategory(h.getCategory());
        r.setSeason(h.getSeason());
        return r;
    }
}
