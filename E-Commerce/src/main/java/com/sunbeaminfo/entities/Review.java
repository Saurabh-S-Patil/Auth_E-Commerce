package com.sunbeaminfo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Review extends BaseEntity {
    
    private int rating;

    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_product_id") // This is the foreign key column in the Review table
    private OrderedProducts orderedProduct; // Reference to the associated OrderedProducts


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // This is the foreign key column in the Review table
    private User user; // Reference to the associated User

    // Constructors, getters, setters...

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    
    // Constructors, getters, setters...

    public OrderedProducts getOrderedProduct() {
        return orderedProduct;
    }

    public void setOrderedProduct(OrderedProducts orderedProduct) {
        this.orderedProduct = orderedProduct;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
