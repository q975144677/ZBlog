package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.blog.dao.BlogDao;
import com.blog.pojo.Blog;
import com.blog.pojo.User;
import com.blog.service.BlogService;
import com.blog.service.UserService;

@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	@Autowired
	BlogService blogService;
	@Autowired
	UserService userService ;
	@GetMapping("my_blog/{username}")
	@PreAuthorize("authentication.name.equals(#username)")
	public ModelAndView myBlog(@PathVariable(name =  "username")String username) {
		ModelAndView mav = new ModelAndView("my_blog");
		 User user = userService.getByUsername(username);
		List<Blog> list = blogDao.findByUserEquals(user);
		mav.addObject("blogs",list);

		return mav;
	}
	@DeleteMapping("my_blog/{username}/{id}")
	@PreAuthorize("authentication.name.equals(#username)")
	public ModelAndView my_blog_delete(@PathVariable(name = "username")String username,@PathVariable(name = "id")int id) {
		ModelAndView mav = new ModelAndView("redirect:../"+username);
		System.out.println(id);
		blogService.delete(id);
		return mav; 
		
		
	}
	
	
	
	
}
