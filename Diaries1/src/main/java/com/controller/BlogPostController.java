package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.BlogPostDao;
import com.dao.UserDao;
import com.model.BlogPost;
import com.model.Error;
import com.model.User;

@Controller
public class BlogPostController 
{
	@Autowired
	BlogPostDao blogPostDao;
	@Autowired
	UserDao userDao;
	
	//SAVE BLOG POST
	@RequestMapping(value="/saveBlogPost" ,method=RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost,HttpSession session)
	{
		if(session.getAttribute("username") == null)
		{
			System.out.println(session.getAttribute("username"));
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String userName = (String)session.getAttribute("username");
		User user = userDao.getUserByUsername(userName);
		blogPost.setPostedOn(new Date());
		blogPost.setPostedBy(user);
		try
		{
			blogPostDao.saveBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error = new Error(6,"Unable to insert blogpost details"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET BLOG POSED WITH THEIR APPROVED STATUS
	@RequestMapping(value = "/getBlogPosts/{approved}")
	public ResponseEntity<?> getBlogPosts(@PathVariable int approved,HttpSession session)
	{
		System.out.println(" the approved status is ?"+ approved);
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogPost> blogPosts = (List<BlogPost>) blogPostDao.getBlogPosts(approved);
		return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	}
	
	//GET BLOG POST BY ID (GET A SPECIFIC BLOG POST BY ID)
	@RequestMapping(value="/getBlogPostById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPostsById(@PathVariable int id, HttpSession session)
	{
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		BlogPost blogPost = blogPostDao.getBlogPostById(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	//UPDATE BLOGPOST MAINLY THE APPROVED STATUS AND REJECTION REASON
	@RequestMapping(value="/updateBlogPost" , method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost, HttpSession session)
	{
		if(session.getAttribute("usename") == "null")
		{
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try
		{
			if(!blogPost.isApproved() && blogPost.getRejectionReason() == null)
			{
				blogPost.setRejectionReason("Not mentioned");
			}
			blogPostDao.updateBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error = new Error(6,"Unable to update the BlogPost" + e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET APPROVED STATUS FOR THE USER
	@RequestMapping("/getApprovalStatus")
	public ResponseEntity<?> getApprovalUpdate(HttpSession session)
	{
		if(session.getAttribute("usename") == "null")
		{
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username = (String) session.getAttribute("username");
		List<BlogPost> blogPosts = blogPostDao.getApprovalStatus(username);
			return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	}
}
