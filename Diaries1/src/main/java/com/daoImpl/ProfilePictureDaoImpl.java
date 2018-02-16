package com.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProfilePictureDao;
import com.model.ProfilePicture;

@Repository
public class ProfilePictureDaoImpl implements ProfilePictureDao 
{
	 @Autowired
	 SessionFactory sessionFactory;

	@Transactional
	public void save(ProfilePicture profilePicture)
	{
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);
	}

	@Transactional
	public ProfilePicture getProfilePic(String username)
	{
		Session session=sessionFactory.getCurrentSession();
		//select * from profilepicture where username='admin'
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, username);
		return profilePicture;
	}

}
