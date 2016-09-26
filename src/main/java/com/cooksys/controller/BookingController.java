package com.cooksys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cooksys.entity.Booking;
import com.cooksys.model.GetAllBookings;
import com.cooksys.model.GetBooking;
import com.cooksys.service.BookingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bookings")
public class BookingController {

	private final BookingService bookingService;
	
	@Autowired
	public BookingController (BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}
	
	//GET /booking/allBookings
	@RequestMapping(method = RequestMethod.GET)
	public List<GetAllBookings> index() {
		return bookingService.index();
	}
		
	//POST /booking
	@RequestMapping(method = RequestMethod.POST)
	public Booking create(@RequestBody Booking booking) {
		return bookingService.create(booking);
	}
		
	//GET /booking/{id}
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Booking read(@PathVariable long id) {
		return bookingService.read(id);
	}
		
	//PATCH /booking/{id}
	@RequestMapping(value = "{id}", method = RequestMethod.PATCH)
	public Booking update(@PathVariable long id, @RequestBody Booking bookingToUpdate) {
		return bookingService.update(id, bookingToUpdate);
	}
		
	//DELETE /booking/{id}
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Booking delete(@PathVariable long id) {
		return bookingService.delete(id);
	}
}
