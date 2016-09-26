package com.cooksys.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cooksys.entity.Booking;
import com.cooksys.entity.User;
import com.cooksys.model.GetAllUsers;
import com.cooksys.model.GetUser;
import com.cooksys.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController (UserService userService) {
		super();
		this.userService = userService;
	}

	//GET /users/allUsers
	@RequestMapping(method = RequestMethod.GET)
	public List<GetAllUsers> index() {
		return userService.index();
	} 
	
	//POST /users
	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return userService.create(user);
	}
	
	//GET /users/{id}
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public GetUser read(@PathVariable long id) {
		return userService.read(id);
	}
	
	//GET /users/{id}/bookings
		@RequestMapping(path = "{id}/bookings", method = RequestMethod.GET)
		public Set<Booking> readBookings(@PathVariable long id) {
			return userService.readBookings(id);
		}
	
	//PATCH /users/{id}
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public User update(@PathVariable long id, @RequestBody User userToUpdate) {
		return userService.update(id, userToUpdate);
	}
	
	//DELETE /users/{id}
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public User delete(@PathVariable long id) {
		return userService.delete(id);
	}
	
	//POST /users/login
	@RequestMapping(path = "/login/", method = RequestMethod.POST)
	public GetUser auth(@RequestBody User userToAuth) {
		return userService.auth(userToAuth);
	}
}
