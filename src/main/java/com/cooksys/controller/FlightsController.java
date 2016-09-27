package com.cooksys.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Booking;
import com.cooksys.entity.Flight;
import com.cooksys.service.FlightService;
import com.cooksys.service.LocationService;

@RestController
@RequestMapping("flights")
@CrossOrigin
public class FlightsController {
	
	private final FlightService flightService;
	
	@Autowired
	public FlightsController (FlightService flightService) {
		super();
		this.flightService = flightService;
	}
	
	//GET /flights/
	@RequestMapping(method = RequestMethod.GET)
	public List<Flight> getFlightList()
	{		
		return flightService.getDailyFlightList();
	}
	
	//GET /flights/itin
	@RequestMapping(path = "/itin/{origin}/{destination}", method = RequestMethod.GET)
	public List<Booking> getItineraries(@PathVariable String origin, @PathVariable String destination )
	{
		return flightService.getItineraries(origin, destination);
	}
}
