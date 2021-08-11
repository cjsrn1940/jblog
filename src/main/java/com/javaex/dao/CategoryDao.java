package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	//카테고리 리스트 불러오기
	public List<CategoryVo> getCateList(String id) {
		System.out.println("[CategoryDao.getCateList()]");
		
		List<CategoryVo> blogCateVo = sqlSession.selectList("category.getCateList", id);
		
		return blogCateVo;
	}
}
