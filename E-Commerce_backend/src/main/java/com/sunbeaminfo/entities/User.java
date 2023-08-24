package com.sunbeaminfo.entities;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true)
    private String first_name;

    @Column(nullable = true)
    private String last_name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private long mob_no;

    @Column(nullable = true)
    private String gender;

    public User(long id ,String email,Set<UserRoleEntity> role,String password){
        this.id=id;
        this.email = email;
        this.password=password;
        this.roles = role;
    }

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserPaymentDetails> paymentMethodList = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_roles_user")),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_roles_role")))
    private Set<UserRoleEntity> roles = new HashSet<>();


	@ManyToMany
    @JoinTable(
        name = "user_address", // Name of the join table
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> ordersList = new ArrayList<>();


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Review review; // Reference to the associated Review

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserQueries> userQueriesList = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payments> paymentsList = new ArrayList<>();

    public List<Payments> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payments> paymentsList) {
        this.paymentsList = paymentsList;
    }

    public void addPayment(Payments payment) {
        paymentsList.add(payment);
        payment.setUser(this);
    }

    public void removePayment(Payments payment) {
        paymentsList.remove(payment);
        payment.setUser(null);
    }

    public List<UserQueries> getUserQueriesList() {
        return userQueriesList;
    }

    public void setUserQueriesList(List<UserQueries> userQueriesList) {
        this.userQueriesList = userQueriesList;
    }

    public void addUserQuery(UserQueries userQuery) {
        userQueriesList.add(userQuery);
        userQuery.setUser(this);
    }

    public void removeUserQuery(UserQueries userQuery) {
        userQueriesList.remove(userQuery);
        userQuery.setUser(null);
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }


    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public void addOrder(Orders order) {
        ordersList.add(order);
        order.setUser(this);
    }

    public void removeOrder(Orders order) {
        ordersList.remove(order);
        order.setUser(null);
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.getUsers().add(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.getUsers().remove(this);
    }



    // Constructors, getters, setters

	public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.setUser(this);
    }

    
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMob_no() {
        return mob_no;
    }

    public void setMob_no(long mob_no) {
        this.mob_no = mob_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public void addRole(UserRoleEntity role) {
        roles.add(role);
    }

    public void removeRole(UserRoleEntity role) {
        roles.remove(role);
    }

	public List<UserPaymentDetails> getProductsList() {
        return paymentMethodList;
    }

    public void setProductsList(List<UserPaymentDetails> productsList) {
        this.paymentMethodList = productsList;
    }

    public void addProduct(UserPaymentDetails product) {
        paymentMethodList.add(product);
        product.setUser(this);
    }

    public void removeProduct(UserPaymentDetails product) {
        paymentMethodList.remove(product);
        product.setUser(null);
    }

	

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", mob_no=" + mob_no +
                ", gender='" + gender + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
