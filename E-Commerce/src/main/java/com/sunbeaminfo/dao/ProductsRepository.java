package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.entities.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    // Add custom query methods if needed
}
