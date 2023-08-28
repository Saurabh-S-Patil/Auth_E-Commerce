package com.sunbeaminfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.entities.Products;
import com.sunbeaminfo.enums.Category;


public interface ProductsRepository extends JpaRepository<Products, Long> {

    List<Products> findByCategory(Category category);

}
