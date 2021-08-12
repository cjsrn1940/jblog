package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	//카테고리 선택 리스트 정보 가져오기
	public List<PostVo> getCateName(String id) {
		System.out.println("[PostService.getCateName]");
		
		List<PostVo> postVoList = postDao.getCateName(id);
		
		return postVoList;
	}
	
	//포스트 등록
	public int insertPost(PostVo postVo) {
		System.out.println("[PostService.insertPost]");
		
		int count = postDao.inserPost(postVo);
		
		
		return count;
		
	}
	
	
}
