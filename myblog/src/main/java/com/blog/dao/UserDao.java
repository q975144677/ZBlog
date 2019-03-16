package com.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.User;

public interface UserDao extends JpaRepository<User, Integer>{
User findByUsernameEquals(String username);
}
