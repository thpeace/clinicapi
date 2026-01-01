package com.clinic.repository;

import com.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    long countByDatBetween(LocalDateTime start, LocalDateTime end);
}