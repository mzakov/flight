package com.cooksys.entity;


import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking implements Comparable<Booking>{

	Date created;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date", insertable = false, updatable = false)
	private Date creationDate() {
		return created;
	};
	
	@PrePersist
	protected void onCreate() {
		created = new Date();
	};
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	
	@OneToMany(mappedBy = "booking", fetch=FetchType.EAGER)
	private List<Flight> flights;
	
	@Column(name="layover")
	private long layover;
	
	@Column(name="flightTime")
	private long flightTime;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public long getLayover() {
		return layover;
	}

	public void setLayover(long layover) {
		this.layover = layover;
	}

	public long getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(long flightTime) {
		this.flightTime = flightTime;
	}

	@Override
	public int compareTo(Booking compareBooking) {
		long compareFlightTime = ((Booking) compareBooking).getFlightTime();
		
		
		return (int) (this.flightTime - compareFlightTime);
	}
	

}
