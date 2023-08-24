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
    private String authority;


    public UserRoleEntity(long id, String authority){
        this.id = id;
        this.authority = authority;
    }

    // Constructors, getters, setters

    public Long getId() {
        return id;
    }


    public void setId(long i) {
        this.id = i;
    }

    public String getRole() {
        return authority;
    }

    public void setRole(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
