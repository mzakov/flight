package com.cooksys.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.AllDirectedPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.FlightGenerator;
import com.cooksys.entity.Booking;
import com.cooksys.entity.Flight;
import com.cooksys.pojo.Cities;

@Service
public class FlightService {

	private final FlightGenerator generator;

	@Autowired
	public FlightService(FlightGenerator generator) {
		this.generator = generator;
	}

	private List<Flight> flightList = new ArrayList<>();

	public List<Flight> getDailyFlightList() {
		return flightList;
	}

	// The fixedDelay parameter determines how often a new day is generated as
	// expressed in milliseconds
	@Scheduled(fixedDelay = 10000)
	private void refreshFlights() {
		flightList = generator.generateNewFlightList();
	}

	//////////////////////////////////////////////////////////////
	HashMap<Object, Flight> flightMap = new HashMap<Object, Flight>();

	public DirectedWeightedMultigraph<String, DefaultWeightedEdge> createFlightGraph() {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> airports = new DirectedWeightedMultigraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		for (Cities city : Cities.values()) {
			airports.addVertex(city.getName());
		}

		List<Flight> flights = this.getDailyFlightList();

		for (Flight flight : flights) {
			String origin = flight.getOrigin();
			String destination = flight.getDestination();
			long weight = flight.getFlightTime();
			Flight newFlight = new Flight();
			newFlight = flight;
			flightMap.put(Graphs.addEdge(airports, origin, destination, weight), newFlight);
		}
		return airports;
	}

	public List<Booking> getItineraries(String departure, String destination) {
		Graph<String, DefaultWeightedEdge> a = this.createFlightGraph();
		AllDirectedPaths<String, DefaultWeightedEdge> all = new AllDirectedPaths<String, DefaultWeightedEdge>(
				(DirectedGraph<String, DefaultWeightedEdge>) a);
		List<GraphPath<String, DefaultWeightedEdge>> allPaths = all.getAllPaths(departure, destination, true, null);
		List<Booking> itineraries = new ArrayList<Booking>();

		for (GraphPath<String, DefaultWeightedEdge> path : allPaths) {
			List<Flight> allFlights = new ArrayList<Flight>();
			for (Object e : path.getEdgeList()) {
				allFlights.add(this.flightMap.get(e));
			}
			if (allFlights.size() > 1) {
				for (int i = 0; i < allFlights.size() - 1; i++) {
					if (allFlights.get(i).getOffset() + allFlights.get(i).getFlightTime() < allFlights.get(i + 1)
							.getOffset()) {
						continue;
					} else {
						allFlights.clear();
						break;
					}
				}
			}
			if (!allFlights.isEmpty()) {
				List<Flight> newFlightSet = new ArrayList<Flight>();
				Booking booking = new Booking();
				long fightTime = 0;
				long layover = 0;
				long temp = allFlights.get(0).getOffset();
				for (int i = 0; i < allFlights.size(); i++) {
					Flight currentFlight = allFlights.get(i);
					fightTime += currentFlight.getFlightTime();
					layover += currentFlight.getOffset() - temp;
					temp = currentFlight.getFlightTime() + currentFlight.getOffset();
					newFlightSet.add(currentFlight);
				}
				booking.setFlights(newFlightSet);
				booking.setFlightTime(fightTime);
				booking.setLayover(layover);

				itineraries.add(booking);
			}
		}
		Collections.sort(itineraries);
		return itineraries;
	}
}
