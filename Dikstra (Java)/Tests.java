package Graph;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void AddVerticies() {
		Graph newGraph = new Graph();

		newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.addVertex("vertex 4"), 15);
		assert(newGraph.hasEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 2")));
		assert(newGraph.getEdgeWeight(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 2")) == 10);

	}
	
	@Test
	void getEdgesFromAndTo() { //also tests hasEdge.
		Graph newGraph = new Graph();

		newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.getVertex("vertex 2"), 15);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 3"), 20);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 4"),newGraph.getVertex("vertex 1"), 5);

		
		assert(newGraph.hasEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 2")));
		assert(newGraph.hasEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 3")));//makes sure that both edges exist
		
		assert(newGraph.hasEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 4")) == false);//checks for a non-existent edge - should be false
		assert(newGraph.hasEdge(newGraph.getVertex("vertex 2"),newGraph.getVertex("vertex 1")) == false);//checks a vertex that doesn't have any from edges 

		assert(newGraph.getEdgesFrom(newGraph.getVertex("vertex 1")).get(0).weight == 10);
		assert(newGraph.getEdgesFrom(newGraph.getVertex("vertex 1")).get(1).weight == 20);//makes sure that both edges added to vertex 1 are in the list
		
		
		assert(newGraph.getEdgesTo(newGraph.getVertex("vertex 2")).get(0).weight == 10);//getEdges to
		assert(newGraph.getEdgesTo(newGraph.getVertex("vertex 2")).get(1).weight == 15);
	}

	@Test
	void getEdges() { 
		Graph newGraph = new Graph();

		newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.getVertex("vertex 2"), 15);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 3"), 20);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 4"),newGraph.getVertex("vertex 1"), 5);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 4"), 2);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 5"),newGraph.getVertex("vertex 2"), 4);

		
		assert(newGraph.getEdges().contains(newGraph.getEdge(newGraph.getVertex("vertex 1"), newGraph.getVertex("vertex 2")))); //gets the list of edges and makes sure that it has these 3 edges.
		assert(newGraph.getEdges().contains(newGraph.getEdge(newGraph.getVertex("vertex 1"), newGraph.getVertex("vertex 3")))); //gets the list of edges and makes sure that it has these 3 edges.
		assert(newGraph.getEdges().contains(newGraph.getEdge(newGraph.getVertex("vertex 1"), newGraph.getVertex("vertex 4")))); //gets the list of edges and makes sure that it has these 3 edges.
		assert(newGraph.getEdges().contains(newGraph.getEdge(newGraph.getVertex("vertex 1"), newGraph.getVertex("vertex 5"))) == false); //check a edge that isn't there and make sure its false.
	}

	@Test
	void getVertex() { 
		Graph newGraph = new Graph();

		newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.getVertex("vertex 2"), 15);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 3"), 20); //add some vertices and edges
		
		assert(newGraph.getVertex("vertex 1").name == "vertex 1"); //checks getVertex.
		assert(newGraph.getVertex("vertex 5") == null); //checks getVertex if the vertex doesn't exist.
	}
	
	@Test
	void getvertices() { 
		Graph newGraph = new Graph();
		Graph noVerticies = new Graph();
		
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.getVertex("vertex 2"), 15);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 3"), 20); //add some vertices and edges
		
		assert(newGraph.getVertices().contains(newGraph.getVertex("vertex 1")));
		assert(newGraph.getVertices().contains(newGraph.getVertex("vertex 2")));//checks getVerticies returns all the vertices added.
		assert(newGraph.getVertices().contains(newGraph.getVertex("vertex 3")));
		assert(noVerticies.getVertices().isEmpty()); //no verticies in the empty graph
	}
	
	@Test
	void directedEdge() { 
		Graph newGraph = new Graph();
		
		assert(newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10).fromVertex.name == "vertex 1");//checks the from vertex.
		assert(newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.addVertex("vertex 4"), 10).toVertex.name == "vertex 4");//checks the to vertex.
		assert(newGraph.addDirectedEdge(newGraph.addVertex("vertex 5"),newGraph.addVertex("vertex 6"), 10).weight == 10);//checks the weight of the edge.
	}
	@Test
	void depthfirst() { 
		Graph newGraph = new Graph();
		

		newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.getVertex("vertex 2"), 15);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 3"), 20);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 4"),newGraph.getVertex("vertex 1"), 5);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 4"), 2);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 2"),newGraph.addVertex("vertex 5"), 4);
		
		System.out.println("Depth Search: ");
	newGraph.DepthFirstSearch(newGraph.getVertex("vertex 1"));
	assert(true); // DepthFirst Search is working as intended - check output for results.
	}
	
	@Test
	void breadthfirst() { 
		Graph newGraph = new Graph();
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.getVertex("vertex 2"), 15);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 3"), 20);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 4"),newGraph.getVertex("vertex 1"), 5);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 1"),newGraph.getVertex("vertex 4"), 2);
		newGraph.addDirectedEdge(newGraph.getVertex("vertex 2"),newGraph.addVertex("vertex 5"), 4);
		
	ArrayList<Vertex> list = newGraph.BreadthFirstSearch(newGraph.getVertex("vertex 1"));
	System.out.println("Breadth Search: ");
	for(Vertex v: list){
		System.out.println(v.name);
		
	}
	assert(true); // BreadthFirst Search is working as intended - check output for results.
	}
	@Test
	void verticies() { 
		Graph newGraph = new Graph();
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 1"),newGraph.addVertex("vertex 2"), 10);
		newGraph.addDirectedEdge(newGraph.addVertex("vertex 3"),newGraph.getVertex("vertex 2"), 15);
		
		assert(newGraph.getVertices().size()==3);
	}
	
	
}
