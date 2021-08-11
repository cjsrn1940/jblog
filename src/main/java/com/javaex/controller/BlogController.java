package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	//메인 블로그 불러오기
	@RequestMapping( value="/{id}" )
	public String blogId(Model model, @PathVariable("id") String id, HttpSession session ) {
		System.out.println("[BlogController.blog()]");
		
		BlogVo blogVo = blogService.getBlog(id);
		
		if(blogVo != null) {
			//System.out.println(blogVo);
			session.setAttribute("blogVoBlogTitle", blogVo.getBlogTitle());
			model.addAttribute("blogVo", blogVo);
			
			return "/blog/blog-main";
		} else {
			return "redirect:/";
		}
		
		
	}
	
	
	
	//블로그 기본설정폼
	@RequestMapping(value="/{id}/admin/basic")
	public String adminBasic(Model model, @PathVariable("id") String id, HttpSession session) {
		System.out.println("[BlogController.adminBasic()]");
		
		BlogVo blogVo = blogService.getBlog(id);
		//System.out.println(blogVo);
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		//System.out.println(userVo);
		
		//블로그가 없는 경우
		if(blogVo.getId() == null) {
			return "redirect:/";
		}
		
		//로그인안한 경우
		if(userVo.getId() == null) {
			return "redirect:/user/loginForm";
		}
		
		//로그인한 아이디랑 블로그 아이디 비교
		if(userVo.getId().equals(blogVo.getId())) {
			model.addAttribute("blogVo", blogVo);
			
			return "/blog/admin/blog-admin-basic";
		} else {
			//로그인은 했지만 블로그 주인이 아닌 경우
			return "redirect:/"+id+"";
		}
		
		
		
		
		
	}
	
	
	//블로그 기본설정 수정
	@RequestMapping(value="/{id}/admin/basic/update")
	public String adminBasicUpdate(@PathVariable("id") String id, @RequestParam("blogTitle") String blogTitle, 
									@RequestParam("file") MultipartFile file, HttpSession session) {
		System.out.println("[BlogController.adminBasic()]");
	 
		
		blogService.updateBlogBasic(id, blogTitle, file);
		
		session.setAttribute("blogVoBlogTitle", blogTitle);
		
		return "redirect:/"+id+"/admin/basic";
	}
	
	
	
	
	
	
	
	
	
}
