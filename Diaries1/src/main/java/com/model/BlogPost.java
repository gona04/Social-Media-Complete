package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class BlogPost 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogId;
	@NotEmpty
	private String blogtitle;
	@Lob
	private String description;
	@ManyToOne
	private User postedBy;
	private Date postedOn;
	private boolean approved;
	private String rejectionReason;
	private boolean viewedStatus;
	
	public int getBlogId()
	{
		return blogId;
	}
	public void setBlogId(int blogId) 
	{
		this.blogId = blogId;
	}
	public String getBlogtitle()
	{
		return blogtitle;
	}
	public void setBlogtitle(String blogtitle) 
	{
		this.blogtitle = blogtitle;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String discription)
	{
		this.description = discription;
	}
	public User getPostedBy() 
	{
		return postedBy;
	}
	public void setPostedBy(User postedBy)
	{
		this.postedBy = postedBy;
	}
	public Date getPostedOn()
	{
		return postedOn;
	}
	public void setPostedOn(Date postedOn)
	{
		this.postedOn = postedOn;
	}
	public boolean isApproved()
	{
		return approved;
	}
	public void setApproved(boolean approved)
	{
		this.approved = approved;
	}
	public String getRejectionReason() 
	{
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason)
	{
		this.rejectionReason = rejectionReason;
	}
	public boolean isViewedStatus() 
	{
		return viewedStatus;
	}
	public void setViewedStatus(boolean viewedStatus) 
	{
		this.viewedStatus = viewedStatus;
	}
	
	
}
