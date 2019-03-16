package com.blog.service;

import java.util.List;

import com.blog.pojo.User;

public interface UserService {
List<User> list();
User get(int id);
User getByUsername(String username);
void delete(int id);
void insert(User user);
void update(User user);
}
