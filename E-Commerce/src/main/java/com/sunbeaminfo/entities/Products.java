package com.sunbeaminfo.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sunbeaminfo.enums.Category;

@Entity
public class Products extends BaseEntity {
    
    private String productName;

    private double price;   

    private int unitStock;

    private String productDescription;

    private String productImage;

    @Enumerated(EnumType.STRING)
    private Category category;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnitStock() {
        return unitStock;
    }

    public void setUnitStock(int unitStock) {
        this.unitStock = unitStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "products [productName=" + productName + ", price=" + price + ", unitStock=" + unitStock
                + ", productDescription=" + productDescription + ", productImage=" + productImage + ", category="
                + category + "]";
    }

    

}
