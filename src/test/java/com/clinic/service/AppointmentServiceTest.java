package com.clinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clinic.model.Appointment;
import com.clinic.repository.AppointmentRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("AppointmentService Tests")
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    private AppointmentService sut;

    private static final Long TEST_ID = 1L;

    @BeforeEach
    void setUp() {
        sut = new AppointmentService(appointmentRepository);
    }

    @Test
    @DisplayName("getAllAppointments should return list")
    void getAllAppointments_ShouldReturnList() {
        List<Appointment> list = Arrays.asList(new Appointment(), new Appointment());
        when(appointmentRepository.findAll()).thenReturn(list);

        List<Appointment> result = sut.getAllAppointments();

        assertEquals(2, result.size());
        verify(appointmentRepository).findAll();
    }

    @Test
    @DisplayName("getActiveAppointments should return active appointments")
    void getActiveAppointments_ShouldReturnActiveAppointments() {
        List<Appointment> list = Arrays.asList(new Appointment());
        when(appointmentRepository.findByActiveTrue()).thenReturn(list);

        List<Appointment> result = sut.getActiveAppointments();

        assertEquals(1, result.size());
        verify(appointmentRepository).findByActiveTrue();
    }

    @Test
    @DisplayName("getAppointmentById should return appointment when found")
    void getAppointmentById_ShouldReturnAppointment_WhenFound() {
        Appointment appt = new Appointment();
        appt.setId(TEST_ID);
        when(appointmentRepository.findById(TEST_ID)).thenReturn(Optional.of(appt));

        Optional<Appointment> result = sut.getAppointmentById(TEST_ID);

        assertTrue(result.isPresent());
        assertEquals(TEST_ID, result.get().getId());
    }

    @Test
    @DisplayName("saveAppointment should return saved appointment")
    void saveAppointment_ShouldReturnSavedAppointment() {
        Appointment appt = new Appointment();
        when(appointmentRepository.save(appt)).thenReturn(appt);

        Appointment result = sut.saveAppointment(appt);

        assertNotNull(result);
        verify(appointmentRepository).save(appt);
    }

    @Test
    @DisplayName("updateAppointment should update and return appointment when found")
    void updateAppointment_ShouldUpdateAndReturn_WhenFound() {
        Appointment existing = new Appointment();
        existing.setId(TEST_ID);
        existing.setTitle("Old Title");

        Appointment details = new Appointment();
        details.setTitle("New Title");
        details.setActive(true);

        when(appointmentRepository.findById(TEST_ID)).thenReturn(Optional.of(existing));
        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(i -> i.getArgument(0));

        Appointment result = sut.updateAppointment(TEST_ID, details);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        verify(appointmentRepository).save(existing);
    }

    @Test
    @DisplayName("updateAppointment should return null when not found")
    void updateAppointment_ShouldReturnNull_WhenNotFound() {
        when(appointmentRepository.findById(TEST_ID)).thenReturn(Optional.empty());

        Appointment result = sut.updateAppointment(TEST_ID, new Appointment());

        assertNull(result);
    }

    @Test
    @DisplayName("deleteAppointment should verify deletion")
    void deleteAppointment_ShouldVerifyDeletion() {
        sut.deleteAppointment(TEST_ID);
        verify(appointmentRepository).deleteById(TEST_ID);
    }
}
