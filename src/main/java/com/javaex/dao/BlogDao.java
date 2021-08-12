package com.javaex.dao;

import java.util.List;
import java.util.Map;

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
	
	//카테고리 정보 불러오기
	public List<BlogVo> cateInfo(String id) {
		System.out.println("[BlogDao.cateInfo()]");
		
		return sqlSession.selectList("blog.selectCateList", id);
	}
	
	//카테고리 별 포스트 정보 불러오기
	public List<BlogVo> postInfo(Map<String, Object> bMap) {
		System.out.println("[BlogDao.postInfo()]");
		
		return sqlSession.selectList("blog.selectPostList", bMap);
	}
	
	//카테고리 별 최신 포스트 상세(기본)
	public BlogVo getPostBasic(Map<String, Object> bMap) {
		System.out.println("[BlogDao.getPost()]");
		
		return sqlSession.selectOne("blog.selectRegdateMaxPost", bMap);
	}
	
	//카테고리 별 최신 포스트 상세(기본 + postNo)
	public BlogVo getPostSelect(Map<String, Object> bMap) {
		System.out.println("[BlogDao.getPostSelect()]");
		
		return sqlSession.selectOne("blog.selectPost", bMap);
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
