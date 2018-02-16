package com.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Job 
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int jobId;
	private String jobTitle;
	private String skillsRequired;
	private String yrsOfExp;
	private float salary;
	private String companyName;
	private String location;
	/*private Date postedOn;*/
	
	public int getJobId()
	{
		return jobId;
	}
	public void setJobId(int jobId) 
	{
		this.jobId = jobId;
	}
	public String getJobTitle() 
	{
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) 
	{
		this.jobTitle = jobTitle;
	}
	public String getSkillsRequired()
	{
		return skillsRequired;
	}
	public void setSkillsRequired(String skillsRequired)
	{
		this.skillsRequired = skillsRequired;
	}
	public String getYrsOfExp()
	{
		return yrsOfExp;
	}
	public void setYrsOfExp(String yrsOfExp)
	{
		this.yrsOfExp = yrsOfExp;
	}
	public float getSalary()
	{
		return salary;
	}
	public void setSalary(float salary)
	{
		this.salary = salary;
	}
	public String getCompanyName() 
	{
		return companyName;
	}
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	public String getLocation()
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
/*	public Date getPostedOn()
	{
		return postedOn;
	}
	public void setPostedOn(Date postedOn)
	{
		this.postedOn = postedOn;
	}*/
}
