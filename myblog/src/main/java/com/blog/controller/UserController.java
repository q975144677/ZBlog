package com.blog.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.blog.dao.UserDao;
import com.blog.pojo.Authority;
import com.blog.pojo.User;
import com.blog.service.AuthorityService;
import com.blog.service.UserService;



@RestController
@RequestMapping("admin")
public class UserController {
@Autowired
UserService userService;
@Autowired
AuthorityService authorityService;
@Autowired
UserDao userDao;
@GetMapping("user")
public ModelAndView userList(@RequestParam(value = "page",defaultValue = "0") Integer p){
	ModelAndView mav = new ModelAndView("/user");
	Sort sort = new Sort(Sort.Direction.DESC,"id");
	PageRequest pageRequest = new PageRequest(p, 10, sort);  
	Page<User> page = userDao.findAll(pageRequest);
	List<User> users = page.getContent();
	mav.addObject("page",page);
	mav.addObject("users",users);
	return mav;
}
@PostMapping("user")
public ModelAndView userInsert(User user) {
	ModelAndView mav = new ModelAndView("redirect:user") ; 
	userService.insert(user);
	return mav;
}
@DeleteMapping("user/{id}")
public ModelAndView userDelete(@PathVariable("id") Integer id) {
	ModelAndView mav = new ModelAndView("redirect:../user");
	userService.delete(id);
	return mav;
}

}
