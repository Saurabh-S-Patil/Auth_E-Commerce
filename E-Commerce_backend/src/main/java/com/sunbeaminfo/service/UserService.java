package com.sunbeaminfo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sunbeaminfo.entities.User;
@Service
//CustomerService.java
public interface UserService {
 User createUser(User customer);
 List<User> getAllUsers();
 User getUserById(Long id);
 boolean deleteUser(Long id);
 boolean updateUser(Long id, User customer);
}

