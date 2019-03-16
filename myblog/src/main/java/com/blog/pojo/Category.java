package com.blog.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
@Entity
@Proxy(lazy = false)
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column
private String name; 
 @OneToMany(mappedBy = "category")
 @LazyCollection(LazyCollectionOption.FALSE)
private List<Blog> blogs;
 
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<Blog> getBlogs() {
	return blogs;
}
public void setBlogs(List<Blog> blogs) {
	this.blogs = blogs;
}
 
 
 
}
