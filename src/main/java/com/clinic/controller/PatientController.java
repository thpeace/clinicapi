package com.clinic.controller;

import com.clinic.dto.PatientResponse;
import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/import")
    public String importPatients(@RequestBody PatientResponse patientResponse) {
        List<Patient> patients = patientResponse.getData();
        patientRepository.saveAll(patients);
        return "Imported " + patients.size() + " patients successfully!";
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}