package com.clinic.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.clinic.model.Appointment;
import com.clinic.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getActiveAppointments() {
        return appointmentRepository.findByActiveTrue();
    }

    public Optional<Appointment> getAppointmentById(@NonNull Long id) {
        return appointmentRepository.findById(id);
    }

    @NonNull
    public Appointment saveAppointment(Appointment appointment) {
        return Objects.requireNonNull(appointmentRepository.save(appointment));
    }

    public Appointment updateAppointment(@NonNull Long id, Appointment appointmentDetails) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setTitle(appointmentDetails.getTitle());
                    appointment.setDescription(appointmentDetails.getDescription());
                    appointment.setStartTime(appointmentDetails.getStartTime());
                    appointment.setEndTime(appointmentDetails.getEndTime());
                    appointment.setUrgency(appointmentDetails.getUrgency());
                    appointment.setActive(appointmentDetails.getActive());
                    return Objects.requireNonNull(appointmentRepository.save(appointment));
                })
                .orElse(null);
    }

    public void deleteAppointment(@NonNull Long id) {
        appointmentRepository.deleteById(id);
    }
}
