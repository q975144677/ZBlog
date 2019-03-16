package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.VoteDao;
import com.blog.pojo.Blog;
import com.blog.pojo.Vote;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import com.blog.service.VoteService;
@Service
public class VoteServiceImpl implements VoteService {
@Autowired
VoteDao voteDao;
@Autowired
UserService userService;
@Autowired
BlogService blogService;

	@Override
	public void insertOrUpdate(Vote vote) {
		// TODO Auto-generated method stub
voteDao.save(vote);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
voteDao.deleteById(id);
	}

	@Override
	public Vote getByUidAndBid(int uid, int bid) {
		// TODO Auto-generated method stub
		Blog blog = blogService.get(bid);
		List<Vote> votes  = blog.getVotes();
		for(Vote vote : votes) {
			if(vote.getUser() == null) {
				try {
					throw new Exception("vote 用户");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(vote.getUser().getId() == uid) {
				return vote;
				
			}
		}
		return null;
	}

	@Override
	public Vote deleteByUidAndBid(int uid, int bid) {
		// TODO Auto-generated method stub
		Blog blog = blogService.get(bid);
		List<Vote> votes  = blog.getVotes();
		for(Vote vote : votes) {
			if(vote.getUser() == null) {
				try {
					throw new Exception("vote 用户");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(vote.getUser().getId() == uid) {
				votes.remove(vote);
	
			delete(vote.getId());
				return vote;
			}
		}
		
		return null;
	}

}
