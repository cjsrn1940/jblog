package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")

public class UserController {

	@Autowired
	private UserService userService;
	
	
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
	
	
	//로그인폼
	@RequestMapping(value="/loginForm")
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		
		UserVo authUser = userService.getUser(userVo);
		//System.out.println(authUser);
		
		if(authUser != null) { //로그인 성공하면(authUser null 이 아니면)
			System.out.println("[로그인성공]");
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else { //로그인 실패하면(authUser null 이면 )
			System.out.println("[로그인실패]");
			return "redirect:/user/loginForm?result=fail";
		}
		
		
	}
	
	//로그아웃
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout()]");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
}
