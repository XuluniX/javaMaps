package de.javamaps.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import de.javamaps.Dijkstra;
import de.javamaps.items.Neighbor;
import de.javamaps.items.Vertex;

public class DijkstraTest {

	private HashMap<Long, Vertex> testData(){
		HashMap<Long, Vertex> testData = new HashMap<Long, Vertex>();
	// Testgraph
	testData.put((long) 1, new Vertex("Stadt1", 1, 0, 0));
	testData.put((long) 2, new Vertex("Stadt2", 2, 0, 0));
	testData.put((long) 3, new Vertex("Stadt3", 3, 0, 0));
	testData.get((long) 1).addNeighbor(new Neighbor(2, 1));
	testData.get((long) 1).addNeighbor(new Neighbor(3, 5));
	testData.get((long) 2).addNeighbor(new Neighbor(3, 1));
	return testData;
	}
	@Test
	public void testAll() {
		StringBuffer shouldBe = new StringBuffer("Total Distance from Stadt1 to Stadt3 is: 2\nWay was:\n1 (name = Stadt1)\n2 (name = Stadt2)\n3 (name = Stadt3)\n");
		StringBuffer is = new StringBuffer(Dijkstra.getshortestWay((long)1,(long) 3,testData()));
		assertEquals(shouldBe.toString(),is.toString());
		assertEquals("Cant find 4 in our map\n",Dijkstra.getshortestWay((long)4,(long) 3,testData()).toString());
		assertEquals("Cant find 4 in our map\n",Dijkstra.getshortestWay((long)1,(long) 4,testData()).toString());
	}
}