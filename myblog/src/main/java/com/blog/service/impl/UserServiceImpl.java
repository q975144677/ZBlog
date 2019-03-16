package com.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.dao.UserDao;
import com.blog.pojo.User;
import com.blog.service.UserService;
@Service
public class UserServiceImpl implements UserService ,UserDetailsService{
@Autowired
	UserDao userDao;
	
	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
	List<User> list = 	userDao.findAll();
		return list;
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		Optional<User> user = userDao.findById(id);
	User result = 	user.get();
		return result;
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
//		List<User> list = userDao.findByUsernameEquals(username);
//		if(list.isEmpty())
//		return null;
//		else {
//			return list.get(0);
	return userDao.findByUsernameEquals(username);	
	//}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	//	User user = getByUsername(username);
	System.out.println(userDao.findByUsernameEquals(username));
		return userDao.findByUsernameEquals(username)  ;
	}
	

}
