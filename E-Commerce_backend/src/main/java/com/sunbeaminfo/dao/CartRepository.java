package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeaminfo.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
}