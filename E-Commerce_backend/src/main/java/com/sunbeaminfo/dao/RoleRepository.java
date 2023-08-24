package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sunbeaminfo.entities.UserRoleEntity;

import java.util.Optional;



@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Integer>{
    Optional<UserRoleEntity> findByAuthority(String authority);
}