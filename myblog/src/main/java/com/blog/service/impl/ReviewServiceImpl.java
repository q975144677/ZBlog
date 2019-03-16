package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.ReviewDao;
import com.blog.pojo.Review;
import com.blog.service.ReviewService;
@Service
public class ReviewServiceImpl implements ReviewService {
@Autowired
ReviewDao reviewDao;
	@Override
	public void insertOrUpdate(Review review) {
		// TODO Auto-generated method stub
		reviewDao.save(review);
	}

	@Override
	public List<Review> list() {
		// TODO Auto-generated method stub
		List<Review> list = reviewDao.findAll();
		return list;
	}

	@Override
	public Review get(int id) {
		// TODO Auto-generated method stub
		return reviewDao.getOne(id);
	}

}
