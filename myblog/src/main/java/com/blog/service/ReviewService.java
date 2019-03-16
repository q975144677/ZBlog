package com.blog.service;

import java.util.List;

import com.blog.pojo.Review;

public interface ReviewService {
void insertOrUpdate(Review review);
List<Review> list();
Review get(int id);
}
