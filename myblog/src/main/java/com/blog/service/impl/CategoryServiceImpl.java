package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.CategoryDao;
import com.blog.pojo.Category;
import com.blog.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
CategoryDao categoryDao;
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
categoryDao.deleteById(id);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		List<Category> list = categoryDao.findAll();
		return list;
	}

	@Override
	public void saveAndUpdate(Category category) {
		// TODO Auto-generated method stub
categoryDao.save(category);
	}

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		Category category = categoryDao.findById(id).get();
		return category;
	}

}
