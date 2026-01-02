package com.clinic.controller;

import com.clinic.model.ServiceGroup;
import com.clinic.service.ServiceGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-groups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServiceGroupController {

    private final ServiceGroupService serviceGroupService;

    public ServiceGroupController(ServiceGroupService serviceGroupService) {
        this.serviceGroupService = serviceGroupService;
    }

    @GetMapping
    public List<ServiceGroup> getAllServiceGroups() {
        return serviceGroupService.getAllServiceGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceGroup> getServiceGroupById(@PathVariable @NonNull Long id) {
        ServiceGroup serviceGroup = serviceGroupService.getServiceGroupById(id);
        if (serviceGroup != null) {
            return ResponseEntity.ok(serviceGroup);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ServiceGroup createServiceGroup(@RequestBody ServiceGroup serviceGroup) {
        return serviceGroupService.saveServiceGroup(serviceGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceGroup> updateServiceGroup(@PathVariable @NonNull Long id,
            @RequestBody ServiceGroup serviceGroupDetails) {
        ServiceGroup updatedServiceGroup = serviceGroupService.updateServiceGroup(id, serviceGroupDetails);
        if (updatedServiceGroup != null) {
            return ResponseEntity.ok(updatedServiceGroup);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceGroup(@PathVariable @NonNull Long id) {
        serviceGroupService.deleteServiceGroup(id);
        return ResponseEntity.ok().build();
    }
}
