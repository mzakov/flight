package com.cooksys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.AllDirectedPaths;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.FlightGenerator;
import com.cooksys.pojo.Cities;
import com.cooksys.pojo.Flight;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;

	private ArrayList<Flight> flightList = new ArrayList<>();
	
	public ArrayList<Flight> getDailyFlightList()
	{
		return flightList;
	}
	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=1000)
	private void refreshFlights()
	{
		flightList = generator.generateNewFlightList();
	}
	
//////////////////////////////////////////////////////////////	
	HashMap<Object, Flight> flightMap = new HashMap<Object, Flight>();
	
	public DirectedWeightedMultigraph<String, DefaultWeightedEdge> createFlightGraph()
    {
		DirectedWeightedMultigraph<String, DefaultWeightedEdge> airport =
                new DirectedWeightedMultigraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for(Cities city : Cities.values()) {
			airport.addVertex(city.getName());
		}
		
		ArrayList<Flight> flights = this.getDailyFlightList();
		
		
		
		for(Flight flight : flights) {
			String origin = flight.getOrigin();
			String destination = flight.getDestination();
			long weight = flight.getFlightTime();
			Flight newFlight = new Flight();
			newFlight = flight;
			flightMap.put(Graphs.addEdge(airport, origin, destination, weight), newFlight);
		}
		
		for(DefaultWeightedEdge e : airport.edgeSet()){

             System.out.println(airport.getEdgeSource(e) + " --> " + airport.getEdgeTarget(e) + ".    flight time: " + airport.getEdgeWeight(e));
         }
		return airport;
    }
	
	public HashSet<ArrayList<Flight>> getItineraries(String departure, String destination)
    {
			Graph<String, DefaultWeightedEdge> a = this.createFlightGraph();
			
			AllDirectedPaths<String, DefaultWeightedEdge> all = new AllDirectedPaths<String, DefaultWeightedEdge>((DirectedGraph<String, DefaultWeightedEdge>) a);
			
			List<GraphPath<String, DefaultWeightedEdge>> allPaths = all.getAllPaths(departure, destination, true, null);
			
			HashSet<ArrayList<Flight>> itineraries = new HashSet<ArrayList<Flight>>();
			
			ArrayList<Flight> allFlights = new ArrayList<Flight>();
			
			
			
			
			for(GraphPath<String, DefaultWeightedEdge> path : allPaths){
				
				allFlights.clear();
				
				Flight newFlight = new Flight();

				for(Object e : path.getEdgeList()){
					
					newFlight.setDestination(this.flightMap.get(e).getDestination());
					newFlight.setOrigin(this.flightMap.get(e).getOrigin());
					newFlight.setFlightTime(this.flightMap.get(e).getFlightTime());
					newFlight.setOffset(this.flightMap.get(e).getOffset());
					
					allFlights.add(newFlight);
				}
				
				if(allFlights.size() > 1) {
					for(int i = 0; i < allFlights.size()-1; i++) {
						
						if(allFlights.get(i).getOffset()+allFlights.get(i).getFlightTime() <
								allFlights.get(i+1).getOffset()){
							continue;
						} else {
							allFlights.clear();
							break;
						}
					} 

				}
				if(!allFlights.isEmpty()){
					ArrayList<Flight> newAllFlights = new ArrayList<Flight>();
					
					for(int i = 0; i < allFlights.size(); i++){
						newAllFlights.add(allFlights.get(i));
					}
					
					itineraries.add(newAllFlights);
					System.out.println(itineraries.toString());
					System.out.println(newAllFlights.toString());
				}
			}
//			System.out.println(itineraries.toString());
			System.out.println(all.getAllPaths(departure, destination, true, null));
//            DijkstraShortestPath<String, DefaultWeightedEdge> p = new DijkstraShortestPath<String, DefaultWeightedEdge>(a, departure, destination);
//            System.out.println(DijkstraShortestPath.findPathBetween(a, departure, destination));
//            System.out.println("The total time of this journey is: "  + p.getPathLength());
//			System.out.println(itineraries.toString());
			return itineraries;      
    }
	
	
}
