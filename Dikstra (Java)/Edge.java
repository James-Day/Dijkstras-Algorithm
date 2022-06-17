package Graph;

public class Edge {
	public Vertex fromVertex;
	public Vertex toVertex;
	public int weight;

	public Edge(Vertex vertex1, Vertex vertex2, int weight) {
		fromVertex = vertex1;
		toVertex = vertex2;
		this.weight = weight;
	}
}
