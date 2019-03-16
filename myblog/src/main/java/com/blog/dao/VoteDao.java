package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.pojo.Vote;

public interface VoteDao extends JpaRepository<Vote, Integer>{

}
