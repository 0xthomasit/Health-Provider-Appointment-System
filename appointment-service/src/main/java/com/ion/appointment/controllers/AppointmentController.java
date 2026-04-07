package com.ion.appointment.controllers;

import com.ion.appointment.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public String bookAppointment(
            @RequestParam LocalDate selectedDate,
            @RequestParam String department) {
        return appointmentService.bookAppointment(selectedDate, department);
    }

}