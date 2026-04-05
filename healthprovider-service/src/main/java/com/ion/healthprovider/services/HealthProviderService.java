package com.ion.healthprovider.services;

import com.ion.healthprovider.models.HealthProvider;

import java.time.LocalDate;
import java.util.List;

public interface HealthProviderService {

    String createHealthProvider(HealthProvider healthProvider);

    List<HealthProvider> getAllAvailableHealthProviderByDateAndDepartment(LocalDate selectedDate, String department);

}
