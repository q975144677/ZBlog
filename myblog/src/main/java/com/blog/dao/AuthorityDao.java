package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {

}
