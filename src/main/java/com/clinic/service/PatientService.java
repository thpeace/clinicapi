package com.clinic.service;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public long getTotalPatients() {
        return patientRepository.count();
    }

    public long getTotalPatientsToday() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return patientRepository.countByDatBetween(startOfDay, endOfDay);
    }
}