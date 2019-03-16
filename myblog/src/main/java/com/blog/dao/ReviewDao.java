package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.Review;

public interface ReviewDao extends JpaRepository<Review, Integer> {

}
