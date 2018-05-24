package A6_Dijkstra;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Vertex {
	
	long idNum;
	String label;
	ConcurrentHashMap <String, Edge> inEdge;
	ConcurrentHashMap <String, Edge> outEdge;
	int inDegree = 0;
	
	boolean known;
	long distance;
	
	public Vertex(long idNum, String label){
		
		this.idNum = idNum;
		this.label = label;
		inEdge = new ConcurrentHashMap <String, Edge>();
		outEdge = new ConcurrentHashMap <String, Edge>();
		
	}
	
	public long getIdNum(){
		return idNum;
	}

}
