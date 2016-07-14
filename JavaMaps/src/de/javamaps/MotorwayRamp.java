package de.javamaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import de.javamaps.items.Vertex;

public class MotorwayRamp {
	public static TreeMap<String, List<Long>> getMotorwayRamps(TreeMap<Long, Vertex> graph) {
		TreeMap<String, List<Long>> out = new TreeMap<String, List<Long>>();
		String name = null;
		Long singleId = null;
		
		// �ber den komplette Graphen iterieren
		for (Entry<Long, Vertex> e : graph.entrySet()) {

			// Hat die Vertex einen Namen, so wird der Knoten als m�glicher
			// Startpunkt gewertet.
			if (!e.getValue().getName().equals("null")) {
				name = e.getValue().getName();
				singleId = e.getKey();

				// Ist schon eine Auffahrt mit diesem Namen vorhanden, so soll
				// die neue ID angeh�ngt werden.
				if (out.containsKey(name)) {
					out.get(name).add(singleId);
				}
				// Ist noch keine Auffahrt mit diesem Namen vorhanden, so
				// wird ein neuer Eintrag erstellt
				else {
					List<Long> temp = new ArrayList<Long>();
					temp.add(singleId);
					out.put(name, temp);
				}
				//F�r die n�chste Iteration wieder die names und IDs zur�cksetzen
				name = null;
				singleId = null;
			}
		}
		System.out.println("Namenliste erzeugt....");
		return out;
	}
}
