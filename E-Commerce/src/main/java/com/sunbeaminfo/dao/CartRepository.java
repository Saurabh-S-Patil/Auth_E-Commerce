package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.entities.Cart;

// @Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}