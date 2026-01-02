package com.clinic.service;

import com.clinic.model.Procedure;
import com.clinic.repository.ProcedureRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureService {

    private final ProcedureRepository procedureRepository;

    public ProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    public List<Procedure> getActiveProcedures() {
        return procedureRepository.findByActiveTrue();
    }

    public Procedure getProcedureById(@NonNull Long id) {
        return procedureRepository.findById(id).orElse(null);
    }

    @NonNull
    public Procedure saveProcedure(@NonNull Procedure procedure) {
        return procedureRepository.save(procedure);
    }

    public Procedure updateProcedure(@NonNull Long id, Procedure procedureDetails) {
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

    public void deleteProcedure(@NonNull Long id) {
        procedureRepository.deleteById(id);
    }
}
