package com.sunbeaminfo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Address extends BaseEntity {

    private String house_no;

    private String landmark;

    private String city;

    private int pincode;

    private String country;

    @ManyToMany(mappedBy = "addresses")
    private Set<User> users = new HashSet<>();


    @OneToOne(mappedBy = "address")
    private Orders order;



    public Address(String house_no, String landmark, String city, int pincode, String country) {
        this.house_no = house_no;
        this.landmark = landmark;
        this.city = city;
        this.pincode = pincode;
        this.country = country;
    }

    public Address() {
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
        user.getAddresses().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getAddresses().remove(this);
    }

    @Override
    public String toString() {
        return "Address [house_no=" + house_no + ", landmark=" + landmark + ", city=" + city + ", pincode=" + pincode
                + ", country=" + country + "]";
    }

    

    
    
}
