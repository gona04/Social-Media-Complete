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

import com.dao.FriendDao;
import com.dao.UserDao;
import com.model.Error;
import com.model.Friend;
import com.model.User;

@Controller
public class FriendController 
{
	@Autowired
	FriendDao friendDao;
	@Autowired
	UserDao userDao;
	
	//GET THE LIST OF USERS
	@RequestMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers(HttpSession session)
	{
		System.out.println("in friend controller");
		String username = (String) session.getAttribute("username");
		if(username == null)
		{
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<User> users = (List<User>)friendDao.getAllUsers(username);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	//SEND FRIEND REQUEST
	@RequestMapping(value="/sendFriendRequest/{toId}", method = RequestMethod.POST)
	public ResponseEntity<?> sendFriendRequest(@PathVariable String toId,HttpSession session)
	{
		String username = (String) session.getAttribute("username");
		if(username == null)
		{
			Error error = new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println(username + " send friend request to " + toId);
		Friend friend = new Friend();
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('P');
		friendDao.friendRequest(friend);
		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	//SEND LIST OF PENDING REQUESTS
	@RequestMapping(value="/pendingRequests")
	public ResponseEntity<?> pendingFriendRequests(HttpSession session)
	{
		String username = (String)session.getAttribute("username");
		
		if(username == null)
		{
			Error error = new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Friend> pendingRequests = friendDao.pendingRequests(username);
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}
	
	//GET USER BY USERiD
	@RequestMapping("/getUserByUsername/{fromId}")
	public ResponseEntity<?> getuserByUsername(@PathVariable String fromId,HttpSession session)
	{
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5 ,"Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUserByUsername(fromId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	//UPDATE FRIEND REQUEST EITHER ACCEPT OR REJECT
	@RequestMapping(value="/updateFriendRequest", method=RequestMethod.PUT)
	public ResponseEntity<?> updateFriendRequest(@RequestBody Friend friend,HttpSession session)
	{
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5,"unAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		friendDao.updatePendingRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	//TO GET THE LIST OF FRIENDS
	@RequestMapping("/listOfFriends")
	public ResponseEntity<?> listOfFriends(HttpSession session)
	{
		String username = (String) session.getAttribute("username");
		if(username == null)
		{
			Error error = new Error(5,"UNAUTHORIZED USER");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Friend> friends = friendDao.listOfFriends(username);
		return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
	}
}
