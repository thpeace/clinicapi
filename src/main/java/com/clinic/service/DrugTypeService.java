package com.clinic.service;

import com.clinic.model.DrugType;
import com.clinic.repository.DrugTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugTypeService {

    private final DrugTypeRepository drugTypeRepository;

    public DrugTypeService(DrugTypeRepository drugTypeRepository) {
        this.drugTypeRepository = drugTypeRepository;
    }

    public List<DrugType> getAllDrugTypes() {
        return drugTypeRepository.findAll();
    }

    public List<DrugType> getActiveDrugTypes() {
        return drugTypeRepository.findByActiveTrue();
    }

    public DrugType getDrugTypeById(Long id) {
        return drugTypeRepository.findById(id).orElse(null);
    }

    public DrugType saveDrugType(DrugType drugType) {
        return drugTypeRepository.save(drugType);
    }

    public DrugType updateDrugType(Long id, DrugType drugTypeDetails) {
        DrugType drugType = drugTypeRepository.findById(id).orElse(null);
        if (drugType != null) {
            drugType.setNameTh(drugTypeDetails.getNameTh());
            drugType.setNameEn(drugTypeDetails.getNameEn());
            drugType.setActive(drugTypeDetails.getActive());
            // createdDate should not be updated manually
            return drugTypeRepository.save(drugType);
        }
        return null;
    }

    public void deleteDrugType(Long id) {
        drugTypeRepository.deleteById(id);
    }
}
