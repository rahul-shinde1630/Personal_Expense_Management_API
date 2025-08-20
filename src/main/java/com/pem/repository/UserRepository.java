package com.pem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.pem.entity.UserEntity;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

//	UserEntity findByUserEmail(String user);

	UserEntity findByEmail(String user);

}
