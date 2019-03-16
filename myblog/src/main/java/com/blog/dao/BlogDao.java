package com.blog.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.Blog;
import com.blog.pojo.Category;
import com.blog.pojo.User;

public interface BlogDao extends JpaRepository<Blog, Integer> {
Page<Blog> findByUserEquals(User user,Pageable pageable );
Page<Blog> findByUserAndTitleLike(User user , String title , Pageable pageable );
Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(String title,User user, String tags, Pageable pageable);
Page<Blog> findByCategoryEquals(Pageable pageable  , Category category);
List<Blog> findByUserEquals(User user );
}
