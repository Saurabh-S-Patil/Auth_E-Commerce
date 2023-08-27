package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.entities.CartProducts;

public interface CartProductRepository extends JpaRepository<CartProducts, Long> {
    
}
