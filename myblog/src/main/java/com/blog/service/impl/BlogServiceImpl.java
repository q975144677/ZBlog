package com.blog.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.dao.BlogDao;
import com.blog.pojo.Blog;
import com.blog.pojo.Category;
import com.blog.pojo.User;
import com.blog.service.BlogService;
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogDao blogDao;
	
	@Override
	public void insert(Blog blog) {
		// TODO Auto-generated method stub
		blogDao.save(blog);
		
	}

	@Override
	public Page<Blog> listByCategory(Category category ,Pageable pageable ) {
		// TODO Auto-generated method stub
		Page<Blog> list = blogDao.findByCategoryEquals(pageable, category);
		return list;
	}

	@Override
	public void update(Blog blog) {
		// TODO Auto-generated method stub
		blogDao.save(blog);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Blog blog = get(id);
		blogDao.delete(blog);
	}

	@Override
	public Page<Blog> list(Pageable pageable ) {
		// TODO Auto-generated method stub
		Page<Blog> list = blogDao.findAll(pageable);
		return list;
	}

	@Override
	public Page<Blog> listByUserId(int id,Pageable pageable ) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(id);
		Page<Blog> list = blogDao.findByUserEquals(user,pageable);
		return list;
	}

	@Override
	public Blog get(int id) {
		// TODO Auto-generated method stub
		Blog blog  = blogDao.findById(id).get();
		return blog;
	}
	@Override
	public void readIncrease(int id) {
		// TODO Auto-generated method stub
		Blog blog = blogDao.getOne(id);
		blog.setReadSize(1+blog.getReadSize());
		blogDao.save(blog);
	}

}
