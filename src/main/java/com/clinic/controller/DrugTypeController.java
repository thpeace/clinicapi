package com.clinic.controller;

import com.clinic.model.DrugType;
import com.clinic.service.DrugTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drug-types")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DrugTypeController {

    private final DrugTypeService drugTypeService;

    public DrugTypeController(DrugTypeService drugTypeService) {
        this.drugTypeService = drugTypeService;
    }

    @GetMapping
    public List<DrugType> getAllDrugTypes() {
        return drugTypeService.getAllDrugTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugType> getDrugTypeById(@PathVariable @NonNull Long id) {
        DrugType drugType = drugTypeService.getDrugTypeById(id);
        if (drugType != null) {
            return ResponseEntity.ok(drugType);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public @NonNull DrugType createDrugType(@RequestBody @NonNull DrugType drugType) {
        return drugTypeService.saveDrugType(drugType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugType> updateDrugType(@PathVariable @NonNull Long id,
            @RequestBody DrugType drugTypeDetails) {
        DrugType updatedDrugType = drugTypeService.updateDrugType(id, drugTypeDetails);
        if (updatedDrugType != null) {
            return ResponseEntity.ok(updatedDrugType);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrugType(@PathVariable @NonNull Long id) {
        drugTypeService.deleteDrugType(id);
        return ResponseEntity.ok().build();
    }
}
