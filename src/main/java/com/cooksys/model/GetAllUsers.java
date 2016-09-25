package com.cooksys.model;

import java.util.ArrayList;
import java.util.List;
import com.cooksys.entity.User;

public class GetAllUsers {

	private long id;

	private String username;

	public GetAllUsers(long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public static List<GetAllUsers> index(List<User> list) {
		ArrayList<GetAllUsers> result = new ArrayList<>();
		for (User user : list)
			result.add(new GetAllUsers(user.getId(), user.getUsername()));
		return result;
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

}
