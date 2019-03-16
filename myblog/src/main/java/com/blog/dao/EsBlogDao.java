package com.blog.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.blog.es.EsBlog;

public interface EsBlogDao extends ElasticsearchRepository<EsBlog, Integer>{

}
