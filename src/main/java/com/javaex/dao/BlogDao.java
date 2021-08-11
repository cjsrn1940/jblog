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
		BlogVo blogVo = sqlSession.selectOne("blog.selectBlogById", id);
		
		//System.out.println(blogVo);
		return blogVo;
	}
	
	//블로그 수정(blogTitle, logoFile)
	public int updateBlogBasic(BlogVo blogVo) {
		System.out.println("[BlogDao.updateBlogBasic()]");
		
		int count = sqlSession.update("blog.updateBlogBasic", blogVo);
		
		return count;
	}
	
	
	//블로그 수정(blogTitle)
	public int updateBlogBasicTitle(BlogVo blogVo) {
		System.out.println("[BlogDao.updateBlogBasicTitle()]");
		
		int count = sqlSession.update("blog.updateBlogBasicTitle", blogVo);
		
		return count;
	}
	
	
}
