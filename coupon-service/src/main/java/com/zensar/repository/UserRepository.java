package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zensar.entity.UserEntity;
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	List<com.zensar.entity.UserEntity> findByUsername(String username);
}
