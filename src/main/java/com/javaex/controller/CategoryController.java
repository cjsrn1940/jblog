package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//ajax admin-cate페이지
	@RequestMapping(value="/{id}/admin/category")
	public String ajaxCate(@PathVariable("id") String id, Model model) {
		System.out.println("[CategoryController.ajaxCate()]");
		
		model.addAttribute("blogId", id);
		
		return "/blog/admin/blog-admin-cate";
	}
	
	
	//카테고리 리스트 가져오기
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/list")
	public List<CategoryVo> adminCategory(Model model, @PathVariable("id") String id) {
		System.out.println("[CategoryController.adminCategory()]");
		
		List<CategoryVo> blogCateList = categoryService.getCateList(id);
		//System.out.println(blogCateVo);
		
		
		return blogCateList;
	}
	
	
	//카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/delete")
	public int categoryDelete(@RequestParam("no") int no) {
		System.out.println("[CategoryController.categoryDelete()]");
		//System.out.println(no);
		
		int count = categoryService.deleteCate(no);
		
		return count;
	}
	
	//카테고리 추가
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/insert")
	public CategoryVo categoryInsert(@ModelAttribute CategoryVo categoryVo,  @PathVariable("id") String id) {
		System.out.println("[CategoryController.categoryInsert()]");
		
		System.out.println(categoryVo);
		categoryVo.setId(id);
		System.out.println(categoryVo);
		
		CategoryVo cateVo = categoryService.addCategory(categoryVo);
		
		
		return cateVo;
	}
}
