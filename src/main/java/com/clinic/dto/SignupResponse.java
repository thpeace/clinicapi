package com.clinic.dto;

import com.clinic.entity.RoleEntity;

public class SignupResponse {

    private Long id;
    private String username;
    private String roleName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String country;
    private String postalCode;
    private String province;
    private String message;

    // Default constructor
    public SignupResponse() {
    }

    // Constructor with all fields
    public SignupResponse(Long id, String username, String roleName, String firstName, String lastName,
            String email, String phoneNumber, String country, String postalCode, String province, String message) {
        this.id = id;
        this.username = username;
        this.roleName = roleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.postalCode = postalCode;
        this.province = province;
        this.message = message;
    }

    // Builder-style static method
    public static SignupResponse of(Long id, String username, RoleEntity role, String firstName, String lastName,
            String email, String phoneNumber, String country, String postalCode, String province) {
        return new SignupResponse(id, username, role.getName(), firstName, lastName, email, phoneNumber,
                country, postalCode, province, "User registered successfully");
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
