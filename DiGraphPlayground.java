package A6_Dijkstra;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
    exTest();
    }
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(0, "a");
      d.addNode(1, "b");
      d.addNode(2, "c");
      d.addNode(3, "d");
      d.addNode(4, "e");
      d.addEdge(0, "a", "b", 1, null);
      d.addEdge(1, "b", "c", 1, null);
      d.addEdge(2, "a", "c", 3, null);
      d.addEdge(3, "c", "d", 2, null);
      d.addEdge(4, "c", "e", 5, null);
      d.addEdge(5, "b", "d", 2, null);
      d.addEdge(6, "d", "e", 3, null);
      d.addEdge(7, "b", "e", 7, null);
      d.addEdge(8, "a", "e", 9, null);
      d.addEdge(9, "a", "d", 5, null);
      
      //d.delEdge("a", "b");
      d.delNode("d");

      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
      ShortestPathInfo[] sp=d.shortestPath("a");
      
      //System.out.println(sp[2].getTotalWeight());
      printTOPO(d.topoSort());
      printSP(sp);
      
    }
    
    public static void printSP(ShortestPathInfo[] sp){
    	for(int i = 0; i< sp.length; i++){
    		System.out.print("["+sp[i].getDest()+","+sp[i].getTotalWeight() +"] ");
    	}
    	
    }
    
    public static void printTOPO(String[] toPrint){
      System.out.print("TOPO Sort: ");
      for (String string : toPrint) {
      System.out.print(string+" ");
    }
      System.out.println();
    }

}