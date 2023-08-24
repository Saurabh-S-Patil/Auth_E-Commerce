package com.sunbeaminfo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orders extends BaseEntity {
    

    private Date orderDate;

    private int paymentMethodId;



    private double orderTotal;

    private boolean orderStatus;

    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedProducts> orderedProductsList = new ArrayList<>();


    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
    private UserQueries userQueries; // Reference to the associated UserQueries


    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payments payments; // Reference to the associated Payments

    // Constructors, getters, setters...

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }
    // Constructors, getters, setters...

    public UserQueries getUserQueries() {
        return userQueries;
    }

    public void setUserQueries(UserQueries userQueries) {
        this.userQueries = userQueries;
    }


    public Date getOrderDate() {
        return orderDate;
    }



    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }



    public int getPaymentMethodId() {
        return paymentMethodId;
    }



    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }



    public double getOrderTotal() {
        return orderTotal;
    }



    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }



    public boolean isOrderStatus() {
        return orderStatus;
    }



    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }



    public User getUser() {
        return user;
    }



    public void setUser(User user) {
        this.user = user;
    }



    public Address getAddress() {
        return address;
    }



    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderedProducts> getOrderedProductsList() {
        return orderedProductsList;
    }

    public void setOrderedProductsList(List<OrderedProducts> orderedProductsList) {
        this.orderedProductsList = orderedProductsList;
    }

    public void addOrderedProduct(OrderedProducts orderedProduct) {
        orderedProductsList.add(orderedProduct);
        orderedProduct.setOrder(this);
    }

    public void removeOrderedProduct(OrderedProducts orderedProduct) {
        orderedProductsList.remove(orderedProduct);
        orderedProduct.setOrder(null);
    }
    
}
