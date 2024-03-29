import java.io.*;              
import java.util.ArrayList;    

public class Graph {

	ArrayList<Node> nodeList;
	ArrayList<Edge> edgeList;
	Tour tour;	
	public Graph() {
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();
		tour = new Tour(0,0,0);
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public Tour getTour() {
		return tour;
	}
	
	public void addNode( Node n ) {
		nodeList.add( n );
	}
	
	public void addEdge( Edge e ) {
		edgeList.add( e );
	}
	
	public void setTour( Tour theTour ) {
		tour = theTour;
	}
	
	public String toString() {
		String s = "Graph g.\n";
		if ( nodeList.size() > 0 ) {
			for( Node n : nodeList ) {
				// Print node info
				String t = "\nNode " + n.getName() + ", abbrev " + n.getAbbrev() + ", value " + n.getVal() + "\n";
				s = s.concat(t);
			}
			s = s.concat("\n");
		}

		return s;
	}
	
}
