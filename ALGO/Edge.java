//import java.util.*;

// Edge between two nodes
public class Edge {
	String type;
	String label;
	Node tail;
	Node head;
	
	public Edge( Node tailNode, Node headNode, String theLabel) {
		setLabel( theLabel );
		setTail( tailNode );
		setHead( headNode );
	}
	
	//Added Type attribute to Edge Object
	public Edge( Node tailNode, Node headNode, String theLabel, String theType ) {
		setLabel( theLabel );
		setTail( tailNode );
		setHead( headNode );
        setType( theType );
	}
	//Added getType Method
    public String getType() {
    	return type;
    }

	public String getLabel() {
		return label;
	}
	
	public Node getTail() {
		return tail;
	}
	
	public Node getHead() {
		return head;
	}
    //Added setType Method
	public void setType( String s ) {
		type = s;
	}

	public void setLabel( String s ) {
		label = s;
	}
	
	public void setTail( Node n ) {
		tail = n;
	}
	
	public void setHead( Node n ) {
		head = n;
	}
}
