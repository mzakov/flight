package com.cooksys.model;

import java.util.Date;
import java.util.List;

import com.cooksys.entity.User;

public class GetUser {

	private Date created;
	
	private long id;
	
	private String username;
	
	private String password;
	
//	private List<GetAllBookings> bookings;
//	
//	private List<GetAllUsers> followers;
//	
//	private List<GetAllUsers> followees;
	
	public GetUser(User user) {
		this.id = user.getId();
		this.created = user.getCreated();
		this.username = user.getUsername();
		this.password = user.getPassword();
//		this.bookings = GetAllBookings.index(user.getBooings());
//		this.followers = GetAllUsers.index(user.getFollowers());
//		this.followees = GetAllUsers.index(user.getFollowees());
	}

	public static GetUser read(User user){
		GetUser result = new GetUser(user);
		return result;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
