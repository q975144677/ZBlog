package com.blog;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blog.dao.ReviewDao;
import com.blog.pojo.Blog;
import com.blog.pojo.Category;
import com.blog.pojo.Review;
import com.blog.pojo.User;
import com.blog.service.BlogService;
import com.blog.service.CategoryService;
import com.blog.service.UserService;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyblogApplication.class)
public class sa {
@Autowired
ReviewDao reviewDao;
	@Autowired 
BlogService blogService ;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService; 
@Test
	public void test() {
//Category category  = new Category();
//PageRequest pageRequest  = new PageRequest(0, 10);
//for(Blog blog  : blogService.list(pageRequest).getContent()) {
//	System.out.println(blog.getId());
//}
//category.setBlogs(blogService.list(pageRequest).getContent());
//category.setName("All");
//categoryService.saveAndUpdate(category);
//Category  category =  categoryService.get(4);
//for(Blog blog  : category.getBlogs()) {
//	System.out.println(blog.getContent());
//	Category category  = new Category();
//	category.setName("d");
//	categoryService.saveAndUpdate(category);
//	category.setBlogs(blogService.list(new PageRequest(0, 5)).getContent());
//	categoryService.saveAndUpdate(category);
//Blog blog = blogService.get(5);
//List<Blog> list = new ArrayList<>();
//list.add(blog);
//category.setBlogs(list);
//System.out.println(category.getId());
//categoryService.saveAndUpdate(category);
//Category category = categoryService.get(4);
//category.setBlogs(new ArrayList<>());
//categoryService.saveAndUpdate(category);
//	List<Blog> list = new ArrayList<>();
//	Category category = new Category() ;
//	Blog blog = new Blog() ; 
//	blog.setTitle("ss");
//	blog.setSubTitle("sds");
//
//	category .setName("dsa");
//	blog.setCategory(category );
//	blog.setContent("dd");
//	Blog blog2 = new Blog() ; 
//	blog2.setTitle("ss");
//	blog2.setSubTitle("sds");
//
//	category .setName("dsa");
//	blog2.setCategory(category );
//	blog2.setContent("dd");
//	list.add(blog);
//	list.add(blog2);
//	category.setBlogs(list);
//	categoryService.saveAndUpdate(category);
	Category category = categoryService.get(13);
	List<Blog> list = category.getBlogs();
	for(Blog blog : list) {
		System.out.println(blog .getId());
	}
}
}
	

