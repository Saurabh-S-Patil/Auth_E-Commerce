package com.sunbeaminfo.service;

import java.util.List;
import java.util.Optional;

import com.sunbeaminfo.entities.Products;

public interface ProductsService {
    List<Products> getAllProducts();
    Optional<Products> getProductById(Long id);
    Products createProduct(Products product);
    Products updateProduct(Long id, Products updatedProduct);
    void deleteProduct(Long id);
}

