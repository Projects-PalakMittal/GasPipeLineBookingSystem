package com.gasnet.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasnet.pojo.Status;
import com.gasnet.pojo.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	Optional<User> findByEmailAndPasswordAndUserStatus(String Email,String Password,Status status);

	Optional<User> findByEmail(String Email);

	boolean existsByEmail(String Email);

	Optional<User> findByEmailAndUserStatus(String email, Status status);
	
}
