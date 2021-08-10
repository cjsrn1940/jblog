package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	//메인 블로그 불러오기(아이디 확인)
	public BlogVo getBlog(String id) {
		System.out.println("[BlogService.getBlog()]");
		
		BlogVo blogVo = blogDao.getBlog(id);
		
		return blogVo;
	}
}
