package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//회원가입폼에서 아이디 중복 체크
	public boolean getUser(String id) {
		System.out.println("[UserService.getUser()]");
		
		UserVo userVo = userDao.selectUser(id);
		
		if(userVo == null) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//회원가입 & 블로그 생성
	public int joinUser(UserVo userVo) {
		System.out.println("[UserService.joinUser()]");
		
		int count = userDao.addUser(userVo);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("id", userVo.getId());
		bMap.put("blogTitle", userVo.getUserName()+"의 블로그입니다");
		
		System.out.println(bMap);
		
		userDao.addBlog(bMap);
		
		return count;
	}
	
	//로그인 사용자정보 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser()]");
		
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
	}
}
