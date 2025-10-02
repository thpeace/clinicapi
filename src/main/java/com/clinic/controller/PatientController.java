package com.clinic.controller;

// import com.clinic.dto.PatientResponse;
import com.clinic.model.Patient;
import com.clinic.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // @GetMapping
    // public PatientResponse getPatients() {
    // List<Patient> patients = patientService.getAllPatients();
    // return new PatientResponse("200", patients);
    // }

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }
}