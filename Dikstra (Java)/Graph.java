package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
	// All edges that stare from this vertex
	private HashMap<Vertex, ArrayList<Edge>> fromEdges; // HashMap with a vertex as a key and with that vertex we can
														// get the list of edges.
	private HashMap<Vertex, ArrayList<Edge>> toEdges; // All edges that go to this vertex

	public Graph() {
		fromEdges = new HashMap<Vertex, ArrayList<Edge>>();
		toEdges = new HashMap<Vertex, ArrayList<Edge>>();
	}
//all edges

	public Vertex addVertex(String label) {
		Vertex newVertex = new Vertex(label);
		fromEdges.put(newVertex, new ArrayList<Edge>());
		toEdges.put(newVertex, new ArrayList<Edge>());
		return newVertex;
	}

	public Edge addDirectedEdge(Vertex fromVertex, Vertex toVertex, int weight) {
		if (hasEdge(fromVertex, toVertex)) { // this is to stop from adding a edge twice
			return null;
		}
		Edge newEdge = new Edge(fromVertex, toVertex, weight);

		fromEdges.get(fromVertex).add(newEdge);
		toEdges.get(toVertex).add(newEdge);

		return newEdge;
	}

	public boolean hasEdge(Vertex fromVertex, Vertex toVertex) {
		if (fromEdges.containsKey(fromVertex) == false) {
			return false; // the from vertex was not found
		}
		ArrayList<Edge> edges = fromEdges.get(fromVertex);
		for (Edge e : edges) {
			if (e.toVertex == toVertex) {
				return true;
			}
		}

		return false;
	}

	public int getEdgeWeight(Vertex fromVertex, Vertex toVertex) { // just a function for testing
		if (fromEdges.containsKey(fromVertex) == false) {
			return 0; // the from vertex was not found
		}
		ArrayList<Edge> edges = fromEdges.get(fromVertex);
		for (Edge e : edges) {
			if (e.toVertex == toVertex) {
				return e.weight;
			}
		}
		return 0;
	}

	public Edge getEdge(Vertex fromVertex, Vertex toVertex) { // just a function for testing
		if (fromEdges.containsKey(fromVertex) == false) {
			return null; // the from vertex was not found
		}
		ArrayList<Edge> edges = fromEdges.get(fromVertex);
		for (Edge e : edges) {
			if (e.toVertex == toVertex) {
				return e;
			}
		}
		return null;
	}

	public ArrayList<Vertex> BreadthFirstSearch(Vertex StartVertex) {
		HashSet<Vertex> discoveredSet = new HashSet<Vertex>(); // using a HashSet so we don't have duplicates
		Queue<Vertex> queue = new LinkedList<Vertex>(); // I chose to do a linked list for my queue
		ArrayList<Vertex> visited = new ArrayList<Vertex>();

		HashMap<Vertex, Integer> distances = new HashMap<Vertex, Integer>(); // this is the list of distances to each
																				// vertex

		distances.put(StartVertex, 0);
		queue.add(StartVertex);
		discoveredSet.add(StartVertex);

		while (queue.size() > 0) {
			Vertex cur = queue.remove();
			visited.add(cur);
			for (Edge e : fromEdges.get(cur)) { // for each edge that that vertex has, search it.
				Vertex adjacent = e.toVertex; // do not visit it, but add it to the queue, to be visited.
				if (discoveredSet.contains(adjacent) == false) { // if we haven't searched this one yet
					queue.add(adjacent); // add them to the queue
					discoveredSet.add(adjacent); // add them to the discovered set
					distances.put(adjacent, distances.get(cur) + 1); // the distance of any adjacent vertex will be one
																		// larger than the original
				}
			}
		}
		return visited; // returns the order in which the BFS was performed.
	}

	public ArrayList<Vertex> DepthFirstSearch(Vertex StartVertex) {
		Stack<Vertex> stack = new Stack<Vertex>(); // I chose to do a linked list for my queue
		ArrayList<Vertex> visited = new ArrayList<Vertex>();

		stack.push(StartVertex);

		while (stack.size() > 0) {
			Vertex cur = stack.pop();
			if (visited.contains(cur) == false) {
				System.out.println(cur.name + " ");
				visited.add(cur);

				for (Edge e : fromEdges.get(cur)) { // for each edge that that vertex has, search it.
					Vertex adjacent = e.toVertex;
					stack.push(adjacent);
				}
			}
		}
		return visited;
	}

	public HashSet<Edge> getEdges() {
		HashSet<Edge> edges = new HashSet<Edge>(); // Using a HashSet because it doesn't allow duplicates so you can
													// return all edges correctly.
		for (ArrayList<Edge> listOfEdges : fromEdges.values()) { // take all the edges from every vertex(edge list in
			// the hashMap) and add them to a hashSet.
			edges.addAll(listOfEdges);
		}
		return edges;
	}

//all edges that start here.
	public ArrayList<Edge> getEdgesFrom(Vertex fromVertex) {
		return fromEdges.get(fromVertex);
	}

	public ArrayList<Edge> getEdgesTo(Vertex toVertex) {
		return toEdges.get(toVertex);
	}

// finds a vertex with a matching label
	public Vertex getVertex(String vertexLabel) {
		for (Vertex v : getVertices()) {
			if (vertexLabel.equals(v.name)) {
				return v;
			}
		}
		return null;// returns null if the vertex was not found
	}

	public Set<Vertex> getVertices() {
		return fromEdges.keySet();

	}

	public HashMap<Vertex, VertexInfo> dijkstraShortestPath(Vertex startVertex) {
		HashMap<Vertex, VertexInfo> info;
		info = new HashMap<Vertex, VertexInfo>();
		PriorityQueue<VertexInfo> unvisted;
		unvisted = new PriorityQueue<VertexInfo>();

		for (Vertex vertex : this.getVertices()) {
			VertexInfo newVertexInfo = new VertexInfo(vertex);
			unvisted.add(newVertexInfo); // add to the priority queue and the HashMap
			info.put(vertex, newVertexInfo);
		}

		VertexInfo startVertexInfo = info.get(startVertex); // the starting vertex should have a distance of 0
		startVertexInfo.distance = 0;
		unvisted.remove(startVertexInfo);
		unvisted.add(startVertexInfo); // removes and re adds the starting vertex to make sure that it is at the top of
										// the prioritiy queue

		while (unvisted.size() > 0) {
			VertexInfo cur = unvisted.remove();
			for (Edge edge : this.getEdgesFrom(cur.vertex)) {
				Vertex adjacent = edge.toVertex;
				int newPathDistance = cur.distance + edge.weight; // add the current distance to this vertex with the
																	// weight of the current edge.
				VertexInfo adjacentInfo = info.get(adjacent); // gets the info for the adjacent vertex
				if (newPathDistance < adjacentInfo.distance) { // if a shorter distance is found
					unvisted.remove(adjacentInfo);
					adjacentInfo.distance = newPathDistance;
					adjacentInfo.Predecessor = cur.vertex;
					unvisted.add(adjacentInfo);
				}

			}
		}
		return info;
	}

	public String getShortestPath(Vertex start, Vertex End, HashMap<Vertex, VertexInfo> list) {
		String route = "";
		Vertex cur = End;
		while (cur != start) {
			route = cur.name + route;
			route = "-> " + route;
			cur = list.get(cur).Predecessor;
		}
		route = start.name + route;
		return route;
	}

	public Edge addEdge(int fromVertexNum, int toVertexNum, int weight) { // this method will make copying the c++ code
																			// that was given a bit easier
		Set<Vertex> list = this.fromEdges.keySet();
		Vertex fromVertex = null;
		Vertex toVertex = null;
		for (Vertex v : list) {
			if (v.name.equals(((Integer) fromVertexNum).toString())) {
				fromVertex = v;
			} else if (v.name.equals(((Integer) toVertexNum).toString())) { // this might be an inefficient way of doing
																			// it, but its just a way to make main a bit
																			// easier to create
				toVertex = v; // edges with numbers for their name instead of strings for their name.
			}
		}

		if (hasEdge(fromVertex, toVertex)) { // this is to stop from adding a edge twice
			return null;
		}
		Edge newEdge = new Edge(fromVertex, toVertex, weight);

		fromEdges.get(fromVertex).add(newEdge);
		toEdges.get(toVertex).add(newEdge);
		if (hasEdge(toVertex, fromVertex)) {
			return null;
		}
		Edge newEdge2 = new Edge(toVertex, fromVertex, weight); // for an undirected edge
		toEdges.get(fromVertex).add(newEdge2);
		fromEdges.get(toVertex).add(newEdge2);
		return newEdge;

	}
}
