package com.clinic.controller;

import com.clinic.dto.request.TreatmentImportRequest;
import com.clinic.model.Treatment;
import com.clinic.service.TreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/active")
    public List<Treatment> getActiveTreatments() {
        return treatmentService.getActiveTreatments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) {
        Treatment treatment = treatmentService.getTreatmentById(id);
        if (treatment != null) {
            return ResponseEntity.ok(treatment);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/tid/{tid}")
    public ResponseEntity<Treatment> getTreatmentByTid(@PathVariable String tid) {
        Treatment treatment = treatmentService.getTreatmentByTid(tid);
        if (treatment != null) {
            return ResponseEntity.ok(treatment);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Treatment createTreatment(@RequestBody Treatment treatment) {
        return treatmentService.saveTreatment(treatment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatmentDetails) {
        Treatment updatedTreatment = treatmentService.updateTreatment(id, treatmentDetails);
        if (updatedTreatment != null) {
            return ResponseEntity.ok(updatedTreatment);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/import")
    public ResponseEntity<List<Treatment>> importTreatments(@RequestBody TreatmentImportRequest request) {
        List<Treatment> importedTreatments = treatmentService.importTreatments(request.getData());
        return ResponseEntity.ok(importedTreatments);
    }
}
