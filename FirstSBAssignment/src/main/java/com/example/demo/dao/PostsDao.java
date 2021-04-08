package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Posts;

public interface PostsDao extends JpaRepository<Posts, Integer>{

}
