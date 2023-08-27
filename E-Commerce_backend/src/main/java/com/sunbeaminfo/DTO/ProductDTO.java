package com.sunbeaminfo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    // private Long id;
    // private int quantity;
    // private String color;
    

    // // Getters and setters

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public int getQuantity() {
    //     return quantity;
    // }

    // public void setQuantity(int quantity) {
    //     this.quantity = quantity;
    // }

    // public String getColor() {
    //     return color;
    // }

    // public void setColor(String color) {
    //     this.color = color;
    // }


    private Long id;

    private String productName;

    private double price;   

    private int unitStock;

    
    private String productDescription;

    private String productImage;

    private String company;
}
