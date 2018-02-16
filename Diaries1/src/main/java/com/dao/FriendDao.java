package com.dao;

import java.util.List;

import com.model.Friend;
import com.model.User;

public interface FriendDao 
{
	List<User> getAllUsers(String username);
	void friendRequest(Friend friend);
	List<Friend> pendingRequests(String username);
	void updatePendingRequest(Friend friend);
	List<Friend> listOfFriends(String username);
}
