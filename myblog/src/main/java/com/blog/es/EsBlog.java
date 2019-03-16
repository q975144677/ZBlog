package com.blog.es;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import com.blog.pojo.Blog;
@Document(indexName = "blog" , type  = "blog")
public class EsBlog {
	@Id
private int id ;
 
private String name;

private String content;

public String getContent() {
	return content;
}
public void blog2EsBlog(Blog blog ) {
	id = blog.getId();
	name = blog.getTitle();
	content = blog.getContent();
}

public void setContent(String content) {
	this.content = content;
}

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
	
}
