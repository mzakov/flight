package com.cooksys.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.cooksys.entity.Booking;
import com.cooksys.entity.Flight;

public class GetBooking {
	
	private long id;

	private Date created;
	
	private String username;
	
	private Set<Flight> flights;
	
	public GetBooking(Booking booking) {
		super();
		this.id = booking.getId();
		this.username = booking.getUser().getUsername();
		this.created = booking.getCreated();
		this.flights = booking.getFlights();
	}
	
	public static GetBooking read(Booking booking){
		GetBooking result = new GetBooking(booking);
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}
	
}
