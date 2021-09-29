import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

class Edges{
    int source;
    int destination;
    int weight;

    public Edges(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class Graph {
    public static int noOfVertices;
    List<Edges> adjacencyList[];

    private static int source;
    private static int destination;

    List<Integer> demand; // to store demand vertices
    List<Integer> supply; // to store supply vertices

    private static int sum0fDemands = 0;
    private static int sumOfSupplies = 0;

    public static int getSource() {
        return source;
    }

    public static void setSource(int source) {
        Graph.source = source;
    }

    public static int getDestination() {
        return destination;
    }

    public static void setDestination(int destination) {
        Graph.destination = destination;
    }

    public static int getNoOfVertices() {
        return noOfVertices;
    }

    public static void setNoOfVertices(int noOfVertices) {
        Graph.noOfVertices = noOfVertices;
    }

    public static int getSum0fDemands() {
        return sum0fDemands;
    }

    public static void setSum0fDemands(int sum0fDemands) {
        Graph.sum0fDemands = sum0fDemands;
    }


    Graph(int source, int dest){
        setSource(source);
        setDestination(dest);
    }

    @SuppressWarnings("unchecked")
    Graph(String fileName, boolean circulation) throws IOException {

        if(circulation){
            circulationGraph(fileName);
        }else{
            Path file = Paths.get(fileName);
            noOfVertices = (int) Files.lines(file).count();

            adjacencyList = new LinkedList[noOfVertices];

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String text;

            int nodeNumber = 0;

            while ((text = bufferedReader.readLine()) != null){
                //System.out.println("lines :: "+text);
                String[] values =text.split(" ");
                List<Edges> edgesList = new LinkedList<>();
                for(int i=0; i<=values.length-2; i=i+2){
                    Edges edge = new Edges(nodeNumber,Integer.valueOf(values[i]),Integer.valueOf(values[i+1]));
                    edgesList.add(edge);
                }
                adjacencyList[nodeNumber]=edgesList;
                nodeNumber++;
            }
        }
    }

    @SuppressWarnings("unchecked")
    void circulationGraph(String fileName) throws IOException{ // For Circulation Graph
        int numOfNodes =0; int sup_dem = 0;
        int fromNode, toNode, temp, weight;
        demand = new LinkedList<>();
        supply = new LinkedList<>();

        Path file = Paths.get(fileName);
        numOfNodes = (int) Files.lines(file).count();

        setNoOfVertices(numOfNodes + 2);
        noOfVertices = getNoOfVertices();

        adjacencyList = new LinkedList[noOfVertices];
        for(int i=0; i < noOfVertices; i++){
            adjacencyList[i] = new LinkedList<>();
        }

        String text; String[] values;
        String splits = " +";  // Multiple whitespace as delimiter.
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //System.out.println("Reading edges from text file...\n");

        temp=0;
        while(temp < numOfNodes){
            text = bufferedReader.readLine();
            values = text.split(splits);
            fromNode = temp;
            sup_dem = Integer.parseInt(values[0]);
            if(sup_dem > 0){
                demand.add(sup_dem);
               // System.out.println("Edge from " + (fromNode+1) + " --(" + sup_dem + ")-- " + (noOfVertices-1));
                addEdge((fromNode+1), (noOfVertices-1), sup_dem);
            }
            else if(sup_dem < 0){
                supply.add(sup_dem);
                //System.out.println("Edge from " + 0 + " --(" + Math.abs(sup_dem) + ")-- " + (fromNode+1));
                addEdge(0, fromNode+1, Math.abs(sup_dem));
            }
            for(int i = 1; i<values.length-1; i = i+2){
                toNode = Integer.parseInt(values[i]);
                weight = Integer.parseInt(values[i+1]);
                //System.out.println("Edge from " + (fromNode+1) + " --(" + weight + ")-- " + (toNode+1));
                addEdge(fromNode+1, toNode+1, weight);
            }
            ++temp;
        }
        sum0fDemands = demand.stream().mapToInt(Integer::intValue).sum();
        sumOfSupplies = supply.stream().mapToInt(Integer::intValue).sum();
        setSum0fDemands(sum0fDemands);
        if(sum0fDemands!=Math.abs(sumOfSupplies)){
            //boolean doDemandsMatchSupplies = false;
            System.out.println("Given graph doesn't contain circulation in it because sumOfSupplies " +Math.abs(sumOfSupplies) + " doesn't match to sumOfDemands " +sum0fDemands);
            System.exit(0);
        }
    }

    public void addEdge(int fromNode, int toNode, int weight)
    {
        adjacencyList[fromNode].add(new Edges(fromNode,toNode, weight));
    }

    void printGraph(List<Edges> adjacencyList[]){

        for(int i=0; i < noOfVertices; i++){
                List<Edges> edgeList = adjacencyList[i];
                StringBuilder sb = new StringBuilder();
                if(edgeList.size()>0){
                    for(Edges edge : edgeList){
                        sb.append("|"+edge.destination+"|"+"|"+edge.weight+"|");
                        sb.append(" -> ");
                    }
                }
                System.out.println("adjacencyList["+i+"] -> "+sb);

        }

    }
}
