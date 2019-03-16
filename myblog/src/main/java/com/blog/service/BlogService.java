package com.blog.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blog.pojo.Blog;
import com.blog.pojo.Category;

public interface BlogService {
	void insert(Blog blog);
	void update(Blog blog);
	void delete(int id);
	Page<Blog> list(Pageable pageable );
	Page<Blog> listByUserId(int id,Pageable pageable );
	Blog get(int id);
	void readIncrease(int id);
	Page<Blog> listByCategory(Category category ,Pageable pageable );
}
