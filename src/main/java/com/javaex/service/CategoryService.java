package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	
	//블로그 카테고리 리스트 불러오기
	public List<CategoryVo> getCateList(String id) {
		System.out.println("[CategoryService.getCateList()]");
		
		List<CategoryVo> blogCateVo = categoryDao.getCateList(id);
		
		return blogCateVo;
	}
	
	//카테고리 삭제
	public int deleteCate(int cateNo) {
		System.out.println("[CategoryService.deleteCate()]");
		
		int count = categoryDao.deleteCate(cateNo);
		
		return count;
	}
	
	//카테고리 추가
	public CategoryVo addCategory(CategoryVo categoryVo) {
		System.out.println("[CategoryService.addCategory()]");
		
		System.out.println(categoryVo);
		categoryDao.addCategory(categoryVo);
		System.out.println(categoryVo);
		
		int cateNo = categoryVo.getCateNo();
		
		CategoryVo cateVo = categoryDao.selectCate(cateNo);
		
		return cateVo;
	}
		
}
