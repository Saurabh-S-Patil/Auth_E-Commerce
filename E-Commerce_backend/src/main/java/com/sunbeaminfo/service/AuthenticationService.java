package com.sunbeaminfo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeaminfo.entities.User;
import com.sunbeaminfo.DTO.CartDTO;
import com.sunbeaminfo.DTO.LoginResponceDTO;
import com.sunbeaminfo.DTO.RegistrationDTO;
import com.sunbeaminfo.DTO.RegistrationResponseDTO;
import com.sunbeaminfo.entities.UserRoleEntity;
import com.sunbeaminfo.dao.CartRepository;
// import com.sunbeaminfo.dao.RoleRepository;
import com.sunbeaminfo.dao.UserRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    // @Autowired
    // private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public RegistrationResponseDTO registerUser(RegistrationDTO user){
        String email = user.getEmail();
        String first_name= user.getFirst_name();
        String last_name = user.getLast_name();
        String gender=user.getGender();
        long mob_no=user.getMob_no();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        //UserRoleEntity userRole = roleRepository.findByAuthority("USER").get();
        UserRoleEntity userRole1 = new UserRoleEntity();
        userRole1.setAuthority("USER");
        userRole1.setId(1);
        Set<UserRoleEntity> authorities = new HashSet<>();

        authorities.add(userRole1);

       // return userRepository.save(new User(0, email, authorities, encodedPassword));
        User savedUser = userRepository.save(new User(null, first_name, last_name, email, encodedPassword, mob_no, gender, null, null, authorities, null, null, null, null, null));
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(savedUser.getId());
        cartService.createCart(cartDTO);

        return new RegistrationResponseDTO(savedUser,"success");
    }

    public LoginResponceDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponceDTO(userRepository.findByEmail(username).get(), token,"success");

        } catch(AuthenticationException e){
            return new LoginResponceDTO(null, "","fail");
        }
    }

}
