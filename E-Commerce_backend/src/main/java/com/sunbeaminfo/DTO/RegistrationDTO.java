package com.sunbeaminfo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String email;
    private String first_name;
    private String last_name;
    private String gender;
    private long mob_no; 
    private String password;
    
}
