package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")

public class UserController {

	@Autowired
	private UserService userService;
	
	
	//로그인폼
	@RequestMapping(value="/loginForm")
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		return "user/loginForm";
	}
	
	
	//회원가입폼
	@RequestMapping(value="/joinForm")
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");
		
		return "user/joinForm";
	}
	
	
	
	//회원가입 & 블로그 생성
	@RequestMapping(value="/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.joinForm()]");
		System.out.println(userVo);
		
		userService.joinUser(userVo);
		
		return "user/joinSuccess";
	}
	
	
	//아이디 중복 체크(ajax)
	@ResponseBody //응답할때
	@RequestMapping(value="/idCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean idCheck(@RequestParam("id") String id) {
		System.out.println("[UserController.idCheck()]");
		
		boolean state = userService.getUser(id);
		
		return state;
	}
	
	
	
	
	
	
	
	
	
}
