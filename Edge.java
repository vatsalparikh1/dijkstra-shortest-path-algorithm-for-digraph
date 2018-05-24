package A6_Dijkstra;
import java.util.*;

public class Edge {
	
	long idNum;
	Vertex source;
	Vertex destination;
	long weight;
	String eLabel;
	
	
	public Edge(long idNum, Vertex source, Vertex destination, long weight, String eLabel){
		this.idNum = idNum;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		this.eLabel = eLabel;
		
	}
	
	public long getIdNum(){
		return idNum;
	}
	

}
