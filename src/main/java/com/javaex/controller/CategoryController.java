package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	//카테고리 리스트 가져오기
	@RequestMapping(value="/{id}/admin/category")
	public String adminCategory(Model model, @PathVariable("id") String id) {
		System.out.println("[CategoryController.adminCategory()]");
		
		List<CategoryVo> blogCateVo = categoryService.getCateList(id);
		//System.out.println(blogCateVo);
		
		model.addAttribute("blogCateVo", blogCateVo);
		
		
		return "/blog/admin/blog-admin-cate";
	}
	
	
	//카테고리 삭제
	@RequestMapping(value="/{id}/admin/category/delete")
	public String categoryDelete() {
		System.out.println("[CategoryController.categoryDelete()]");
		
		return "";
	}
}
