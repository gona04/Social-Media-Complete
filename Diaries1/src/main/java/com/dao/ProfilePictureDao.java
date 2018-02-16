package com.dao;

import com.model.ProfilePicture;

public interface ProfilePictureDao 
{

	void save(ProfilePicture profilePicture);
	ProfilePicture getProfilePic(String username);
	
}
