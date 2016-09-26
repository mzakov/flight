package com.cooksys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "flights")
public class Flight{
	
	public Flight(String origin, String destination, long flightTime, long offset) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightTime = flightTime;
		this.offSet = offset;
	}

	public Flight() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return "leaves " + this.origin + " at " + this.offSet +
				" and arrives in " + this.destination + " at " +
				(this.offSet+this.flightTime) + " flight time: " + this.flightTime;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long id;
	
	//Name of city where flight originates
	@Column
	private String origin;
	
	//Name of city where flight lands
	@Column
	private String destination;
	
	//How many hours flight is in the air
	@Column
	private long flightTime;
	
	//How many hours after the start of the day until the flight takes off
	@Column
	private long offSet;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public long getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(long flightTime) {
		this.flightTime = flightTime;
	}
	public long getOffset() {
		return offSet;
	}
	public void setOffset(long offset) {
		this.offSet = offset;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}


	
}
