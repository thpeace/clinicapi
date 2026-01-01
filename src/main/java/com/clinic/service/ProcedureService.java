package com.clinic.service;

import com.clinic.model.Procedure;
import com.clinic.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {

    @Autowired
    private ProcedureRepository procedureRepository;

    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    public List<Procedure> getActiveProcedures() {
        return procedureRepository.findByActiveTrue();
    }

    public Procedure getProcedureById(Long id) {
        return procedureRepository.findById(id).orElse(null);
    }

    public Procedure saveProcedure(Procedure procedure) {
        return procedureRepository.save(procedure);
    }

    public Procedure updateProcedure(Long id, Procedure procedureDetails) {
        Procedure procedure = procedureRepository.findById(id).orElse(null);
        if (procedure != null) {
            procedure.setNameTh(procedureDetails.getNameTh());
            procedure.setNameEn(procedureDetails.getNameEn());
            procedure.setUnit(procedureDetails.getUnit());
            procedure.setActive(procedureDetails.getActive());
            // createdDate should not be updated manually
            return procedureRepository.save(procedure);
        }
        return null;
    }

    public void deleteProcedure(Long id) {
        procedureRepository.deleteById(id);
    }
}
