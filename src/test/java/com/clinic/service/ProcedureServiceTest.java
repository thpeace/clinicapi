package com.clinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clinic.model.Procedure;
import com.clinic.repository.ProcedureRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcedureService Tests")
class ProcedureServiceTest {

    @Mock
    private ProcedureRepository procedureRepository;

    private ProcedureService sut;
    private static final Long TEST_ID = 1L;

    @BeforeEach
    void setUp() {
        sut = new ProcedureService(procedureRepository);
    }

    @Test
    @DisplayName("getAllProcedures should return list")
    void getAllProcedures_ShouldReturnList() {
        when(procedureRepository.findAll()).thenReturn(Collections.singletonList(new Procedure()));
        assertEquals(1, sut.getAllProcedures().size());
    }

    @Test
    @DisplayName("getActiveProcedures should return active list")
    void getActiveProcedures_ShouldReturnActiveList() {
        when(procedureRepository.findByActiveTrue()).thenReturn(Collections.singletonList(new Procedure()));
        assertEquals(1, sut.getActiveProcedures().size());
    }

    @Test
    @DisplayName("getProcedureById should return procedure when found")
    void getProcedureById_ShouldReturnProcedure_WhenFound() {
        when(procedureRepository.findById(TEST_ID)).thenReturn(Optional.of(new Procedure()));
        assertNotNull(sut.getProcedureById(TEST_ID));
    }

    @Test
    @DisplayName("saveProcedure should return saved procedure")
    void saveProcedure_ShouldReturnSavedProcedure() {
        Procedure p = new Procedure();
        when(procedureRepository.save(p)).thenReturn(p);
        assertNotNull(sut.saveProcedure(p));
    }

    @Test
    @DisplayName("updateProcedure should update fields")
    void updateProcedure_ShouldUpdateFields() {
        Procedure existing = new Procedure();
        existing.setIndex(TEST_ID);

        Procedure details = new Procedure();
        details.setNameTh("Updated");

        when(procedureRepository.findById(TEST_ID)).thenReturn(Optional.of(existing));
        when(procedureRepository.save(existing)).thenReturn(existing);

        Procedure updated = sut.updateProcedure(TEST_ID, details);
        assertEquals("Updated", updated.getNameTh());
    }

    @Test
    @DisplayName("updateProcedure should return null if not found")
    void updateProcedure_ShouldReturnNull_IfNotFound() {
        when(procedureRepository.findById(TEST_ID)).thenReturn(Optional.empty());
        assertNull(sut.updateProcedure(TEST_ID, new Procedure()));
    }

    @Test
    @DisplayName("deleteProcedure should verify deletion")
    void deleteProcedure_ShouldVerifyDeletion() {
        sut.deleteProcedure(TEST_ID);
        verify(procedureRepository).deleteById(TEST_ID);
    }
}
