package com.testcom.oce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testcom.oce.entity.Post;

public interface PostJPARepository extends JpaRepository<Post, Integer> {

}
