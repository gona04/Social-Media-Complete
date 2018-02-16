package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FriendDao;
import com.model.Friend;
import com.model.User;

@Repository
public class FriendDaoImpl implements FriendDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	//GETING ALL USERS ! 
	@Transactional
	public List<User> getAllUsers(String username) 
	{
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(
				"select * from UserDetails where userName in (select userName from UserDetails where userName!=? minus (select fromId from Friend where toId=? union select toId from friend where fromId=?))");
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class);
		List<User> suggestedUsers = query.list();
		System.out.println("Size of suggested Users list is " + suggestedUsers);
		return suggestedUsers;
		/*System.out.println(username + " in FriendController");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userName != ?");
		query.setString(0, username);
		List<User> users = (List<User>) query.list();
		for(User user :users)
		{
			System.out.println(user.getUserName());
		}
		return users;*/
	}

	//SENDING A FRIEND REQUEST!
	@Transactional
	public void friendRequest(Friend friend)
	{
		Session session = sessionFactory.getCurrentSession();
		session.save(friend);
		session.flush();
	}
	
	
	//GETTING PENDING FRIEND REQUEST
	@Transactional
	public List<Friend> pendingRequests(String username) 
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Friend where toId=? and status = 'P'");
		query.setString(0, username);
		List<Friend> requestsPending = (List<Friend>) query.list();
		return requestsPending;
	}

	//FRIEND REQUEST ACCEPTED OR REJECTED!
	@Transactional
	public void updatePendingRequest(Friend friend) 
	{
		Session session = sessionFactory.getCurrentSession();
		if(friend.getStatus() == 'A')
		{
			session.update(friend);
		}
		else if(friend.getStatus() == 'D')
		{
			session.delete(friend);
		}
		session.flush();
	}

	//TO GET THE LIST OF FRIENDS
	@Transactional
	public List<Friend> listOfFriends(String username) 
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Friend where toId = ? or fromId = ? and status = 'A'");
		query.setString(0, username);
		query.setString(1, username);
		List<Friend> friends = (List<Friend>) query.list();
		return friends;
	}

}
