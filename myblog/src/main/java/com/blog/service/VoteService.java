package com.blog.service;

import com.blog.pojo.Vote;

public interface VoteService {
void insertOrUpdate(Vote vote);
void delete(int id);
Vote deleteByUidAndBid(int uid, int bid);
Vote getByUidAndBid(int uid, int bid);
}
