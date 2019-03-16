package com.blog.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.blog.dao.UserDao;

import com.blog.pojo.Authority;
import com.blog.pojo.Blog;
import com.blog.pojo.Category;
import com.blog.pojo.Review;
import com.blog.pojo.User;
import com.blog.pojo.Vote;
import com.blog.service.AuthorityService;
import com.blog.service.BlogService;
import com.blog.service.CategoryService;
import com.blog.service.ReviewService;
import com.blog.service.UserService;
import com.blog.service.VoteService;
import com.fasterxml.jackson.databind.BeanProperty.Bogus;

@RestController
public class ForeController {
	@Autowired 
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	AuthorityService authorityService;
	@Autowired
	UserDao userDao;
	@Autowired
	BlogService blogService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	VoteService voteService;

	@RequestMapping("index")
	public ModelAndView index() {
return new ModelAndView("index");
	
	}
	
@GetMapping("/login")
public ModelAndView login() {
	ModelAndView mav = new ModelAndView("login");
	return mav;
}
@PostMapping("login")
public ModelAndView login_check(User user,HttpSession session) {
	ModelAndView mav = new ModelAndView("homepage");
	User result = userService.getByUsername(user.getUsername());
	session.setAttribute("user", 22);


	return mav;
}
@GetMapping("/register")
public ModelAndView register(HttpSession session) {
	ModelAndView mav=  new ModelAndView("register");
	session.setAttribute("22", 22);
	
	
	SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

	return mav;
}
@PostMapping("register")
public ModelAndView register_check(User user) {

	ModelAndView mav=  new ModelAndView("redirect:login");
	User result = userService.getByUsername(user.getUsername());
	if(result != null) {
		mav.addObject("error","error");
		mav.setViewName("register");
		return mav;
	}else {
		List<Authority> list = new ArrayList<Authority>();
		list.add(authorityService.get(2));
		user.setAuthorities(list);
		BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.insert(user);

try {
	String path = ResourceUtils.getURL("classpath:").getPath();
File file = new File(path,"static/0.jpg");
File pa = new File(path+"static/img");
if(!pa.exists()) {
	pa.mkdirs();
}
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path+"static/img/"+user.getUsername()+".jpg"));
	byte[] b = new byte[1024];

	while((bis.read(b)!=-1)) {
		bos.write(b);
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	}
	return mav;
}
@RequestMapping("login_error")
public ModelAndView l() {
	return new ModelAndView("login_error");
}
@GetMapping("error")
public ModelAndView l2() {
	return new ModelAndView("error");
}
@RequestMapping("wrong")
public ModelAndView l22() {
	return new ModelAndView("register");
}
@RequestMapping("homepage")
public ModelAndView h(@RequestParam(value = "page" , defaultValue = "0")int page) {
	ModelAndView mav = new ModelAndView("homepage");
	Sort sort = new Sort(Direction.DESC,"id");
	PageRequest pageable = new PageRequest(0, 10, sort);
	Page<Blog> blogs = blogService.list(pageable);
	mav.addObject("page",blogs);
	List<Blog> list = blogs.getContent();
	mav.addObject("blogs",list);
	List<Category> categories = categoryService.list();
	mav.addObject("categories",categories);
	return mav;
	
}
@GetMapping("blog/{id}")
public ModelAndView blogDetail(@PathVariable("id")int id) {
	ModelAndView mav = new ModelAndView("blog_detail");
	Blog blog = blogService.get(id);
	List<Review> list = blog.getReviews();
	Collections.reverse(list); 
	blog.setReviews(list);
	mav.addObject("blog",blog);
	Object object  = SecurityContextHolder.getContext().getAuthentication().getPrincipal() ; 
	if(object instanceof User) {
		User user =  (User)object; 
	if(voteService.getByUidAndBid(user.getId(), id) != null) {
		mav.addObject("hasVote",true);
	}else {
		mav.addObject("hasVote",false);
	}
	}else {
		mav.addObject("hasVote",false);
	}
	return mav;
}
@PostMapping("review/{id}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
@Transactional
public ModelAndView reviewInsert(Review review,@PathVariable(name = "id")int bid) {
	ModelAndView mav = new ModelAndView("redirect:../blog/"+bid);
	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
review.setId(0);
	review.setUser(user);
	Blog blog = blogService.get(bid);
	blog.setCommentSize((blog.getCommentSize()+1));
	blogService.insert(blog);
	review.setBlog(blog);
	reviewService.insertOrUpdate(review);
	return mav;
	
}

@RequestMapping("/vote")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
public String addVote(int bid) {
	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	Blog blog  = blogService.get(bid);
	blog.getVotes().add(new Vote(user));
	blog.setVoteSize(blog.getVoteSize()+1);
	blogService.update(blog);
	return "success";
}
@PostMapping("vote/{id}")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
@Transactional
public String delteVote(@PathVariable(name = "id")int bid) {
	System.out.println(bid+"------------------");
	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
Vote vote = 	voteService.deleteByUidAndBid(user.getId(),bid );

Blog blog = blogService.get(bid);
blog.setVoteSize(blog.getVoteSize()-1);
blogService.insert(blog);
	return "success";
}
@GetMapping("write_blog")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
public ModelAndView writeBlog() {
	ModelAndView mav = new ModelAndView("write_blog");
	List<Category> list = categoryService.list();
	mav.addObject("categories",list);
	return mav;
	
}
@PostMapping("write_blog")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
public ModelAndView write(Blog  blog) {
	ModelAndView mav = new ModelAndView("redirect:homepage");
	blog.setCommentSize(0);
	blog.setReadSize(0);
	blog.setUser((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	blog.setVoteSize(0);
	blogService.insert(blog);
	
	return mav;
}
@GetMapping("category/{id}/{page}")
public ModelAndView category(@PathVariable(name = "id") int id , @PathVariable(name = "page" ) int page) {
	ModelAndView mav = new ModelAndView("category");
	Sort sort = new Sort(Direction.DESC,"id");
	Pageable pageable = new PageRequest(page-1, 10,sort);
	Category category =  categoryService.get(id);
	Page<Blog> blogs = blogService.listByCategory(category, pageable);
	
	mav.addObject("blogs",blogs);
	mav.addObject("id",id);
	return mav;
}
}
