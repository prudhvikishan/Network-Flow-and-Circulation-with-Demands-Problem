import java.io.*;
import java.lang.*;
class Main{
    public static String fileName; //graph input by the user
    public static Graph final_graph;
    public static Graph graph;
    public static int max_flow;
    public static Graph circulationGraph;
    public static int sumOfDemands;
    public static void main(String[] args) throws IOException {
        switch (args[0].toLowerCase()){
            case "-b":
                fileName = args[1];
                int source = Integer.parseInt(args[2]);
                int destination = Integer.parseInt(args[3]);
                final_graph = new Graph(source, destination);
                graph  = new Graph(fileName,false);
                System.out.println("\n Input Graph is...");
                graph.printGraph(graph.adjacencyList);
                BreadthFirstSearch.bfs(graph.adjacencyList);
                break;
            case "-f":
                fileName = args[1];
                graph = new Graph(fileName,false);
                //System.out.println("\nOriginal Graph Representation...");
                graph.printGraph(graph.adjacencyList);
                max_flow = FordFulkerson.maxFlow(graph.adjacencyList);
                System.out.println("\nmax flow for the given graph is: " +max_flow);
                System.out.println("\nResidual Graph Representation...");
                graph.printGraph(FordFulkerson.residual_graph);
                break;
            case "-c":
                fileName = args[1];
                circulationGraph = new Graph(fileName,true);
                //System.out.println("\nOriginal Graph Representation...");
                circulationGraph.printGraph(circulationGraph.adjacencyList);
                sumOfDemands = Graph.getSum0fDemands();
                max_flow = FordFulkerson.maxFlow(circulationGraph.adjacencyList);
                System.out.println("\nmax flow for the given graph is: " +max_flow);
                if(max_flow == sumOfDemands){
                    System.out.println("\nGraph has circulation ");
                }
                else{
                    System.out.println("\nGraph doesn't have circulation in it because sumOfDemands & sumOfSupplies: " + sumOfDemands + " is not equal to maxFlow: " +max_flow);

                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + args[0].toLowerCase());
        }
    }
}