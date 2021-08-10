package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;


@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	//메인 블로그 불러오기(아이디 확인)
	public BlogVo getBlog(String id) {
		System.out.println("[BlogDao.getBlog()]");
		
		return sqlSession.selectOne("blog.selectBlogById", id);
	}
}
