package com.controller;

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

import com.dao.JobDao;
import com.dao.UserDao;
import com.model.Error;
import com.model.Job;
import com.model.User;

@Controller
public class JobController
{
	@Autowired
	JobDao jobDao;
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/saveJob", method = RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session)
	{
		System.out.println("in save job controller" +"  "+session.getAttribute("username"));
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5,"unAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username = (String) session.getAttribute("username");
		User user = userDao.getUserByUsername(username);
		System.out.println("after getting user by userName" + "   " + user.getRole());
		if(user.getRole().equals("ADMIN"))
		try
		{
			System.out.println("in job controller try method");
			jobDao.saveJob(job);
			System.out.println(job.getJobTitle()+"\n"+job.getCompanyName());
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage() +"\n"+e.getStackTrace());
			Error error = new Error(7,"Unable to insert Job details" + e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else 
		{
			Error error = new Error(6,"Access Denied");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/getAllJobs", method = RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5,"unAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Job> jobs = jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getjobbyid/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int id, HttpSession session)
	{
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5,"unAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Job job = jobDao.getJobById(id);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
}
