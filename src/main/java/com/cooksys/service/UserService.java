package com.cooksys.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.Booking;
import com.cooksys.entity.User;
import com.cooksys.model.GetAllUsers;
import com.cooksys.model.GetUser;
import com.cooksys.repository.UserRepository;


@Service
public class UserService{

	private final UserRepository userRepo;
	
	@Autowired
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	//GET /user/allUsers
	
	public List<GetAllUsers> index() {
		return GetAllUsers.index(userRepo.findAll());
	}

	//POST /user/
	
	public User create(User user) {
		return userRepo.saveAndFlush(user);
	}

	//GET /user/{id}
	
	public GetUser read(long id) {
		return GetUser.read(userRepo.findOne(id));
	}

	// PATCH /user/{id}
	
	public User update(long id, User userToUpdate) {
		userToUpdate.setId(id);
		return userRepo.saveAndFlush(userToUpdate);
	}

	//DELETE /user/{id}
	
	public User delete(long id) {
		User result = userRepo.findOne(id);
		userRepo.delete(id);
		return result;
	}

	//POST /user/login
	
	public GetUser auth(User userToAuth) {
		return GetUser.read(userRepo.findByUsername(userToAuth.getUsername()));
	}

	//GET /user/{id}/bookings
	public Set<Booking> readBookings(long id) {
		return userRepo.findOne(id).getBookings();
	}
	
}
