package com.clinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clinic.dto.PatientResponse;
import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/import")
    public String importPatients(@RequestBody PatientResponse patientResponse) {
        List<Patient> patients = patientResponse.getData();

        logger.info("Received {} patients to import", patients.size());

        for (Patient p : patients) {
            logger.debug("Importing patient: {} {}", p.getFname(), p.getLname());
            p.setId(null);
        }

        patientRepository.saveAll(patients);
        logger.info("Successfully imported {} patients", patients.size());
        return "Imported " + patients.size() + " patients successfully!";
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}