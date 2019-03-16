package com.blog.service;

import java.util.List;

import com.blog.pojo.Category;

public interface CategoryService {
void delete(int id);
List<Category> list();
void  saveAndUpdate(Category category );
Category get(int id);
}
