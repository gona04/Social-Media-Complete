package com.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BlogPostDao;
import com.model.BlogComment;
import com.model.BlogPost;

@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao 
{

	@Autowired
	SessionFactory sessionFactory;
	
	//SAVE A BLOGPOST
	public void saveBlogPost(BlogPost blogpost) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.save(blogpost);
		session.flush();
	}

	//GET BLOG POST BASED ON THEIR APPROVED STATUS
	public List<BlogPost> getBlogPosts(int approved) 
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BlogPost where approved=" + approved);
		List<BlogPost> blogPosts = query.list();
		return blogPosts;
	}

	//GET A SPECIFIC BLOGPOST BY ID
	public BlogPost getBlogPostById(int id) 
	{
		Session session = sessionFactory.getCurrentSession();
		BlogPost blogPost = (BlogPost) session.get(BlogPost.class, id);
		return blogPost;
	}

	//UPDATE BLOG POST MAINLY APPROVED STATUS REASON FOR REJECTION ETC
	public void updateBlogPost(BlogPost blogPost)
	{
		Session session = sessionFactory.getCurrentSession();
		session.update(blogPost);
		session.flush();
	}

	//GET THE UPDATE STATUS TO THE USER
	public List<BlogPost> getApprovalStatus(String username) 
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BlogPost where postedBy.userName=? and viewedStatus=? and (approved=1 or rejectionReason != null)");
		query.setString(0, username);
		query.setBoolean(1, false);
		List<BlogPost> blogPosts = (List<BlogPost>)query.list();
		for(BlogPost bp : blogPosts)
		{
			bp.setViewedStatus(true);
			session.update(bp);
		}
		return blogPosts;
	}

	
	public void addBlogComment(BlogComment blogComment) 
	{
		Session session=sessionFactory.getCurrentSession();
		session.save(blogComment);
	}

	public List<BlogComment> getBlogComments(int blogPostId)
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogPost.id=?");
		query.setInteger(0, blogPostId);
		return query.list();//list of blogcomments for blogpostid
	}

	public void updateViewedStatus(List<BlogPost> blogPosts)
	{
		Session session=sessionFactory.getCurrentSession();
		for(BlogPost bp:blogPosts)
		{
		bp.setViewedStatus(true);
		
		session.update(bp);
		}
	}
}
