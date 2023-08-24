package com.sunbeaminfo.DTO;

import com.sunbeaminfo.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String password;
    private String status;
    // private User user;

    public RegistrationResponseDTO(User user, String status) {
        this.id = user.getId();
        this.first_name=user.getFirst_name();
        this.last_name=user.getLast_name();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.password = user.getPassword();
        this.status = status;
    }
}
