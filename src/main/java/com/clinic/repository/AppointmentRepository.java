package com.clinic.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find active appointments within a date range
    List<Appointment> findByActiveTrueAndStartTimeBetween(LocalDateTime start, LocalDateTime end);

    // Find all active appointments
    List<Appointment> findByActiveTrue();
}
