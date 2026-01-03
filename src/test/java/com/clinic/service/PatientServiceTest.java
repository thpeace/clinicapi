package com.clinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("PatientService Tests")
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    private PatientService sut;

    @BeforeEach
    void setUp() {
        sut = new PatientService(patientRepository);
    }

    @Test
    @DisplayName("getAllPatients should return list of patients")
    void getAllPatients_ShouldReturnListOfPatients() {
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = sut.getAllPatients();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(patientRepository).findAll();
    }

    @Test
    @DisplayName("savePatient should return saved patient")
    void savePatient_ShouldReturnSavedPatient() {
        Patient patient = new Patient();
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient result = sut.savePatient(patient);

        assertNotNull(result);
        verify(patientRepository).save(patient);
    }

    @Test
    @DisplayName("getTotalPatients should return count")
    void getTotalPatients_ShouldReturnCount() {
        when(patientRepository.count()).thenReturn(10L);

        long count = sut.getTotalPatients();

        assertEquals(10L, count);
        verify(patientRepository).count();
    }

    @Test
    @DisplayName("getTotalPatientsToday should return count for today")
    void getTotalPatientsToday_ShouldReturnCountForToday() {
        when(patientRepository.countByDatBetween(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(5L);

        long count = sut.getTotalPatientsToday();

        assertEquals(5L, count);
        verify(patientRepository).countByDatBetween(any(LocalDateTime.class), any(LocalDateTime.class));
    }
}
