package com.testcom.oce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testcom.oce.entity.User;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer> {
	
}
