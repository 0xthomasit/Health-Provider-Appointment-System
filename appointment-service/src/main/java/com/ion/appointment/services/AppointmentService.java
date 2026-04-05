package com.ion.appointment.services;

import java.time.LocalDate;

public interface AppointmentService {
    String bookAppointment(LocalDate date, String hpDepartment);
}
