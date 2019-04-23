package com.pierce.sample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pierce.sample.model.Blog;

@Service("blogService")
@Transactional
public class BlogServiceImpl implements BlogService{
		
	static HashMap<Integer,Blog> postIdMap=getPostIdMap();

	public BlogServiceImpl() {
		super();

		if(postIdMap==null)
		{
			postIdMap=new HashMap<Integer,Blog>();
		// Creating some object of posts while initializing
			Blog javaPost = new Blog(1, "Java Platforms","aaaaaaadsfzddv");
			Blog angularPost = new Blog(2, "Angular7 UI Development","cgvfgvsfvsfdv");
			Blog phythonPost = new Blog(3, "Phython-Emerging Technology","sdgfsfgvdfgbdbhdg");
			Blog AiMlPost = new Blog(4, "AI/ML Scopes","sfgfvgfdvfsvfdv");
			Blog cloudPost = new Blog(5, "Cloud Computing","Microsoft Azure, Amazon web services and IBM cloud");
			
			postIdMap.put(1,javaPost);
			postIdMap.put(2,angularPost);
			postIdMap.put(3,phythonPost);
			postIdMap.put(4,AiMlPost);
			postIdMap.put(5,cloudPost);
			
		}
	}

	public static HashMap<Integer, Blog> getPostIdMap() {
		return postIdMap;
	}
	
	public List<Blog> getAllPosts()
	{
		List<Blog> posts = new ArrayList<Blog>(postIdMap.values());
		return posts;
	}


	public Blog getPostById(int id) {
		for(Blog blog : getAllPosts()){
			if(blog.getId() == id){
				return blog;
			}
		}
		return null;
	}
	
	public Blog getPostByTitle(String title) {
		for(Blog blog : getAllPosts()){
			if(blog.getTitle() == title){
				return blog;
			}
		}
		return null;
	}
	
	
	public Blog addPosts(Blog blog)
	{
		//posts.setId(postIdMap.size()+1);
		postIdMap.put(blog.getId(), blog);
		System.out.println("posts added -->"+blog.toString());
		return blog;
	}
	
	public Blog updatePosts(Blog blog)
	{
		postIdMap.put(blog.getId(), blog);
		return blog;
	}
	
	public void deletePost(int id)
	{
		//for 
		postIdMap.remove(id);
	}

	@Override
	public boolean isPostExists(Blog blog) {
		return getPostByTitle(blog.getTitle())!=null;
	}

/*	public void deletepostById(String id) {
		
		for (Iterator<Blog> iterator = postIdMap.iterator(); iterator.hasNext(); ) {
		    Blog blog = iterator.next();
		    if (blog.getId() == id) {
		        iterator.remove();
		    }
		}
	}*/
}
