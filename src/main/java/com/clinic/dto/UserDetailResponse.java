package com.clinic.dto;

import java.time.LocalDateTime;

import com.clinic.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailResponse {

    private Long id;
    private String username;
    private String roleName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String country;
    private String postalCode;
    private String province;
    private Boolean enabled;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // Static factory method to create from User entity
    public static UserDetailResponse fromUser(User user) {
        UserDetailResponse response = new UserDetailResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRoleName(user.getRole() != null ? user.getRole().getName() : null);
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setProfilePicture(user.getProfilePicture());
        response.setCountry(user.getCountry());
        response.setPostalCode(user.getPostalCode());
        response.setProvince(user.getProvince());
        response.setEnabled(user.getEnabled());
        response.setCreatedDate(user.getCreatedDate());
        response.setUpdatedDate(user.getUpdatedDate());
        return response;
    }
}
