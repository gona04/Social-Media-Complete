package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.UserDao;
import com.model.Error;
import com.model.User;

@Controller
public class UserController 
{
	@Autowired
	UserDao userDao;
	
	//REGISTRATION OR SIGNUP
	@RequestMapping(value="/register")
	public ResponseEntity<?> register(@RequestBody User user)
	{
		if(!userDao.isValidEmail(user.getEmail()))
		{
			Error error = new Error(2,"Duplicate email id ");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(!userDao.isValidUserName(user.getUserName()))
		{
			Error error = new Error(3,"username already exists please create a new one! ");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		
		try
		{
			userDao.registerUser(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error error = new Error(1,"Unable to register" + e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//FOR LOGIN!
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody User user,HttpSession session)
	{
		System.out.println("in login controller");
		User validUser = userDao.login(user);
		if(validUser == null)
		{
			Error error = new Error(4,"Invalid username/password Please enter a valid username/password");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		validUser.setOnline(true);
		userDao.update(validUser);
		session.setAttribute("username", validUser.getUserName());
		System.out.println(validUser.getUserName() + " "+validUser.getPassword());
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
	}
	
	//FOR LOGOUT!
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session)
	{
		System.out.println("in logout controller before if");
		if(session.getAttribute("username") == null)
		{
			System.out.println("in logout if controller");
			Error error = new Error(5,"Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String userName = (String) session.getAttribute("username");
		System.out.println("username is "+ userName);
		User user = userDao.getUserByUsername(userName);
		user.setOnline(false);
		session.removeAttribute("username");
		System.out.println("in controller logout after if"+"\n"+session.getAttribute("username"));
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//TO GET ALL USERS
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session )
	{
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5,"unAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUserByUsername((String)session.getAttribute("username"));
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	//TO UPDATE A USER
	@RequestMapping(value = "/userUpdate",method = RequestMethod.PUT)
	public ResponseEntity<?> userUpdate(@RequestBody User user,HttpSession session)
	{
		if(session.getAttribute("username") == null)
		{
			System.out.println("in update User Printing error");
			Error error = new Error(5,"Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		User user1 = user;
		userDao.update(user1);
		System.out.println("user Updated successfully! in controller");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//GET USER BY USERNAME
	@RequestMapping("/getUserDetails/{fromId}")
	public ResponseEntity<?> getUserDetails(@PathVariable String fromId,HttpSession session)
	{
		if(session.getAttribute("username") == null)
		{
			Error error = new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUserByUsername(fromId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
