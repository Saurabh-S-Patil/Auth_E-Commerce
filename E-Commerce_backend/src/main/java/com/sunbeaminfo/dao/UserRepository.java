package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeaminfo.entities.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User>findByEmail(String email);
	Optional<User> findById(Long id);
	void deleteById(Long id);
	boolean existsById(Long id);

}
