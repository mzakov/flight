package com.cooksys.pojo;

import org.jgrapht.Graphs;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;

import com.cooksys.service.FlightService;


public class Itineraries {
	
	@Autowired
	FlightService flightService;
	
	public SimpleDirectedWeightedGraph<String, Flight> createFlightGraph()
    {
		SimpleDirectedWeightedGraph<String, Flight> airport =
                new SimpleDirectedWeightedGraph<String, Flight>(Flight.class);
		
		for(Cities city : Cities.values()) {
			airport.addVertex(city.getName());
		}
		
		for(Flight flight : flightService.getDailyFlightList()) {
			Graphs.addEdge(airport, flight.getOrigin(), flight.getDestination(), flight.getFlightTime());
		}
		
		return airport;
    }

}
