package de.javamaps.items;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private final long id;
	private final double lat;
	private final double lon;
	private final String name;
	private Long previous = null;
	private int way_dist = Integer.MAX_VALUE; /// ersatz f�r unendlich
												/// //bisheriger Weg zu diesem
												/// Knoten
	private boolean visited;
	private List<Neighbor> neighbors = new ArrayList<Neighbor>();

	public void setAsStart() {
		this.way_dist = 0;
	}

	public Vertex(String NameIn, long idIn, double lonIn, double latIn) {
		this.name = NameIn;
		this.lat = latIn;
		this.lon = lonIn;
		this.id = idIn;
		this.visited = false;
	}

	public Long getPrevious() {
		return previous;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public void setPrevious(Long previous) {
		this.previous = previous;
	}

	public int getWay_dist() {
		return way_dist;
	}

	public void setWay_dist(int way_dist) {
		this.way_dist = way_dist;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Neighbor> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<Neighbor> neighbors) {
		this.neighbors = neighbors;
	}
	
	public void addNeighbor(Neighbor newNeighbor) {
		this.neighbors.add(newNeighbor);;
	}
	
	public void addNeighbor(long id) {
		this.neighbors.add(new Neighbor(id,Integer.MAX_VALUE));
	}
	
	public boolean hasNeighbors(){
		if (this.neighbors.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	public Neighbor nearestNeighbor(){
		int min = Integer.MAX_VALUE;
		Neighbor out = null;
		for(Neighbor n :this.neighbors){
			if (n.getDis() < min){
				out = n;
				min = n.getDis();
			}
		}
		this.neighbors.remove(out);
		return out;
	}
}
