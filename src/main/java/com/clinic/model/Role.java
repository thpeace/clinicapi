package com.clinic.model;

public enum Role {
    ADMIN("Administrator with full access"),
    DOCTOR("Medical doctor"),
    NURSE("Nursing staff"),
    RECEPTIONIST("Front desk receptionist"),
    PHARMACIST("Pharmacy staff"),
    LAB_TECHNICIAN("Laboratory technician"),
    USER("Standard user");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
