////
// Author:       James Day	
// Section:    A
// Assignment:  Dijkstra's Algorithm 
// Description:  this algorithm finds the shorts path 
//				between 2 points.
//
//			Problems: There are no problems to my knowlede.
//			
//			My Answers:
//			shortest path from 1 - 37 is 22
//			shortest path from 14 - 23 25
//
////

#pragma once
using namespace std;
const int NumberVertices = 38;
const int INFINITE = 1000;


class Graph {
public:
	
	
	int TotalCost[NumberVertices][NumberVertices] = { 0 };
	int Distance[NumberVertices] = { 0 };
	int parent[NumberVertices] = { 0 };
	bool Visted[NumberVertices] = { 0 };
	
	Graph(int NumberofVertices) {
	
		
		for (int i = 0; i < NumberVertices; i++) {
				parent[i] = i;
				Distance[i] = INFINITE;			//Any high number would work
		}
			for (int i = 0; i < NumberVertices; i++) {
				for (int j = 0; j < NumberVertices; j++) {
					TotalCost[i][j] = INFINITE;
				}
			}
			
			
	//Constructor
	}
	void addVertex(int VertexNum, int VertexNumTwice) {		//not sure why the main passes in the vertex twice
		Visted[VertexNum] = 0;
	}
	void addEdge(int VertexOne, int VertexTwo, int Cost) {
		TotalCost[VertexOne][VertexTwo] = Cost;
		TotalCost[VertexTwo][VertexOne] = Cost;

	}
	void calculatePaths(int StartingPoint) {
		for (int i = 0; i<NumberVertices; i++) {
			Distance[i] = INFINITE;
			Visted[i] = false;						//resets the arrays for subsequent calls
		}
		Distance[StartingPoint] = 0;
		for (int i = 0; i < NumberVertices - 1; i++) {			//Number of Vertices-1 cause of the final node
			int nearestUnvisitNode = getNearest();
			Visted[nearestUnvisitNode] = true;
			for (int adj = 0; adj < NumberVertices; adj++) {
				if (TotalCost[nearestUnvisitNode][adj] != INFINITE &&
					Distance[adj] >Distance[nearestUnvisitNode] + TotalCost[nearestUnvisitNode][adj]) {
					Distance[adj] = Distance[nearestUnvisitNode] + TotalCost[nearestUnvisitNode][adj];
					parent[adj] = nearestUnvisitNode;
				}
				
			}

		}
		ShowPaths(StartingPoint);
		
	}
	int getNearest() {
		int lowestValue = INFINITE;
		int minVertex = 0;
		for (int i = 0; i < NumberVertices; i++) {
			if (!Visted[i] && Distance[i]<lowestValue) {
				lowestValue = Distance[i];
				minVertex = i;
			}
		}
		return minVertex;
	}
	void ShowPaths(int StartingPoint) {
	
		cout << "Node:\t\t Cost: \t\t\tPath" <<endl;
		for (int i = 0; i < NumberVertices; i++) {
			int temp = parent[i];
			cout << i << "\t\t\t" << Distance[i] << "\t\t\t" << " ";
			cout << i << " ";
			while (Distance[i]!= INFINITE && temp != StartingPoint)	 //If distance[i] = INF then its node 18 or 19
			{
				cout << " <- " << temp << " ";
				temp = parent[temp];
			}
			cout<<endl;
		}
	}
};
/*
STARTING FROM 14
Node:            Cost:                  Path
0                       4                        0
1                       12                       1  <- 0
2                       12                       2  <- 0
3                       16                       3  <- 17  <- 16  <- 12  <- 11
4                       10                       4  <- 7  <- 11
5                       15                       5  <- 8  <- 11
6                       14                       6  <- 2  <- 0
7                       7                        7  <- 11
8                       14                       8  <- 11
9                       13                       9  <- 13  <- 12  <- 11
10                      15                       10  <- 13  <- 12  <- 11
11                      6                        11
12                      8                        12  <- 11
13                      10                       13  <- 12  <- 11
14                      0                        14
15                      16                       15  <- 12  <- 11
16                      14                       16  <- 12  <- 11
17                      15                       17  <- 16  <- 12  <- 11
18                      1000                     18
19                      1000                     19
20                      12                       20  <- 0
21                      13                       21  <- 20  <- 0
22                      22                       22  <- 25  <- 5  <- 8  <- 11
23                      25                       23  <- 37  <- 30  <- 10  <- 13  <- 12  <- 11
24                      14                       24  <- 21  <- 20  <- 0
25                      21                       25  <- 5  <- 8  <- 11
26                      24                       26  <- 30  <- 10  <- 13  <- 12  <- 11
27                      16                       27  <- 24  <- 21  <- 20  <- 0
28                      20                       28  <- 24  <- 21  <- 20  <- 0
29                      20                       29  <- 30  <- 10  <- 13  <- 12  <- 11
30                      19                       30  <- 10  <- 13  <- 12  <- 11
31                      16                       31  <- 34  <- 20  <- 0
32                      20                       32  <- 31  <- 34  <- 20  <- 0
33                      24                       33  <- 32  <- 31  <- 34  <- 20  <- 0
34                      13                       34  <- 20  <- 0
35                      23                       35  <- 15  <- 12  <- 11
36                      21                       36  <- 32  <- 31  <- 34  <- 20  <- 0
37                      23                       37  <- 30  <- 10  <- 13  <- 12  <- 11

STARTING FROM 1
Node:            Cost:                  Path
0                       8                        0
1                       0                        1  <- 0
2                       16                       2  <- 0
3                       21                       3  <- 2  <- 0
4                       14                       4  <- 8  <- 5
5                       8                        5
6                       16                       6  <- 5
7                       12                       7  <- 0
8                       9                        8  <- 5
9                       20                       9  <- 13  <- 12  <- 11  <- 7  <- 0
10                      22                       10  <- 13  <- 12  <- 11  <- 7  <- 0
11                      13                       11  <- 7  <- 0
12                      15                       12  <- 11  <- 7  <- 0
13                      17                       13  <- 12  <- 11  <- 7  <- 0
14                      12                       14  <- 0
15                      23                       15  <- 12  <- 11  <- 7  <- 0
16                      21                       16  <- 12  <- 11  <- 7  <- 0
17                      22                       17  <- 3  <- 2  <- 0
18                      1000                     18
19                      1000                     19
20                      16                       20  <- 0
21                      17                       21  <- 20  <- 0
22                      15                       22  <- 25  <- 5
23                      23                       23  <- 22  <- 25  <- 5
24                      18                       24  <- 21  <- 20  <- 0
25                      14                       25  <- 5
26                      21                       26  <- 25  <- 5
27                      20                       27  <- 28  <- 25  <- 5
28                      15                       28  <- 25  <- 5
29                      20                       29  <- 25  <- 5
30                      21                       30  <- 29  <- 25  <- 5
31                      20                       31  <- 28  <- 25  <- 5
32                      16                       32  <- 28  <- 25  <- 5
33                      20                       33  <- 32  <- 28  <- 25  <- 5
34                      17                       34  <- 20  <- 0
35                      21                       35  <- 36  <- 32  <- 28  <- 25  <- 5
36                      17                       36  <- 32  <- 28  <- 25  <- 5
37                      22                       37  <- 35  <- 36  <- 32  <- 28  <- 25  <- 5
Press any key to continue . . .



shortest path from 1-37 is 22
shortest path from 14 -23 25

*/