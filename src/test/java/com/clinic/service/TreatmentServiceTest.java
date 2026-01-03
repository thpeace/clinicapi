package com.clinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clinic.dto.request.TreatmentImportDto;
import com.clinic.model.Treatment;
import com.clinic.repository.TreatmentRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("TreatmentService Tests")
class TreatmentServiceTest {

    @Mock
    private TreatmentRepository treatmentRepository;

    private TreatmentService sut;
    private static final Long TEST_ID = 1L;
    private static final String TEST_TID = "T001";

    @BeforeEach
    void setUp() {
        sut = new TreatmentService(treatmentRepository);
    }

    @Test
    @DisplayName("getAllTreatments should return list")
    void getAllTreatments_ShouldReturnList() {
        when(treatmentRepository.findAll()).thenReturn(Collections.singletonList(new Treatment()));
        assertEquals(1, sut.getAllTreatments().size());
    }

    @Test
    @DisplayName("getActiveTreatments should return active list")
    void getActiveTreatments_ShouldReturnActiveList() {
        when(treatmentRepository.findByStatus("Y")).thenReturn(Collections.singletonList(new Treatment()));
        assertEquals(1, sut.getActiveTreatments().size());
    }

    @Test
    @DisplayName("getTreatmentById should return treatment")
    void getTreatmentById_ShouldReturnTreatment() {
        when(treatmentRepository.findById(TEST_ID)).thenReturn(Optional.of(new Treatment()));
        assertNotNull(sut.getTreatmentById(TEST_ID));
    }

    @Test
    @DisplayName("getTreatmentByTid should return treatment")
    void getTreatmentByTid_ShouldReturnTreatment() {
        when(treatmentRepository.findByTid(TEST_TID)).thenReturn(Optional.of(new Treatment()));
        assertNotNull(sut.getTreatmentByTid(TEST_TID));
    }

    @Test
    @DisplayName("saveTreatment should return saved treatment")
    void saveTreatment_ShouldReturnSavedTreatment() {
        Treatment t = new Treatment();
        when(treatmentRepository.save(t)).thenReturn(t);
        assertNotNull(sut.saveTreatment(t));
    }

    @Test
    @DisplayName("updateTreatment should update fields")
    void updateTreatment_ShouldUpdateFields() {
        Treatment existing = new Treatment();
        existing.setId(TEST_ID);

        Treatment details = new Treatment();
        details.setTname("Updated Name");

        when(treatmentRepository.findById(TEST_ID)).thenReturn(Optional.of(existing));
        when(treatmentRepository.save(existing)).thenReturn(existing);

        Treatment updated = sut.updateTreatment(TEST_ID, details);

        assertEquals("Updated Name", updated.getTname());
        verify(treatmentRepository).save(existing);
    }

    @Test
    @DisplayName("updateTreatment should return null if not found")
    void updateTreatment_ShouldReturnNull_IfNotFound() {
        when(treatmentRepository.findById(TEST_ID)).thenReturn(Optional.empty());
        assertNull(sut.updateTreatment(TEST_ID, new Treatment()));
    }

    @Test
    @DisplayName("importTreatments should save new treatments")
    void importTreatments_ShouldSaveNewTreatments() {
        TreatmentImportDto dto = new TreatmentImportDto();
        dto.setTid("NEW001");
        dto.setTname("New Treatment");
        dto.setPrice("100.00");

        when(treatmentRepository.findByTid("NEW001")).thenReturn(Optional.empty());
        when(treatmentRepository.saveAll(anyList())).thenAnswer(i -> i.getArgument(0));

        List<Treatment> result = sut.importTreatments(Arrays.asList(dto));

        assertEquals(1, result.size());
        assertEquals("NEW001", result.get(0).getTid());
    }

    @Test
    @DisplayName("importTreatments should skip existing treatments")
    void importTreatments_ShouldSkipExistingTreatments() {
        TreatmentImportDto dto = new TreatmentImportDto();
        dto.setTid("EXIST001");

        when(treatmentRepository.findByTid("EXIST001")).thenReturn(Optional.of(new Treatment()));
        when(treatmentRepository.saveAll(anyList())).thenAnswer(i -> i.getArgument(0));

        List<Treatment> result = sut.importTreatments(Arrays.asList(dto));

        assertEquals(0, result.size());
    }
}
