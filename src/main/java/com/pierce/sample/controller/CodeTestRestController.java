package com.pierce.sample.controller;
 
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pierce.sample.model.Blog;
import com.pierce.sample.service.BlogService;
 

 
@RestController
@RequestMapping("/blog-web")
public class CodeTestRestController {
 
    @Autowired
    BlogService blogService;  //Service which will do all data retrieval/manipulation work
    
    private static final Logger log = Logger.getLogger(CodeTestRestController.class);
 
     
    //-------------------Retrieve All Posts--------------------------------------------------------
     
    @RequestMapping(value = "/posts/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Blog>> getAllPosts() {
    	log.info("Inside getAllPosts()");
        List<Blog> blogs = blogService.getAllPosts();
        if(blogs.isEmpty()){
            return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Post--------------------------------------------------------
     
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Blog> getPostById(@PathVariable("id") int id) {
    	log.info("Inside getPostById()"+id);
        Blog blog = blogService.getPostById(id);
        if (blog == null) {
        	log.debug("Post with id " + id + " not found");
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }
 
     
     
    //-------------------Add a post--------------------------------------------------------
     
    @RequestMapping(value = "/posts/", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Void> addPosts(@RequestBody Blog blog, UriComponentsBuilder ucBuilder) {
        log.info("Inside addPosts()" + blog.getTitle());
        if (blogService.isPostExists(blog)) {
            log.debug("A Post with title " + blog.getTitle() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        blogService.addPosts(blog);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/posts/{id}").buildAndExpand(blog.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a post --------------------------------------------------------
     
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Blog> updatePosts(@PathVariable("id") int id, @RequestBody Blog blog) {
    	log.info("Inside updatePosts()" + id);
         
        Blog newblog = blogService.getPostById(id);
         
        if (newblog==null) {
            log.debug("post with id " + id + " not found");
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
 
        newblog.setTitle(blog.getTitle());
        newblog.setContent(blog.getContent());
        blogService.updatePosts(newblog);
        return new ResponseEntity<Blog>(newblog, HttpStatus.OK);
    }
 
    //------------------- Delete a post --------------------------------------------------------
     
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Blog> deletePost(@PathVariable("id") int id) {
    	log.info("Inside deletePost "+ id);
 
        Blog blog = blogService.getPostById(id);
        if (blog == null) {
            log.debug("Unable to delete. Post with id " + id + " not found");
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
 
        blogService.deletePost(id);
        return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
    }
    
 
}