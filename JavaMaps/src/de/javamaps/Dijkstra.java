package de.javamaps;

import java.util.HashMap;
import java.util.Map.Entry;

import de.javamaps.items.*;

/**
 * @author Yannick
 * @version 1.03
 * @since 1.8.0_91
 */
public class Dijkstra {

	/**
	 * @param start = id of the Startvertex
	 * @param end = id of the Endvertex
	 * @param graphMap - with all Vertex + Neighbors and calculated Neighbor-Distance
	 * @return StringBuffer which contains the console output
	 */
	public static StringBuffer getshortestWay(Long start, Long end, HashMap<Long, Vertex> graphMap) {
		StringBuffer output = new StringBuffer();
		try {
			/// start setzten - �berpr�ft durch try catch auch automatisch on
			/// der Punkt start �berhaupt existiert
			graphMap.get(start).setAsStart();

			try {
				// �berpr�fung ob end in der Map �berhaupt existiert
				graphMap.get(end).getName();

				try {
					// solange der der end-Punkt noch nicht "besucht" wurde
					// Funktion f�r
					// Knoten mit der K�rzesten way_dist ausw�hlen
					while (!graphMap.get(end).isVisited()) {
						Vertex inuse = graphMap.get(getNext(graphMap));
						// n�hsten Nachbarn ausw�hlen
						if (inuse.hasNeighbors()) {
							Neighbor nextN = inuse.nearestNeighbor();
							Vertex nextV = graphMap.get(nextN.getName());
							if (!nextV.isVisited()) {
								int newDis = inuse.getWay_dist() + nextN.getDis();
								if (newDis < nextV.getWay_dist()) {
									nextV.setWay_dist(inuse.getWay_dist() + nextN.getDis());
									nextV.setPrevious(inuse.getId());
								}
							}
						} else {
							// sobald der Knoten abgearbeitet ist (d.h. er hat
							// keine
							// offenen Nachbarn mehr) wird er als besucht
							// gesetzt
							inuse.setVisited(true);
						}
					}
					// Ausgabe nach Abschluss des Algorithmus
					output.append("Total Distance from ");
					output.append(graphMap.get(start).getName());
					output.append(" to ");
					output.append(graphMap.get(end).getName());
					output.append(" is: ");
					output.append(graphMap.get(end).getWay_dist());
					output.append("\n");
					output.append("Way was:\n");
					try {
						output.append(getFullWay(graphMap.get(end), graphMap));
					} catch (Exception e) {
						output.append(e);
					}

				} catch (Exception e) { // wenn der Algorithmus nicht
										// determiniert
					output.append("cant reach ");
					output.append(end);
					output.append("\n");
				}
			} catch (Exception e) {
				output.append("Cant find ");
				output.append(end);
				output.append(" in our map");
				output.append("\n");
			}
		} catch (Exception e) {
			output.append("Cant find ");
			output.append(start);
			output.append(" in our map");
			output.append("\n");
		}
		return output;
	}

	/**
	 * @param vertex
	 * @param graphMap
	 * @return Stringbuffer that list the way from a vertex back to the start vertex of the graphMap
	 */
	private static StringBuffer getFullWay(Vertex vertex, HashMap<Long, Vertex> graphMap) {
		StringBuffer output = new StringBuffer();
		if (vertex.getPrevious() != null) {
			Vertex previous = graphMap.get(vertex.getPrevious());
			output.append(getFullWay(previous, graphMap));
		}
		output.append(vertex.getId());
		output.append(" (name = ");
		output.append(vertex.getName());
		output.append(")");
		output.append("\n");
		return output;
	}

	/**
	 * @param graphMap
	 * @return id of the nearest open Vertex
	 */
	private static Long getNext(HashMap<Long, Vertex> graphMap) {
		Long out = null;
		int min = Integer.MAX_VALUE;
		for (Entry<Long, Vertex> e : graphMap.entrySet()) {
			/// Wenn noch offene Knoten bestehen kleinsten weg ermitteln
			if (!e.getValue().isVisited() && e.getValue().getWay_dist() < min) {
				min = e.getValue().getWay_dist();
				out = e.getKey();
			}
		}
		return out;
	}
}
