package com.ion.appointment.configs;

import com.ion.appointment.dtos.HealthProvider;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDate;
import java.util.List;

public interface HealthProviderClient {
    @GetExchange("/api/v1/healthproviders/available")
    List<HealthProvider> getAvailableHealthProviders(@RequestParam LocalDate selectedDate, @RequestParam String department);
}