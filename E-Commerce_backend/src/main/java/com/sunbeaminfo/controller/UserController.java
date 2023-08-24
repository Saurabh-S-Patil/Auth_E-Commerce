package com.sunbeaminfo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.UserDTO;
import com.sunbeaminfo.dao.CartRepository;
import com.sunbeaminfo.entities.Cart;
import com.sunbeaminfo.entities.User;
import com.sunbeaminfo.service.CartService;
import com.sunbeaminfo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService customerService; 
    
    private final CartRepository cartRepository;

     private final CartService cartService;

    public UserController(UserService customerService,CartRepository cartRepository,CartService cartService) {
        this.customerService = customerService;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

//    @PostMapping("/signup")
//    public ResponseEntity<String> createCustomer(@RequestBody User customer) {
//        if (customer.getFirst_name() != null && customer.getLast_name() != null) {
//            User createdCustomer = customerService.createUser(customer);
//            CartDTO cart = new CartDTO();
//            cart.setUserId(createdCustomer.getId()); 
//         //    cartService.createCart(cart);
//             cartRepository.save(cart);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created with ID: " + createdCustomer.getId());
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication required or First Name or Last Name is missing");
//        }
//    }

@PostMapping("/signup")
public ResponseEntity<String> createCustomer(@RequestBody User customer) {
    if (customer.getFirst_name() != null && customer.getLast_name() != null) {
        User createdCustomer = customerService.createUser(customer);
        CartDTO cart = new CartDTO();
        cart.setUserId(createdCustomer.getId()); 
           cartService.createCart(cart);  // You can remove this line since you're directly saving the cart
        // cartRepository.save(cart); // Uncomment this line to save the cart
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created with ID: " + createdCustomer.getId());
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication required or First Name or Last Name is missing");
    }
}


    @GetMapping("/get")
    public ResponseEntity<List<UserDTO>> getCustomerList() {
    List<User> users = customerService.getAllUsers();
    List<UserDTO> userDTOs = new ArrayList<>();
    for (User user : users) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirst_name(user.getFirst_name());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());


        // Set other fields...
        userDTOs.add(userDTO);
    }
    return ResponseEntity.ok(userDTOs);
}


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam("uuid") Long id) {
            boolean deleted = customerService.deleteUser(id);
            if (deleted) {
                return ResponseEntity.ok("Successfully deleted customer with ID: " + id);
            } else {
                return ResponseEntity.badRequest().body("Customer not found with ID: " + id);
            }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestParam("uuid") Long id, @RequestBody User customer) {
            customer.setId(id);
            boolean updated = customerService.updateUser(id, customer);
            if (updated) {
                return ResponseEntity.ok("Successfully updated customer with ID: " + id);
            } else {
                return ResponseEntity.badRequest().body("Customer not found with ID: " + id);
            }
    }
}
