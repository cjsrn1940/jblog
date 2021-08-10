package com.javaex.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원정보 가져오기 --> id로 중복체크
	public UserVo selectUser(String id) {
		System.out.println("[UserDao.selectUser()]");
		System.out.println(id);
		
		return sqlSession.selectOne("user.selectUserById", id);
	}
	
	
	//user 추가
	public int addUser(UserVo userVo) {
		System.out.println("[UserDao.addUser()]");
		
		int count = sqlSession.insert("user.addUser", userVo);
		
		return count;
	}
	
	//blog생성
	public int addBlog(Map<String, Object> bMap) {
		System.out.println("[UserDao.addBlog()]");
		
		return sqlSession.insert("blog.addBlog", bMap);
	}
	
	//user 한명 정보 확인하기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}
}
