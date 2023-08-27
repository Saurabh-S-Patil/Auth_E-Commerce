package com.sunbeaminfo.DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sunbeaminfo.entities.CartProducts;
import com.sunbeaminfo.entities.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartDTO {

    private Long id;
    private int quantity;
    private String color;
    private Long userId;
    private Set<CartProducts> productsList = new HashSet<>();

    private Set<ProductDTO> products;

    public Set<CartProducts> getProductsList() {
        return productsList;
    }

    public void setProductsList(Set<CartProducts> productsList) {
        this.productsList = productsList;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Constructors, getters, setters
}
