package com.javaex.vo;

public class BlogVo {
	
	private String id;
	private String blogTitle;
	private String logoFile;
	private String userName;
	private int cateNo;
	private String cateName;
	private String postTitle;
	private String regDate;
	private String postContent;
	private int postNo;
	
	
	
	
	public BlogVo() {
		super();
	}

	public BlogVo(String id, String blogTitle) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
	}

	public BlogVo(String id, String blogTitle, String logoFile) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}



	public BlogVo(String id, String blogTitle, String logoFile, String userName, int cateNo, String cateName,
			String postTitle, String regDate, String postContent, int postNo) {
		super();
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
		this.userName = userName;
		this.cateNo = cateNo;
		this.cateName = cateName;
		this.postTitle = postTitle;
		this.regDate = regDate;
		this.postContent = postContent;
		this.postNo = postNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + ", userName=" + userName
				+ ", cateNo=" + cateNo + ", cateName=" + cateName + ", postTitle=" + postTitle + ", regDate=" + regDate
				+ ", postContent=" + postContent + ", postNo=" + postNo + "]";
	}
	
	
	
	
	
	
	
	
	
}
