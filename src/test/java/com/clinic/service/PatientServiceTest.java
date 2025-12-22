package com.clinic.service;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PatientService.
 * 
 * Best Practices Applied:
 * - Test isolation with mocks
 * - Testing empty collections
 * - Verifying repository interactions
 * - Testing with multiple scenarios
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("PatientService Tests")
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService sut;

    private Patient testPatient;

    @BeforeEach
    void setUp() {
        testPatient = new Patient();
        testPatient.setId(1L);
        testPatient.setHn("HN001");
        testPatient.setFname("John");
        testPatient.setLname("Doe");
    }

    @Nested
    @DisplayName("getAllPatients Tests")
    class GetAllPatientsTests {

        @Test
        @DisplayName("getAllPatients should return list of patients")
        void getAllPatients_ShouldReturnListOfPatients() {
            // Arrange
            Patient patient2 = new Patient();
            patient2.setId(2L);
            patient2.setHn("HN002");

            List<Patient> expectedPatients = Arrays.asList(testPatient, patient2);
            when(patientRepository.findAll()).thenReturn(expectedPatients);

            // Act
            List<Patient> result = sut.getAllPatients();

            // Assert
            assertEquals(2, result.size());
            assertEquals(expectedPatients, result);
        }

        @Test
        @DisplayName("getAllPatients should return empty list when no patients exist")
        void getAllPatients_WhenNoPatients_ShouldReturnEmptyList() {
            // Arrange
            when(patientRepository.findAll()).thenReturn(Collections.emptyList());

            // Act
            List<Patient> result = sut.getAllPatients();

            // Assert
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("getAllPatients should call repository findAll")
        void getAllPatients_ShouldCallRepositoryFindAll() {
            // Arrange
            when(patientRepository.findAll()).thenReturn(Collections.emptyList());

            // Act
            sut.getAllPatients();

            // Assert
            verify(patientRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("savePatient Tests")
    class SavePatientTests {

        @Test
        @DisplayName("savePatient should return saved patient")
        void savePatient_ShouldReturnSavedPatient() {
            // Arrange
            when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

            // Act
            Patient result = sut.savePatient(testPatient);

            // Assert
            assertNotNull(result);
            assertEquals(testPatient.getId(), result.getId());
            assertEquals(testPatient.getHn(), result.getHn());
        }

        @Test
        @DisplayName("savePatient should call repository save with patient")
        void savePatient_ShouldCallRepositorySave() {
            // Arrange
            when(patientRepository.save(testPatient)).thenReturn(testPatient);

            // Act
            sut.savePatient(testPatient);

            // Assert
            verify(patientRepository).save(testPatient);
        }

        @Test
        @DisplayName("savePatient should return patient with generated ID")
        void savePatient_ShouldReturnPatientWithGeneratedId() {
            // Arrange
            Patient newPatient = new Patient();
            newPatient.setFname("Jane");

            Patient savedPatient = new Patient();
            savedPatient.setId(100L);
            savedPatient.setFname("Jane");

            when(patientRepository.save(newPatient)).thenReturn(savedPatient);

            // Act
            Patient result = sut.savePatient(newPatient);

            // Assert
            assertNotNull(result.getId());
            assertEquals(100L, result.getId());
        }
    }
}
