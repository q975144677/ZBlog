package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

}
