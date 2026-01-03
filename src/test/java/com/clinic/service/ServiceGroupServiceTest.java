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

import com.clinic.model.ServiceGroup;
import com.clinic.repository.ServiceGroupRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("ServiceGroupService Tests")
class ServiceGroupServiceTest {

    @Mock
    private ServiceGroupRepository serviceGroupRepository;

    private ServiceGroupService sut;
    private static final Long TEST_ID = 1L;

    @BeforeEach
    void setUp() {
        sut = new ServiceGroupService(serviceGroupRepository);
    }

    @Test
    @DisplayName("getAllServiceGroups should return list")
    void getAllServiceGroups_ShouldReturnList() {
        when(serviceGroupRepository.findAll()).thenReturn(Collections.singletonList(new ServiceGroup()));
        assertEquals(1, sut.getAllServiceGroups().size());
    }

    @Test
    @DisplayName("getActiveServiceGroups should return active list")
    void getActiveServiceGroups_ShouldReturnActiveList() {
        when(serviceGroupRepository.findByActiveTrue()).thenReturn(Collections.singletonList(new ServiceGroup()));
        assertEquals(1, sut.getActiveServiceGroups().size());
    }

    @Test
    @DisplayName("getServiceGroupById should return group when found")
    void getServiceGroupById_ShouldReturnGroup_WhenFound() {
        when(serviceGroupRepository.findById(TEST_ID)).thenReturn(Optional.of(new ServiceGroup()));
        assertNotNull(sut.getServiceGroupById(TEST_ID));
    }

    @Test
    @DisplayName("saveServiceGroup should return saved group")
    void saveServiceGroup_ShouldReturnSavedGroup() {
        ServiceGroup s = new ServiceGroup();
        when(serviceGroupRepository.save(s)).thenReturn(s);
        assertNotNull(sut.saveServiceGroup(s));
    }

    @Test
    @DisplayName("updateServiceGroup should update fields")
    void updateServiceGroup_ShouldUpdateFields() {
        ServiceGroup existing = new ServiceGroup();
        existing.setId(TEST_ID);

        ServiceGroup details = new ServiceGroup();
        details.setNameTh("Updated");

        when(serviceGroupRepository.findById(TEST_ID)).thenReturn(Optional.of(existing));
        when(serviceGroupRepository.save(existing)).thenReturn(existing);

        ServiceGroup updated = sut.updateServiceGroup(TEST_ID, details);
        assertEquals("Updated", updated.getNameTh());
    }

    @Test
    @DisplayName("updateServiceGroup should return null if not found")
    void updateServiceGroup_ShouldReturnNull_IfNotFound() {
        when(serviceGroupRepository.findById(TEST_ID)).thenReturn(Optional.empty());
        assertNull(sut.updateServiceGroup(TEST_ID, new ServiceGroup()));
    }

    @Test
    @DisplayName("deleteServiceGroup should verify deletion")
    void deleteServiceGroup_ShouldVerifyDeletion() {
        sut.deleteServiceGroup(TEST_ID);
        verify(serviceGroupRepository).deleteById(TEST_ID);
    }
}
