package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.JobDao;
import com.model.Job;

@Repository
public class JobDaoImpl implements JobDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void saveJob(Job job) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.save(job);
		System.out.println("in job daoImpl"+"        "+job.getLocation());
		session.flush();
	}

	@Transactional
	public List<Job> getAllJobs() 
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Job");
		List<Job> jobs = (List<Job>) query.list();
		return jobs;
	}

	@Transactional
	public Job getJobById(int id) 
	{
		Session session = sessionFactory.getCurrentSession();
		Job job = (Job) session.get(Job.class, id);
		return job;
	}

}
