package com.sunbeaminfo.entities;

// import com.sunbeaminfo.enums.UserRole;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Enumerated(EnumType.STRING)
    private String role;


    public UserRoleEntity(long id, String role){
        this.id = id;
        this.role = role;
    }

    // Constructors, getters, setters

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthority'");
    }
}
