package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	//메인 블로그 불러오기(아이디 확인)
	public BlogVo getBlog(String id) {
		System.out.println("[BlogService.getBlog()]");
		
		BlogVo blogVo = blogDao.getBlog(id);
		
		return blogVo;
	}
	
	

	//블로그 기본설정 수정
	public int updateBlogBasic(String id, String blogTitle, MultipartFile file) {
		System.out.println("[BlogService.updateBlogBasic()]");
		
		long fileSize = file.getSize();
		
		if(fileSize > 0) {
			
			String saveDir = "C:\\javaStudy\\upload";
			
			//원파일이름
			String orgName = file.getOriginalFilename();
			System.out.println("orgName:" + orgName);
			
			//확장자
			String exName = orgName.substring(orgName.lastIndexOf("."));
			System.out.println("exName:" + exName);
			
			
			//저장파일이름
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			System.out.println("saveName:" + saveName);
			
			//파일패스
			String filePath = saveDir + "\\" + saveName;
			System.out.println("filePath:" + filePath);
			
			//1.파일을 서버의 하드디스크에 저장
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				
				bout.write(fileData);
				bout.close();		
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			BlogVo blogUpdateVo = new BlogVo(id, blogTitle, saveName);
			int count = blogDao.updateBlogBasic(blogUpdateVo);
			return count;
		} else {
			
			System.out.println(file);
			
			BlogVo blogUpdateVo = new BlogVo(id, blogTitle);
			
			int count = blogDao.updateBlogBasicTitle(blogUpdateVo);
			return count;
			
		}
		
		

	}
	
	
	
	
	
	
	
	
	
}
