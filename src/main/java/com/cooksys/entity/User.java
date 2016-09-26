package com.cooksys.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User {

	Date created;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable=false)
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
	
	@Column(name="username", updatable = false, nullable=false)
	private String username;
	
	@Column(name="password", updatable = false, nullable=false)
	private String password;
	
	@Column
	
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	private Set<Booking> bookings;
	
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

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

}
