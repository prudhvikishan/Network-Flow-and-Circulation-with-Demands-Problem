import java.util.*;

public class BreadthFirstSearch {

    static boolean[] nodeVisited;
    static int[] parent;
    static long time = 0;
    public static long getTime(){
        return time;
    }
    public static void setTime(long endTimeMs){
        time = endTimeMs;
    }
    public static boolean bfs(List<Edges> list[]){

        int source = Graph.getSource();
        int destination = Graph.getDestination();
        int totalNodes = Graph.getNoOfVertices();
        //System.out.println("total nodes "+totalNodes);

        nodeVisited = new boolean[totalNodes];
        parent = new int[totalNodes];

        Arrays.fill(parent, -1);

        Queue<Edges> queue = new LinkedList<>();
        queue.add(new Edges(source,source,0));
        long startTime = System.nanoTime();
        System.out.print("\nBreadth First Search: ");
        while(queue!=null && queue.size()>0){
            Edges currentNode = queue.poll();
            int currentNodeValue = currentNode.source;
            if(!nodeVisited[currentNodeValue] && list[currentNodeValue]!=null){
                System.out.print(currentNode.source + " ");
                List<Edges> adjacencyList = list[currentNodeValue];
                for(Edges edge : adjacencyList){
                    int nodeNumber = edge.destination;
                    if(!nodeVisited[nodeNumber] && !queue.contains(nodeNumber) && edge.weight>0){
                        queue.add(new Edges(nodeNumber,0,0));
                        if(parent[nodeNumber]==-1){
                            parent[nodeNumber] = currentNodeValue;
                        }
                    }
                }
            }
            nodeVisited[currentNodeValue] = true;
        }

        long endTime = System.nanoTime() - startTime;
        System.out.println("\n\nTime taken for bfs to run in nano-seconds: " +endTime);
        long endTimeMS =  endTime / 1_000_000;
        System.out.println("\nTime taken for bfs to run in milli-seconds: " +endTimeMS);

        setTime(endTimeMS);

        if(nodeVisited[destination]){
            System.out.print("\nShortest path between " +source+ " and " + destination + " is: ");
            System.out.println("having " + finalPath(parent, destination) + " edges in between them");
        }
        else{
            System.out.println("\nThere is no path that exists between " +source+ " and " + destination);
        }

        return (nodeVisited[destination] == true);
    }

    public static int finalPath(int[] parent, int destination){
        int noOfEdges = 0;

        int predecessor = parent[destination];
        List<Integer> pathList = new LinkedList<>();
        pathList.add(destination);
        while(predecessor!=-1){
            pathList.add(predecessor);
            predecessor = parent[predecessor];
            noOfEdges++;
        }
        Collections.reverse(pathList);
        for(Integer path : pathList){
            System.out.print(path+" ");
        }
        return noOfEdges;
    }
}
