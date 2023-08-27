package com.sunbeaminfo;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sunbeaminfo.dao.RoleRepository;
import com.sunbeaminfo.dao.UserRepository;
import com.sunbeaminfo.entities.User;
import com.sunbeaminfo.entities.UserRoleEntity;

@SpringBootApplication // => consists of @Configuration+.....
//=> You can add bean configs here
public class Day14LabApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day14LabApplication.class, args);
	}

	@Bean // => a method level anno to tell SC , this method rets an
	// obj to be managed as spring bean by SC --configure ModelMapper as a spring
	// bean
	public ModelMapper modelMapper() {
		System.out.println("in model mapper bean method");
		ModelMapper mapper = new ModelMapper();
		// set mathching policy=STRICT =>the mapping of props will take place iff -->
		// names n data type of the props match
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			
			if(!roleRepository.findByAuthority("USER").isPresent()) {
			roleRepository.save(new UserRoleEntity(1, "USER"));
			}

			if(!roleRepository.findByAuthority("ADMIN").isPresent()) {
			UserRoleEntity adminRole = roleRepository.save(new UserRoleEntity(2, "ADMIN"));
			
			Set<UserRoleEntity> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User(1, "admin", roles, passwordEncode.encode("password"));

			userRepository.save(admin);
			}
		};
	}

}
