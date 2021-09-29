# Network-Flow-and-Circulation-with-Demands-Problem
Goal of this academic project is to implement the Breadth First Search, Ford-Fulkerson Network flow algorithm and use it to solve the Circulation with Demands problem.


There are 5 java source code files in this project

1. Main (Main class of this project which  contains the switch case for the 3 executions)
2. Graph (Graph class is used to create adjacency lists - graph from .txt files)
3. BreadthFirstSearch (This class has bfs implementation and gives shortest path between source and sink by taking the No.of edges into calculation)
4. FordFulkerson (This class gives the Maximum flow in the graph by using BreadthFirstSearch algorithm)
5. GraphTestCase (To generate multiple test cases dynamically this algorithm is implemented. It is independent of the other 4 classes).

Please find below steps for Project Execution:

* The source_code files and test cases should be in the same directory.
* There should be a blank line at the last in the text file input. It indicates sink node.
* Input for BFS and FFA algorithm are provided as txt files. For BFS we need to give source and sink as command line arguments.
* Input for Circulation problem should also be a text file with Supplies and Demands mentioned in the similar format above.

Steps to execute the program: 

First run javac Main.java

Then to run the bfs algorithm

java Main –b <graph_*>.txt source sink // -b for bfs implementation, source and sink should be given by the user

java Main –f <graph_*>.txt //-f for ford-fulkerson implementation

java Main –c <circular*>.txt// -c for circulation implementation

Graph Test case is an independent Java program. It can be compiled and run as in the below steps:
javac GraphTestCase.java
java GraphTestCase
