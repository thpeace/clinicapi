package com.clinic.controller;

import com.clinic.model.Procedure;
import com.clinic.service.ProcedureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedures")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProcedureController {

    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping
    public List<Procedure> getAllProcedures() {
        return procedureService.getAllProcedures();
    }

    @GetMapping("/active")
    public List<Procedure> getActiveProcedures() {
        return procedureService.getActiveProcedures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Procedure> getProcedureById(@PathVariable Long id) {
        Procedure procedure = procedureService.getProcedureById(id);
        if (procedure != null) {
            return ResponseEntity.ok(procedure);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Procedure createProcedure(@RequestBody Procedure procedure) {
        return procedureService.saveProcedure(procedure);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedure> updateProcedure(@PathVariable Long id, @RequestBody Procedure procedureDetails) {
        Procedure updatedProcedure = procedureService.updateProcedure(id, procedureDetails);
        if (updatedProcedure != null) {
            return ResponseEntity.ok(updatedProcedure);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedure(@PathVariable Long id) {
        procedureService.deleteProcedure(id);
        return ResponseEntity.ok().build();
    }
}
