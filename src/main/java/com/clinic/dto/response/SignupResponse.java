package com.clinic.dto.response;

import com.clinic.model.RoleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Builder-style static method
    public static SignupResponse of(Long id, String username, RoleEntity role, String firstName, String lastName,
            String email, String phoneNumber, String country, String postalCode, String province) {
        return new SignupResponse(id, username, role.getName(), firstName, lastName, email, phoneNumber,
                country, postalCode, province, "User registered successfully");
    }
}
