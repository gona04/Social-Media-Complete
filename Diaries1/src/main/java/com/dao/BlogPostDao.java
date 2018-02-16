package com.dao;

import java.util.List;

import com.model.BlogComment;
import com.model.BlogPost;

public interface BlogPostDao 
{
	void saveBlogPost(BlogPost blogpost);
	List<BlogPost> getBlogPosts(int approved);
	BlogPost getBlogPostById(int id);
	void updateBlogPost(BlogPost blogPost);
	List<BlogPost> getApprovalStatus(String username);
	
	//OF BLOG COMMENT CLASS
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getBlogComments(int blogPostId);
	void updateViewedStatus(List<BlogPost> blogPosts);
}
