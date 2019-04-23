package com.pierce.sample.model;

public class Blog {

	private Integer id;
	
	private String title;
	
	private String content;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Blog(){
		super();
	}
	
	public Blog(int id, String title, String content){
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [postid=" + id + ", title=" + title + ", content=" + content
				+ "]";
	}


}
