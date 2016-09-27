package com.cooksys.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.cooksys.entity.Booking;
import com.cooksys.entity.Flight;

public class GetAllBookings {

	private long id;

	private Date created;
	
	private String username;
	
	private List<Flight> flights;

	public GetAllBookings(long id, String username, Date created, List<Flight> list) {
		super();
		this.id = id;
		this.username = username;
		this.created = created;
		this.flights = list;
	}

	public static List<GetAllBookings> index(List<Booking> list) {
		ArrayList<GetAllBookings> result = new ArrayList<>();
		for (Booking booking : list)
			result.add(new GetAllBookings(booking.getId(), booking.getUser().getUsername(), booking.getCreated(), booking.getFlights()));
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

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}



}
