package com.cooksys.controller;

import java.util.ArrayList;
import java.util.HashSet;

import org.jgrapht.Graphs;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.pojo.Flight;
import com.cooksys.service.FlightService;
import com.cooksys.service.LocationService;

@RestController
@RequestMapping("flights")
@CrossOrigin
public class FlightsController {
	
	private final FlightService flightService;
	
	private final LocationService locationService;
	
	@Autowired
	public FlightsController (FlightService flightService, LocationService locationService) {
		super();
		this.flightService = flightService;
		this.locationService = locationService;
	}
	
	//GET /flights/
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Flight> getFlightList()
	{		
		return flightService.getDailyFlightList();
	}
	
	//GET /flights/itin
	@RequestMapping(path = "/itin/{origin}/{destination}", method = RequestMethod.GET)
	public HashSet<ArrayList<Flight>> getItineraries(@PathVariable String origin, @PathVariable String destination )
	{
		return flightService.getItineraries(origin, destination);
	}
}
