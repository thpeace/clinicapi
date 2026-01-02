package com.clinic.service;

import com.clinic.dto.TreatmentImportDto;
import com.clinic.model.Treatment;
import com.clinic.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public List<Treatment> getActiveTreatments() {
        return treatmentRepository.findByStatus("Y");
    }

    public Treatment getTreatmentById(Long id) {
        return treatmentRepository.findById(id).orElse(null);
    }

    public Treatment getTreatmentByTid(String tid) {
        return treatmentRepository.findByTid(tid).orElse(null);
    }

    public Treatment saveTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Treatment updateTreatment(Long id, Treatment treatmentDetails) {
        Treatment treatment = treatmentRepository.findById(id).orElse(null);
        if (treatment != null) {
            treatment.setTid(treatmentDetails.getTid());
            treatment.setBcode(treatmentDetails.getBcode());
            treatment.setTname(treatmentDetails.getTname());
            treatment.setPrice(treatmentDetails.getPrice());
            treatment.setPrice1(treatmentDetails.getPrice1());
            treatment.setPrice2(treatmentDetails.getPrice2());
            treatment.setPrice3(treatmentDetails.getPrice3());
            treatment.setPrice4(treatmentDetails.getPrice4());
            treatment.setTyp(treatmentDetails.getTyp());
            treatment.setScoreD(treatmentDetails.getScoreD());
            treatment.setScoreDtyp(treatmentDetails.getScoreDtyp());
            treatment.setScore(treatmentDetails.getScore());
            treatment.setScoreTyp(treatmentDetails.getScoreTyp());
            treatment.setCosts(treatmentDetails.getCosts());
            treatment.setQty(treatmentDetails.getQty());
            treatment.setUnit(treatmentDetails.getUnit());
            treatment.setVat(treatmentDetails.getVat());
            treatment.setDtyp(treatmentDetails.getDtyp());
            treatment.setDc(treatmentDetails.getDc());
            treatment.setDu(treatmentDetails.getDu());
            treatment.setEc(treatmentDetails.getEc());
            treatment.setEu(treatmentDetails.getEu());
            treatment.setTgroup(treatmentDetails.getTgroup());
            treatment.setStatus(treatmentDetails.getStatus());
            treatment.setTtyp(treatmentDetails.getTtyp());
            treatment.setAlate(treatmentDetails.getAlate());
            treatment.setLv(treatmentDetails.getLv());
            treatment.setN1(treatmentDetails.getN1());
            treatment.setN2(treatmentDetails.getN2());
            return treatmentRepository.save(treatment);
        }
        return null;
    }

    public void deleteTreatment(Long id) {
        treatmentRepository.deleteById(id);
    }

    public List<Treatment> importTreatments(List<TreatmentImportDto> importDtos) {
        List<Treatment> treatments = new ArrayList<>();

        for (TreatmentImportDto dto : importDtos) {
            // Check if treatment with same tid already exists
            Treatment existingTreatment = treatmentRepository.findByTid(dto.getTid()).orElse(null);

            if (existingTreatment == null) {
                Treatment treatment = Treatment.builder()
                        .tid(dto.getTid())
                        .bcode(dto.getBcode())
                        .tname(dto.getTname())
                        .price(parseBigDecimal(dto.getPrice()))
                        .price1(parseBigDecimal(dto.getPrice1()))
                        .price2(parseBigDecimal(dto.getPrice2()))
                        .price3(parseBigDecimal(dto.getPrice3()))
                        .price4(parseBigDecimal(dto.getPrice4()))
                        .typ(dto.getTyp())
                        .scoreD(dto.getScore_d())
                        .scoreDtyp(dto.getScore_dtyp())
                        .score(dto.getScore())
                        .scoreTyp(dto.getScore_typ())
                        .costs(parseBigDecimal(dto.getCosts()))
                        .qty(parseInteger(dto.getQty()))
                        .unit(dto.getUnit())
                        .vat(dto.getVat())
                        .dtyp(dto.getDtyp())
                        .dc(dto.getDc())
                        .du(dto.getDu())
                        .ec(dto.getEc())
                        .eu(dto.getEu())
                        .tgroup(dto.getTgroup())
                        .status(dto.getStatus())
                        .ttyp(dto.getTtyp())
                        .alate(dto.getAlate())
                        .lv(dto.getLv())
                        .n1(dto.getN1())
                        .n2(dto.getN2())
                        .build();
                treatments.add(treatment);
            }
        }

        return treatmentRepository.saveAll(treatments);
    }

    private BigDecimal parseBigDecimal(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer parseInteger(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
