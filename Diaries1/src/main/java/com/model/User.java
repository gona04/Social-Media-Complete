package com.model;

import javax.persistence.*;

@Entity
@Table(name="UserDetails")
public class User 
{
	@Id
	private String userName;
	@Column(nullable=false)
	private String password;
	@Column(unique=true,nullable=false)
	private String email;
	private String role;
	private String firstName;
	private String lastName;
	@Column(name="online_status")
	private boolean online;
	
	
	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getRole() 
	{
		return role;
	}
	public void setRole(String role) 
	{
		this.role = role;
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public boolean isOnline()
	{
		return online;
	}
	public void setOnline(boolean online)
	{
		this.online = online;
	}
	
	
	
}
