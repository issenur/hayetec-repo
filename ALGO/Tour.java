import java.util.*;
  
public class Tour {
    //This object contains 4 important things
	
	//#1 An int which indicates when the next random swap shall happen

	//#2 An int which indicates when the next random tour shall happen
	
	//#3 The total length of the tour object.
    int tourLength;
	int nextRestart;
	int nextRandomWalk;
	
	//All the cities in the tour are stored in this array.	
	ArrayList<Node> tourNodes;
	
	//the rest is self explanatory just look at the name and signature
	//of the method.	
	public Tour(int tourLength, int nextRestart, int nextRandomWalk) {
		setTourLength(tourLength);
		setNextRestart(nextRestart);
		setNextRandomWalk(nextRandomWalk);
		tourNodes = new ArrayList<Node>();
	}

	public int getTourLength() {
		return tourLength;
	}
	
	public int getNextRestart() {
		return nextRestart;
	}	

	public int getNextRandomWalk(){
		return nextRandomWalk;
	}
	
	public ArrayList<Node> getTourNodes() {
		return tourNodes;
	}
	
	public void setTourLength (int theTourLength ) {
		tourLength = theTourLength;
	}
	
	public void setNextRandomWalk(int theNextRandomWalk){
		nextRandomWalk = theNextRandomWalk;
	} 		
	
	public void setNextRestart(int theNextRestart){
		nextRestart = theNextRestart;
	}
	//this method adds nodes to the Tour, so cities I must visit are added
	//by this method
	public void addNodeTourNodes(Node n){
		tourNodes.add(n);
	}		
}
