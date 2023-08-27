package com.sunbeaminfo.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart extends BaseEntity {
    
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    // @OneToMany(mappedBy = "Cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // private Set<CartProducts> productsList = new HashSet<>();

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CartProducts> productsList = new HashSet<>();

    private double totalAmmount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartProducts> getProductsList() {
        return productsList;
    }

    public void setProductsList(Set<CartProducts> productsList) {
        this.productsList = productsList;
    }

    // public List<CartProducts> getProductsList() {
    //     return productsList;
    // }

    // public void setProductsList(List<CartProducts> productsList) {
    //     this.productsList = productsList;
    // }

    public void addProduct(CartProducts product) {
        productsList.add(product);
    }

    public void removeProduct(CartProducts product) {
        productsList.remove(product);
    }
}
