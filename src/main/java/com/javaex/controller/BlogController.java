package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	//메인 블로그 불러오기
	@RequestMapping( value="/{id}" )
	public String blogId(Model model, @PathVariable("id") String id ) {
		System.out.println("[BlogController.blog()]");
		
		BlogVo blogVo = blogService.getBlog(id);
		
		if(blogVo != null) {
			//System.out.println(blogVo);
			model.addAttribute("blogVo", blogVo);
			
			return "/blog/blog-main";
		} else {
			return "redirect:/user/loginForm";
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
