package com.cooksys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.Booking;
import com.cooksys.entity.Flight;
import com.cooksys.entity.User;
import com.cooksys.model.GetAllBookings;
import com.cooksys.model.GetBooking;
import com.cooksys.repository.BookingRepository;
import com.cooksys.repository.FlightRepository;
import com.cooksys.repository.UserRepository;

@Service
public class BookingService{
	
	private final BookingRepository bookingRepo;
	private final UserRepository userRepo;
	private final FlightRepository flightRepo;
	
	@Autowired
	public BookingService(BookingRepository bookingRepo, UserRepository userRepo, FlightRepository flightRepo) {
		super();
		this.bookingRepo = bookingRepo;
		this.userRepo = userRepo;
		this.flightRepo = flightRepo;
	}

	//GET /booking/allBookings

	public List<GetAllBookings> index() {
		return GetAllBookings.index(bookingRepo.findAll());
	}

	//POST /booking

	public Booking create(Booking booking) {
		Booking savedB = bookingRepo.save(booking);
		
//		long bId = savedB.getId(); 
		for(Flight flight : savedB.getFlights()){
			flight.setBooking(savedB);
			flightRepo.saveAndFlush(flight);
		}
		return savedB;
	}

	//GET /booking/{id}

	public Booking read(long id) {
		return bookingRepo.findOne(id);
	}

	//PATCH /booking/{id}

	public Booking update(long id, Booking bookingToUpdate) {
		bookingToUpdate.setId(id);
		return bookingRepo.save(bookingToUpdate);
	}

	//DELETE /booking/{id}

	public Booking delete(long id) {
		Booking result = this.read(id);
		bookingRepo.delete(id);
		return result;
	}

	

}
