package com.sunbeaminfo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sunbeaminfo.dao.UserRepository;
import com.sunbeaminfo.entities.User;
import com.sunbeaminfo.entities.UserRoleEntity;

//UserServiceImpl.java
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

 private final UserRepository userRepository;

 public UserServiceImpl(UserRepository userRepository) {
     this.userRepository = userRepository;
 }

 @Override
 public User createUser(User user) {
     return userRepository.save(user);
 }

 @Override
 public List<User> getAllUsers() {
     return userRepository.findAll();
 }

 @Override
 public User getUserById(Long id) {
     return userRepository.findById(id).orElse(null);
 }

 @Override
 public boolean deleteUser(Long id) {
     if (userRepository.existsById(id)) {
         userRepository.deleteById(id);
         return true;
     }
     return false;
 }

 @Override
 public boolean updateUser(Long id, User user) {
     if (userRepository.existsById(id)) {
         user.setId(id);
         userRepository.save(user);
         return true;
     }
     return false;
 }

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if(!username.equals("saurabh")) throw new UsernameNotFoundException("not saurabh");

    Set<UserRoleEntity> roles = new HashSet<>();
    roles.add(new UserRoleEntity(1, "USER"));

    return new User( 1 , username, roles, encoder.encode("password"));
}

}