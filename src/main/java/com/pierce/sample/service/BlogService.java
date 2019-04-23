package com.pierce.sample.service;

import java.util.List;
import com.pierce.sample.model.Blog;

public interface BlogService {
	
	Blog getPostById(int id);
		
	Blog addPosts(Blog user);
	
	Blog updatePosts(Blog user);
	
	void deletePost(int id);

	List<Blog> getAllPosts();

	boolean isPostExists(Blog blog); 
	
	 Blog getPostByTitle(String title);
}
