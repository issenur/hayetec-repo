import java.util.*;

// A node of a graph for the Spring 2018 ICS 340 program

public class Node {
    //ISSE:Added color, startTime, finishTime,
	String color;
    int startTime;
    int finishTime;

	String name;
	String val;  // The value of the Node
	String abbrev;  // The abbreviation for the Node
	ArrayList<Edge> outgoingEdges;  
	ArrayList<Edge> incomingEdges;
	
	public Node( String theAbbrev ) {
		setAbbrev( theAbbrev );
		val = null;
		name = null;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
	}
	public Node( String theAbbrev, String color, 
	  int startTime, int finishTime ) {
		setAbbrev( theAbbrev );
		setColor(color);
		setStartTime(startTime);
		setFinishTime(finishTime);
		val = null;
		name = null;
		outgoingEdges = new ArrayList<Edge>();
		incomingEdges = new ArrayList<Edge>();
	}
	
	public String getAbbrev() {
		return abbrev;
	}
	
	public String getName() {
		return name;
	}
	
	public String getVal() {
		return val;
	}
	
	//ISSE: Added getColor, getStartTime, getFinishTime Methods
	public String getColor() {
		return color;
	}

	public int getStartTime() {
		return startTime;
	}
	
	public int getFinishTime() {
		return finishTime;
	}
	
	public ArrayList<Edge> getOutgoingEdges() {
		return outgoingEdges;
	}
	
	public ArrayList<Edge> getIncomingEdges() {
		return incomingEdges;
	}
	
	public void setColor( String theColor ) {
		color = theColor;
	}

	public void setStartTime (int theStartTime ) {
		startTime = theStartTime;
	}		
	
	public void setFinishTime (int theFinishTime ) {
		finishTime = theFinishTime;
	}
		
	public void setAbbrev( String theAbbrev ) {
		abbrev = theAbbrev;
	}
	
	public void setName( String theName ) {
		name = theName;
	}
	public void setVal ( String theVal){
		val = theVal;
	}
	
	public void addOutgoingEdge( Edge e ) {
		outgoingEdges.add( e );
	}
	
	public void addIncomingEdge( Edge e ) {
		incomingEdges.add( e );
	}
	
}
