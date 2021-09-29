import java.util.*;
public class FordFulkerson {
    static int max_flow = 0;
    static int V;
    static List<Edges> residual_graph[];
    static Edges vertex = null;
    static long time = 0;
    public static void setFFATime(long endTimeMs){
        time = endTimeMs;
    }

    public static int maxFlow(List<Edges> adjacency[]){
        int v, u; int index =0;
        V = Graph.getNoOfVertices();
        residual_graph = adjacency;
        int source = 0;
        int dest = V-1;
        System.out.println("source: " +source + " dest: " +dest);
        Graph.setSource(source);
        Graph.setDestination(dest);
        long startTime = System.nanoTime();
        while(BreadthFirstSearch.bfs(residual_graph)){
            int path_flow = Integer.MAX_VALUE;
            for(v = dest; v!=source; v = BreadthFirstSearch.parent[v]){
                u = BreadthFirstSearch.parent[v];
                List<Edges> edges_list = residual_graph[u];
                for(int i =0; i<edges_list.size(); i++){
                    if(v == edges_list.get(i).destination){
                        index = i;
                        break;
                    }
                }
                path_flow = Math.min(path_flow, residual_graph[u].get(index).weight);
            }
            System.out.println("Min-Path-flow: " +path_flow);
            max_flow += path_flow;
            for(v = dest; v!=source; v = BreadthFirstSearch.parent[v]){
                u = BreadthFirstSearch.parent[v];
                List<Edges> edges_list = residual_graph[u];
                for(int i=0; i<edges_list.size(); i++){
                    if(v == edges_list.get(i).destination){
                        vertex = edges_list.get(i);
                        index = i;
                    }
                }
                residual_graph[u].get(index).weight -= path_flow;
                index = -1;
                if(!residual_graph[vertex.destination].isEmpty()){
                    List<Edges> add_edge = residual_graph[vertex.destination];
                    for (int i =0; i<add_edge.size(); i++) {
                        if (u == add_edge.get(i).destination) {
                            index = i;
                        }
                    }
                    if(index != -1){
                        residual_graph[vertex.destination].get(index).weight += path_flow;
                    }
                    else{
                        residual_graph[vertex.destination].add(new Edges(v,u, path_flow));
                    }
                }
                else{
                    List<Edges> new_list = new LinkedList<>();
                    new_list.add(new Edges(v,u, path_flow));
                    residual_graph[vertex.destination] = new_list;
                }
            }
        }
        long endTime = System.nanoTime() - startTime;
        System.out.println("\n\nTime taken for ford-fulkerson to run in nano-seconds: " +endTime);
        long endTimeS = endTime / 1_000_000;
        System.out.println("\nTime taken for ford-fulkerson to run in milli-seconds: " +endTimeS);
        setFFATime(endTimeS);
        return max_flow;
    }
}