package com.clinic.service;

import com.clinic.model.ServiceGroup;
import com.clinic.repository.ServiceGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceGroupService {

    private final ServiceGroupRepository serviceGroupRepository;

    public ServiceGroupService(ServiceGroupRepository serviceGroupRepository) {
        this.serviceGroupRepository = serviceGroupRepository;
    }

    public List<ServiceGroup> getAllServiceGroups() {
        return serviceGroupRepository.findAll();
    }

    public List<ServiceGroup> getActiveServiceGroups() {
        return serviceGroupRepository.findByActiveTrue();
    }

    public ServiceGroup getServiceGroupById(Long id) {
        return serviceGroupRepository.findById(id).orElse(null);
    }

    public ServiceGroup saveServiceGroup(ServiceGroup serviceGroup) {
        return serviceGroupRepository.save(serviceGroup);
    }

    public ServiceGroup updateServiceGroup(Long id, ServiceGroup serviceGroupDetails) {
        ServiceGroup serviceGroup = serviceGroupRepository.findById(id).orElse(null);
        if (serviceGroup != null) {
            serviceGroup.setNameTh(serviceGroupDetails.getNameTh());
            serviceGroup.setNameEn(serviceGroupDetails.getNameEn());
            serviceGroup.setActive(serviceGroupDetails.getActive());
            // createdDate should not be updated manually
            return serviceGroupRepository.save(serviceGroup);
        }
        return null;
    }

    public void deleteServiceGroup(Long id) {
        serviceGroupRepository.deleteById(id);
    }
}
