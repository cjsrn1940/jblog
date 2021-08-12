package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	
	//블로그 글작성 불러오기
	@RequestMapping(value="/{id}/admin/writeForm")
	public String adminWriteForm(@PathVariable("id") String id, Model model) {
		System.out.println("[PostController.adminWriteForm()]");
		model.addAttribute("blogId", id);
		
		List<PostVo> postVoList = postService.getCateName(id);
		model.addAttribute("postVoList", postVoList);
		
		return "/blog/admin/blog-admin-write";
	}
	
	//포스트 등록
	@RequestMapping(value="/{id}/admin/writeForm/insert")
	public String adminWrite(Model model, @PathVariable("id") String id, @ModelAttribute PostVo postVo) {
		System.out.println("[PostController.adminWrite()]");
		model.addAttribute("blogId", id);
		
		System.out.println(postVo);
		postService.insertPost(postVo);
		
		
		
		return "redirect:/"+ id +"/admin/writeForm";
	}
}
