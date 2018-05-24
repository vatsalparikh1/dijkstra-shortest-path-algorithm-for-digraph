package A6_Dijkstra;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DiGraph implements DiGraph_Interface {


  ConcurrentHashMap <String, Vertex> vertices;
  HashSet <Long> edge_id;
  HashSet <Long> vertex_id;
  int numNodes = 0;
  int numEdges = 0;
	
	

  public DiGraph ( ) { 
	  vertices = new ConcurrentHashMap<String, Vertex>();
	  edge_id = new HashSet<Long>();
	  vertex_id = new HashSet<Long>();
	  
  }

@Override
public boolean addNode(long idNum, String label) {
	
	if(idNum < 0 || vertices.containsKey(label) || label == null ||vertex_id.contains(idNum)){
		
		return false;
		
	}else{
		
		vertex_id.add(idNum);
		vertices.put(label,new Vertex(idNum, label));
		numNodes++;
		
		return true;
		
	}
	
	
	
	
	
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	
	
	
	if(idNum < 0 || edge_id.contains(idNum)||!vertices.containsKey(sLabel)||!vertices.containsKey(dLabel)||vertices.get(sLabel).outEdge.containsKey(dLabel)){
		return false;
	}else{
		edge_id.add(idNum);
		
		Vertex sourceNode = vertices.get(sLabel);
		Vertex destinationNode = vertices.get(dLabel);
		
		Edge newEdge = new Edge(idNum,sourceNode,destinationNode,weight,eLabel);
		numEdges ++;
		
		sourceNode.outEdge.put(dLabel,newEdge);
		destinationNode.inEdge.put(sLabel, newEdge);
		destinationNode.inDegree ++;
		
		
		return true;
	
	}
	
}

@Override
public boolean delNode(String label) {
	if(!vertices.containsKey(label)){
		return false;
	}else{
		
		Vertex toDelete = vertices.get(label);
		
		
		for(Edge e : toDelete.inEdge.values()){ //remove in edges
			e.source.outEdge.remove(toDelete.label);
			edge_id.remove(e.getIdNum());
			
			numEdges--;//Correction
		}
		for(Edge e : toDelete.outEdge.values()){//remove out edges
			e.destination.inEdge.remove(toDelete.label);
			edge_id.remove(e.getIdNum());
			e.destination.inDegree--;
			
			numEdges--;//Correction
		}
		
		vertex_id.remove(toDelete.getIdNum());
		vertices.remove(label);
		numNodes--;
		return true;
		
	}
	
	
	
	
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	if(vertices.get(sLabel) != null && vertices.get(sLabel).outEdge != null){
		if(!(vertices.get(sLabel).outEdge.containsKey(dLabel))){
			return false;
		}else{
			
			long toRemove = vertices.get(sLabel).outEdge.get(dLabel).getIdNum();
			vertices.get(sLabel).outEdge.remove(dLabel);
			vertices.get(dLabel).inEdge.remove(sLabel);
			edge_id.remove(toRemove);
			vertices.get(dLabel).inDegree--;
			
			numEdges--;
			return true;
		}
	}else{
		return false;
	}
	
	
}

@Override
public long numNodes() {
	
	return numNodes;
}

@Override
public long numEdges() {
	return numEdges;
}

@Override
public String[] topoSort() {
	
	
	int correctSize = 0;
	Queue<Vertex> isHead = new LinkedList<Vertex>();
	ArrayList<String> sorted = new ArrayList<String>();
	
	for(Vertex v : vertices.values()){
		if(v.inDegree == 0){
			isHead.add(v);
		}
		correctSize++;
	}
	
	while(!isHead.isEmpty()){
		Vertex current = isHead.remove();
		sorted.add(current.label);
		
		for(Edge e : current.outEdge.values()){
			delEdge(current.label, e.destination.label);
			if(e.destination.inDegree == 0){
				isHead.add(e.destination);
			}
			
		}
		
	
		
		
		
		
	}
	
	
	if(sorted.size() != correctSize){
		return null;
	}else{
		String[] output = new String[correctSize];
		output = sorted.toArray(output);
		return output;
	}
	
	
}

@Override
public ShortestPathInfo[] shortestPath(String label) {
	int numVertices = 0;
	for(Vertex v : vertices.values()){
		v.known = false;
		v.distance = Long.MAX_VALUE;
		numVertices++;
	}
	
	Vertex start = vertices.get(label);
	MinBinHeap PQ = new MinBinHeap();

	start.distance = 0;
	PQ.insert(new EntryPair(start, start.distance));
	
	while(PQ.size() != 0){
		Vertex n = PQ.getMin().getValue();
		long d = n.distance;
		PQ.delMin();
		
		
		if(n.known){
			continue;
		}else{
			n.known = true;
			for(Edge e : n.outEdge.values()){
				Vertex a = e.destination;
				
				if (a.known){//if known
					continue;
				}else{ //if not known
					if(a.distance > d + e.weight){
						a.distance = d + e.weight;
						PQ.insert(new EntryPair(a,a.distance));
					}
				}
				
			}
			
		}
		
		
		
	}
	
	ShortestPathInfo[] output = new ShortestPathInfo[numVertices];
	
	numVertices = 0;
	
	for(Vertex v : vertices.values()){
		String name = v.label;
		
		if(v.distance == Long.MAX_VALUE){
			v.distance = -1;
		}
		
		ShortestPathInfo temp = new ShortestPathInfo(v.label,v.distance);
		
		output[numVertices] = temp;
		
		numVertices ++;
		
	}
	
	
	return output;
	
}



 
}
