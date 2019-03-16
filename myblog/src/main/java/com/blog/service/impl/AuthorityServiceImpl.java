package com.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.AuthorityDao;
import com.blog.pojo.Authority;
import com.blog.service.AuthorityService;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDao authorityDao;
	
	
	@Override
	public Authority get(int id) {
		// TODO Auto-generated method stub
		
		Authority authority  = authorityDao.getOne(id);
		return authority;
	}

}
