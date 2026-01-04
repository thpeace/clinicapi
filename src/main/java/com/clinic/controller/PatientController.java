package com.clinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clinic.dto.response.PatientResponse;
import com.clinic.model.Patient;
import com.clinic.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/patients")
@Tag(name = "Patients", description = "Patient management endpoints")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/import")
    public String importPatients(@RequestBody PatientResponse patientResponse) {
        List<Patient> patients = patientResponse.getData();

        logger.info("Received {} patients to import", patients.size());

        for (Patient p : patients) {
            logger.debug("Importing patient: {} {}", p.getFname(), p.getLname());
            p.setId(null);
            patientService.savePatient(p);
        }

        logger.info("Successfully imported {} patients", patients.size());
        return "Imported " + patients.size() + " patients successfully!";
    }

    private static final Set<Integer> ALLOWED_PAGE_SIZES = Set.of(10, 20, 50, 100, 500);

    @GetMapping
    public Page<Patient> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Request received: GET /api/patients with page={}, size={}", page, size);

        // Validate size - default to 10 if not in allowed list
        if (!ALLOWED_PAGE_SIZES.contains(size)) {
            logger.warn("Invalid page size {} requested, defaulting to 10", size);
            size = 10;
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients = patientService.getAllPatients(pageable);
        logger.info("Returning {} patients (page {} of {})",
                patients.getNumberOfElements(), page, patients.getTotalPages());
        return patients;
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalPatients() {
        logger.info("Request received: GET /api/patients/total");
        long count = patientService.getTotalPatients();
        logger.info("Total patients count: {}", count);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/total/today")
    public ResponseEntity<Long> getTotalPatientsToday() {
        logger.info("Request received: GET /api/patients/total/today");
        long count = patientService.getTotalPatientsToday();
        logger.info("Total patients created today: {}", count);
        return ResponseEntity.ok(count);
    }
}