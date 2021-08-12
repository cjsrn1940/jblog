package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	//카테고리 선택 리스트 정보 가져오기
	public List<PostVo> getCateName(String id) {
		System.out.println("[PostDao.getCateName]");
		
		return sqlSession.selectList("post.selectCate", id);
	}
	
	//포스트 등록
	public int inserPost(PostVo postVo) {
		System.out.println("[PostDao.inserPost]");
		
		return sqlSession.insert("post.insertPost", postVo);
	}
}
