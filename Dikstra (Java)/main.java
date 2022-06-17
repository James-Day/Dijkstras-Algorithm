package Graph;

import java.util.HashMap;
import java.util.Set;

public class main {

	public static void main(String[] args) {
		Graph graph = new Graph();
		for (Integer i = 0; i < 18; i++) {
			graph.addVertex(i.toString());
		}
		for (Integer i = 20; i < 38; i++) {
			graph.addVertex(i.toString());
		}
		// 0
		graph.addEdge(0, 1, 8);
		graph.addEdge(0, 2, 8);
		graph.addEdge(0, 7, 4);
		graph.addEdge(0, 14, 4);
		graph.addEdge(0, 20, 8);
		// 1
		graph.addEdge(1, 5, 8);
		// 2
		graph.addEdge(2, 3, 5);
		graph.addEdge(2, 6, 2);
		// 3
		graph.addEdge(3, 17, 1);
		// 4
		graph.addEdge(4, 5, 7);
		graph.addEdge(4, 7, 3);
		graph.addEdge(4, 8, 5);
		// 5
		graph.addEdge(5, 6, 8);
		graph.addEdge(5, 8, 1);
		graph.addEdge(5, 25, 6);
		// 6 All edges added
		// 7
		graph.addEdge(7, 11, 1);
		// 8
		graph.addEdge(8, 11, 8);
		graph.addEdge(8, 12, 7);
		// 9
		graph.addEdge(9, 10, 6);
		graph.addEdge(9, 12, 9);
		graph.addEdge(9, 13, 3);
		// 10
		graph.addEdge(10, 13, 5);
		graph.addEdge(10, 17, 2);
		graph.addEdge(10, 30, 4);
		// 11
		graph.addEdge(11, 12, 2);
		graph.addEdge(11, 14, 6);
		// 12
		graph.addEdge(12, 13, 2);
		graph.addEdge(12, 15, 8);
		graph.addEdge(12, 16, 6);
		// 13
		graph.addEdge(13, 17, 9);
		// 14 All edges added
		// 15
		graph.addEdge(15, 35, 7);
		// 16
		graph.addEdge(16, 17, 1);
		// 17 All edges added
		// 20
		graph.addEdge(20, 21, 1);
		graph.addEdge(20, 24, 3);
		graph.addEdge(20, 27, 5);
		graph.addEdge(20, 34, 1);
		// 21
		graph.addEdge(21, 24, 1);
		// 22
		graph.addEdge(22, 23, 8);
		graph.addEdge(22, 25, 1);
		graph.addEdge(22, 26, 8);
		// 23
		graph.addEdge(23, 26, 2);
		graph.addEdge(23, 37, 2);
		// 24
		graph.addEdge(24, 27, 2);
		graph.addEdge(24, 28, 6);
		// 25
		graph.addEdge(25, 26, 7);
		graph.addEdge(25, 28, 1);
		graph.addEdge(25, 29, 6);
		// 26
		graph.addEdge(26, 29, 9);
		graph.addEdge(26, 30, 5);
		// 27
		graph.addEdge(27, 28, 5);
		graph.addEdge(27, 34, 7);
		// 28
		graph.addEdge(28, 31, 5);
		graph.addEdge(28, 32, 1);
		// 29
		graph.addEdge(29, 30, 1);
		graph.addEdge(29, 33, 5);
		// 30
		graph.addEdge(30, 37, 4);
		// 31
		graph.addEdge(31, 32, 4);
		graph.addEdge(31, 34, 3);
		graph.addEdge(31, 35, 9);
		// 32
		graph.addEdge(32, 33, 4);
		graph.addEdge(32, 36, 1);
		// 33
		graph.addEdge(33, 36, 7);
		// 34 All edges added
		// 35
		graph.addEdge(35, 36, 4);
		graph.addEdge(35, 37, 1);
		// 36 All edges added
		// 37 All edges added
		System.out.println("starting from 1");
		HashMap<Vertex, VertexInfo> map2 = graph.dijkstraShortestPath(graph.getVertex("1"));
		Set<Vertex> verticies2 = graph.getVertices();

		for (int i = 0; i < verticies2.size() + 2; i++) { // the plus 2 is making up for the missing vertices 18-19
			if (i != 18 && i != 19) {
				Vertex v = graph.getVertex(((Integer) i).toString());
				VertexInfo curInfo = map2.get(v);
				if (curInfo.Predecessor == null && v != graph.getVertex("1")) {
					System.out.println("1 to " + v.name + ": no path exists");
				} else {
					System.out.println("1 to " + v.name + ": " + graph.getShortestPath(graph.getVertex("1"), v, map2));
				}
			}
		}
		System.out.println("starting from 14");
		HashMap<Vertex, VertexInfo> map = graph.dijkstraShortestPath(graph.getVertex("14"));
		Set<Vertex> verticies = graph.getVertices();

		for (int i = 0; i < verticies.size() + 2; i++) {
			if (i < 18 || i > 19) {
				Vertex v = graph.getVertex(((Integer) i).toString());
				VertexInfo curInfo = map.get(v);
				if (curInfo.Predecessor == null && v != graph.getVertex("14")) {
					System.out.println("14 to " + v.name + ": no path exists");
				} else {
					System.out.println("14 to " + v.name + ": " + graph.getShortestPath(graph.getVertex("14"), v, map));
				}
			}
		}
	}
}
