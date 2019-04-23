package codetest;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.web.client.RestTemplate;

import com.pierce.sample.model.Blog;
 
public class RestCodeTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/codetest/blog-web";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void getAllPosts(){
        System.out.println("Testing getAllPosts API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> postsMap = restTemplate.getForObject(REST_SERVICE_URI+"/posts/", List.class);
         
        if(postsMap!=null){
            for(LinkedHashMap<String, Object> map : postsMap){
                System.out.println("Post : id="+map.get("id")+", Title="+map.get("title")+", Content="+map.get("content"));;
            }
        }else{
            System.out.println("No post exist----------");
        }
    }
     
    /* GET */
    private static void getPostById(){
        System.out.println("Testing getPostById API----------");
        RestTemplate restTemplate = new RestTemplate();
        Blog blog = restTemplate.getForObject(REST_SERVICE_URI+"/posts/1", Blog.class);
        System.out.println(blog);
    }
     
    /* POST */
    private static void addPosts() {
        System.out.println("Testing add Post API----------");
        RestTemplate restTemplate = new RestTemplate();
        Blog blog = new Blog(9,"Algorithms","Bubble sort algo");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/posts/", blog, Blog.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT */
    private static void updatePosts() {
        System.out.println("Testing update Post API----------");
        RestTemplate restTemplate = new RestTemplate();
       
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        Blog blog  = new Blog(1,"Work Life","lets do something potential");
        restTemplate.put(REST_SERVICE_URI+"/posts/1", blog, map);
        System.out.println(blog);
    }
 
    /* DELETE */
    private static void deletePost() {
        System.out.println("Testing delete Post API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/posts/3");
    }
 

    public static void main(String args[]){
    	getAllPosts();
        getPostById();
        addPosts();
        getAllPosts();
        updatePosts();
        getAllPosts();
        deletePost();
        getAllPosts();
    }
}