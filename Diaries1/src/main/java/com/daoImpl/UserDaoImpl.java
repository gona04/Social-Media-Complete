package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.model.User;

@Repository
public class UserDaoImpl implements UserDao 
{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void registerUser(User user) 
	{
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Transactional
	public boolean isValidEmail(String email)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where email = ?");
		query.setString(0, email);
		User user = (User) query.uniqueResult();
		
		if(user == null)
			return true;
		
		else
		return false;
	}

	@Transactional
	public boolean isValidUserName(String userName) 
	{
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, userName);
		
		if (user == null)
			return true;
		
		else
		return false;
	}

	@Transactional
	public User login(User user) 
	{
		System.out.println("in userDao");
		System.out.println(user.getUserName()+"\n"+ user.getPassword());
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userName = ? and password = ?");
		query.setString(0, user.getUserName());
		query.setString(1, user.getPassword());
		User user1 = (User)query.uniqueResult();
		return user1;
	}

	@Transactional
	public void update(User user) 
	{	
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		session.flush();
	}
	
	@Transactional
	public User getUserByUsername(String username) 
	{
		Session session =sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, username);
		return user;
	}
	
}
