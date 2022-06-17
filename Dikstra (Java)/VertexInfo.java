package Graph;

public class VertexInfo implements Comparable<VertexInfo> {
	public Vertex vertex;
	public int distance;
	public Vertex Predecessor;

	public VertexInfo(Vertex vertex) {
		this.vertex = vertex;
		distance = Integer.MAX_VALUE;
		Predecessor = null;

	}

	public int compareTo(VertexInfo other) { // allows us to use a priority queue, and it knows to compare using
												// distance
		if (distance > other.distance) {
			return 1;
		} else if (distance < other.distance) {
			return -1;
		}
		return 0;
	}
}
