package com.clinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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

import com.clinic.model.DrugType;
import com.clinic.repository.DrugTypeRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("DrugTypeService Tests")
class DrugTypeServiceTest {

    @Mock
    private DrugTypeRepository drugTypeRepository;

    private DrugTypeService sut;
    private static final Long TEST_ID = 1L;

    @BeforeEach
    void setUp() {
        sut = new DrugTypeService(drugTypeRepository);
    }

    @Test
    @DisplayName("getAllDrugTypes should return list")
    void getAllDrugTypes_ShouldReturnList() {
        when(drugTypeRepository.findAll()).thenReturn(Collections.singletonList(new DrugType()));
        assertEquals(1, sut.getAllDrugTypes().size());
    }

    @Test
    @DisplayName("getActiveDrugTypes should return active list")
    void getActiveDrugTypes_ShouldReturnActiveList() {
        when(drugTypeRepository.findByActiveTrue()).thenReturn(Collections.singletonList(new DrugType()));
        assertEquals(1, sut.getActiveDrugTypes().size());
    }

    @Test
    @DisplayName("getDrugTypeById should return type when found")
    void getDrugTypeById_ShouldReturnType_WhenFound() {
        when(drugTypeRepository.findById(TEST_ID)).thenReturn(Optional.of(new DrugType()));
        assertNotNull(sut.getDrugTypeById(TEST_ID));
    }

    @Test
    @DisplayName("saveDrugType should return saved type")
    void saveDrugType_ShouldReturnSavedType() {
        DrugType dt = new DrugType();
        when(drugTypeRepository.save(dt)).thenReturn(dt);
        assertNotNull(sut.saveDrugType(dt));
    }

    @Test
    @DisplayName("updateDrugType should update fields")
    void updateDrugType_ShouldUpdateFields() {
        DrugType existing = new DrugType();
        existing.setId(TEST_ID);

        DrugType details = new DrugType();
        details.setNameTh("Updated");

        when(drugTypeRepository.findById(TEST_ID)).thenReturn(Optional.of(existing));
        when(drugTypeRepository.save(existing)).thenReturn(existing);

        DrugType updated = sut.updateDrugType(TEST_ID, details);
        assertEquals("Updated", updated.getNameTh());
    }

    @Test
    @DisplayName("updateDrugType should return null if not found")
    void updateDrugType_ShouldReturnNull_IfNotFound() {
        when(drugTypeRepository.findById(TEST_ID)).thenReturn(Optional.empty());
        assertNull(sut.updateDrugType(TEST_ID, new DrugType()));
    }

    @Test
    @DisplayName("deleteDrugType should verify deletion")
    void deleteDrugType_ShouldVerifyDeletion() {
        sut.deleteDrugType(TEST_ID);
        verify(drugTypeRepository).deleteById(TEST_ID);
    }
}
