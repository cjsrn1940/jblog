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
	
	//카테고리 삭제(포스트 수 확인)
	public int deleteCate(int cateNo) {
		System.out.println("[CategoryDao.deleteCate()]");
		
		return sqlSession.delete("category.deleteCate", cateNo);
	}
	
	//카테고리 추가
	public int addCategory(CategoryVo categoryVo) {
		System.out.println("[CategoryDao.addCategory()]");
		
		return sqlSession.insert("category.insertCate", categoryVo);
	}
	
	//카테고리 하나 불러오기
	public CategoryVo selectCate(int cateNo) {
		System.out.println("[CategoryDao.selectCate()]");
		CategoryVo cateVo = sqlSession.selectOne("category.selectCateBycateNo", cateNo);
		
		System.out.println(cateVo);
		return cateVo;
	}
}









