import java.io.*;
import java.util.ArrayList;
// Class DelivB does the work for deliverable DelivB of the Prog340

public class DelivB {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	
	Graph grph;
	int time;
	Node nde;
	
	public DelivB( File in, Graph gr ) {
		inputFile = in;
		g = gr;
		
		
		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = 
		  inputFileName.substring( 0, 
		    inputFileName.length() -4); // Strip off ".txt"
		String outputFileName = baseFileName.concat( "_out.txt" );
		outputFile = new File(  outputFileName );
		if ( outputFile.exists() ) {    // For retests
			//outputFile.delete();
		}
		try {                                         
			output = new PrintWriter(outputFile);     
		}                                             
		catch (Exception x ) {                        
    		System.err.format("Exception: %s%n", x);  
    		System.exit(0);                           
		}        
	
	    /*I AM USING THIS SECTION LIKE A MAIN METHOD I AM CALLING THE 
		  MAIN METHODS FROM HERE*/
		
		Graph grph = new Graph();
	   
	    /*I am creating this graph object and copying  the object g
		  in order to avoid confusion.*/
		grph = g;                                     
	    
		/*Calling the DFS Method which will call the recursive DFS_VISIT 
		  Method*/
		dFS(grph);
	    
		/*This is a method that labels only The Forward Backward and Cross Edges
		  I labeled at as setEdgeType2 because I didn't want to get it confused with the 
		  The other setEdgeType method in the Edge Class*/
		setEdgeType2(grph);	
		
	    /*This is like a toString for Deliverable B, It does fromatting
		  and printing out the values*/
		delivbPrintOut(grph);	
	}
	
	
	/*This Method will sort an Outgoing EdgeList by ascending integer edge values.
	  It is a slightly Modified version of quick Sort Method. I must
	  give credit to HowToDoJava.com for this method*/
	private	void quickSortInts(ArrayList<Edge> outEdges, int low, int high){	
		//this if statement is used to detect and empty
		//arraylist or indicate when it is already sorted.
		if(outEdges.equals(null) || outEdges.size() == 0){
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid = low + (high - low)/2;

		//quick sort uses pivots to sort against, this pivot is an int
		//so I convert it to an integer.
		int pivotInt = Integer.parseInt(outEdges.get(mid).getLabel());

		int i = low;
		int j = high;
		//this moves the pointers i & j towards eachother until they find
		// a value that is larger than pivot to the left of pivot and
		// a value that is smaller than pivot to the right of pivot.
		while (i <= j){
			while(Integer.parseInt(outEdges.get(i).getLabel()) < pivotInt){
				i++;
			}
			while(Integer.parseInt(outEdges.get(j).getLabel()) > pivotInt){
				j--;
			}	

			if(i <= j){
				swapIntEdge(outEdges, i, j);
				i++;
				j--;
			}
		}
		if (low < j){
			quickSortInts(outEdges,low, j);
		}
		if (high  >  i){
			quickSortInts(outEdges, i, high);
		}
	}

	/*This is a helper function for the quickSortInts Method, which does the
	  Swapping*/
	private void swapIntEdge(ArrayList<Edge> outEdges, int i, int j){	
		//this is a place holder refrence for when I swap Edges in the
		//Arraylist
		Edge temp = outEdges.get(i);
		
		//Below this line is where I handle the situation where two outgoing
		//Nodes having the same int value. For a tie breaker I go by
		//the node with the least lexocraphic value			
		if(outEdges.get(i).getLabel().equals(outEdges.get(j).getLabel())){
			if((outEdges.get(i).getHead().getName().compareTo(
							outEdges.get(j).getHead().getName())) < 0){
			  //We do nothing here because the lesser value is located
			  //lower down the Array List	
			}else{
				outEdges.set(i, outEdges.get(j));
				outEdges.set(j, temp);
			}	
		}else{	
			outEdges.set(i, outEdges.get(j));
			outEdges.set(j, temp);
		}
	}

	/*This Method will sort an Outgoing EdgeList by descending lexographic label
	  values. It is a slightly Modified version of quick Sort Method. I must
	  give credit to HowToDoJava.com for this method*/
	private void quickSortStrings(ArrayList<Edge> outEdges, int low, int high){
		//this if statement is used to detect and empty
		//arraylist or indicate when it is already sorted.
		String pivotStr = null;
		if(outEdges.equals(null) || outEdges.size() == 0){
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid = low + (high - low)/2;


		//quick sort uses pivots to sort the values against, this pivot is the
		//name string in the middle of the Array List. It will use it as a
		//reference to sort the two havles of arraylist.
		pivotStr = outEdges.get(mid).getHead().getName();

		int i = low;
		int j = high;
		
		while (i <= j){
			while(outEdges.get(i).getHead().getName().compareTo(pivotStr) < 0){
				i++;
			}
			while(outEdges.get(j).getHead().getName().compareTo(pivotStr) > 0){
				j--;
			}
			if(i <= j){
				swapEdge(outEdges, i, j);
				i++;
				j--;
			} 
		}
		if (low < j){
			quickSortStrings(outEdges,low, j);
		}
		if (high  >  i){
			quickSortStrings(outEdges, i, high);
		} 
	}


	/*This is a helper function for the quickSortStrings Method, which does the
	  Swapping*/
	private void swapEdge(ArrayList<Edge> outEdges, int i, int j){	
		Edge temp = outEdges.get(i);	
		outEdges.set(i, outEdges.get(j));
		outEdges.set(j, temp);
	

	}


	/*This Method will sort a NodeList ascending integer start time
	  values. It is used by the DelivBPrintOut method to pringt out
	  the nodes by ascending start times.
	  It is a slightly Modified version of quick Sort Method. I must
	  give credit to HowToDoJava.com for this method*/
	private void quickSortByStartTime(ArrayList<Node> nodes , int low, int high){
		if(nodes.equals(null) || nodes.size() == 0) {
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid = low + (high - low)/2;
	
		//quick sort uses pivots to sort the values against, this pivot is the
		//middle of the pack start time to be used as a sort refrence.
		int pivot = nodes.get(mid).getStartTime();
		
		int i = low;
		int j = high;
			
		while (i <= j){
			while(nodes.get(i).getStartTime() < pivot){
				i++;
			}
			while(nodes.get(j).getStartTime() > pivot){
				j--;
			}
			
			if(i <= j){
				swapNode(nodes, i, j);
				i++;
				j--;
			}

		}
		if (low < j){
			quickSortByStartTime(nodes,low, j);
		}
		if (high  > i){
			quickSortByStartTime(nodes, i, high);
		}
	}

	/*This Method will sort a NodeList ascending integer start time
	  values. It is used by the DFS method to pringt out
	  the nodes by ascending start times.
	  It is a slightly Modified version of quick Sort Method. I must
	  give credit to HowToDoJava.com for this method*/
	private void quickSortNames(ArrayList<Node> nodes , int low, int high){
		//this if statement is used to detect and empty
		//arraylist or indicate when it is already sorted.
		if(nodes.equals(null) || nodes.size() == 0) {
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid = low +(high - low)/2;

		//quick sort uses pivots to sort the values against, this pivot is the
		//middle of the pack start time to be used as a sort refrence.
		String pivotStr = nodes.get(mid).getName();
		
		int i = low;
		int j = high;
			
		while (i <= j){
			while(nodes.get(i).getName().compareTo(pivotStr) > 0){
				i++;
			}
			while(nodes.get(j).getName().compareTo(pivotStr) < 0){
				j--;
			}
			if(i <= j){
				swapNode(nodes, i, j);
				i++;
				j--;
			}
		}
		if (low < j){
			quickSortNames(nodes,low, j);
		}
		if (high  > i){
			quickSortNames(nodes, i, high);
		}
	}
	//This method is a helper for the quickSortNames AND quickSortByFinishTimes
	//Methods.
	private void swapNode(ArrayList<Node> nodes, int i, int j){
		Node temp = nodes.get(i);
		nodes.set(i,nodes.get(j));
		nodes.set(j,temp);
	}
	
	//This Method sets Only Cross; Backward and Forward Edges.
	//It uses the start times and finish times to determine
	//wether the edges are backward forward or cross edges.
	//The dFS_visit method sets the Tree edges. I decided to
	//Label Tree edges and Forward Eges Seperate times because
	//They have identical finish and start time ratio between 
	//tail and head nodes.	
	private void setEdgeType2(Graph gp){	
		for(int i = 0; i <= gp.edgeList.size() - 1; i++){		
			if(gp.edgeList.get(i).getType().equals("_")){
			
				//This sets all the Forward Edges by examing finish times
				//and start times of head and tail nodes.	
				if((gp.edgeList.get(i).getTail().getStartTime() 
				   < gp.edgeList.get(i).getHead().getStartTime())
				   && ( gp.edgeList.get(i).getTail().getFinishTime() 
				   > gp.edgeList.get(i).getHead().getFinishTime())){
					
					gp.edgeList.get(i).setType("F");
				}
				
				//This sets all the Backward Edges by examing finish times
				//and start times of head and tail nodes.	
				if(((gp.edgeList.get(i).getTail().getStartTime()
				  > gp.edgeList.get(i).getHead().getStartTime())
				   && (gp.edgeList.get(i).getTail().getFinishTime()
				   < gp.edgeList.get(i).getHead().getFinishTime()))||
				gp.edgeList.get(i).getTail() == gp.edgeList.get(i).getHead()){
					
					gp.edgeList.get(i).setType("B");   
				}

				//This sets all the Cross Edges by examing finish times
				//and start times of head and tail nodes.	
				if(gp.edgeList.get(i).getTail().getStartTime() 
				 > gp.edgeList.get(i).getHead().getFinishTime()){
					gp.edgeList.get(i).setType("C");   
				}
			}
		}
	}

	//This is a helper function for isEdgesALLInts
	//Method, it checks wether each value is a
	//String or int by catching the NumberFormatException
	//For all non Ints And then it checks wether the number
	//is a negative.
	private boolean isInt(String num){
		try{
			int number = Integer.parseInt(num);
		}catch(NumberFormatException nfe){
			return false; 	
		}
		int number = Integer.parseInt(num);
			return(number >= 0);
	} 

	//This method checks wether all values are ints or not by 
	//converting each edge label to int and checking if it is
	//a positive. If it is not a positive int this method returns
	//false
	private boolean isEdgesAllInts(Graph gp){
		for(int i = 0; i <= gp.getEdgeList().size() - 1; i++){
			if(!(gp.getEdgeList().get(i).getLabel().equals(null))){
				if(!(isInt(gp.getEdgeList().get(i).getLabel()))){ 
					return false;
				}
			}	
		}
		return true;	
	}					
	//This method is the top of the tree of method calls. It works with the
	//recursive dFS_Visit. This method is responsible for 
	//finding the Node with S Value. It is also responsible
	//for choosing what Node to start backup at when the recursive
	// dFS_Visit runs out of Nodes To visit.	
	private void dFS(Graph gp) {
		for(int i = 0; i <= gp.nodeList.size() - 1; i++){
			gp.nodeList.get(i).setColor("WHITE");
		}
	
		for(int i = 0; i <= gp.edgeList.size() - 1; i++){
			gp.edgeList.get(i).setType("_");
		}			
		
		time = 0;
		//This will be set to false when The while loop finds the
		//S value to start the dFS with.
		boolean control = true;
		
		//This index is needed so I don't go out of Bounds when traversing
		//the node list below.
		int index = 0;	
		
		//This is where the dFS method finds the start Node.
		//It looks for a white Node with S as a value then
		//calls the recursive dFS_Visit method.	
		while(control && (index <= gp.getNodeList().size() - 1)){
			if((gp.getNodeList().get(index).getVal().equals("S")) 
			 && (gp.nodeList.get(index).getColor().equals("WHITE"))){
				dFS_Visit(gp, gp.getNodeList().get(index));
				control = false;
			}	
			index++;
		}
		
		//Sorts the nodelist of the graph by ascending order so When we
		//run out of paths to explore we can start with the node with the
		//lowest lexographic value.	
		quickSortNames(gp.getNodeList(), 0, gp.getNodeList().size() - 1); 
		
		//this is where we start back up on the depth first search
		//when we exhausted a certain path.	
		for(int i = 0; i <= gp.getNodeList().size() - 1; i++){		
			if(gp.nodeList.get(i).getColor().equals("WHITE")){
				dFS_Visit(gp, gp.getNodeList().get(i));
			}
		
		}
	}

	//This method does most of the work. It decides which paths to take
	//and It sets that start and finish times. It does this by using the
	//color of the Node to avoid cycles and loops. 
	private void dFS_Visit(Graph gp, Node nde){
		//Setting start time and placing node on the frontier.
		time = time + 1;
		nde.setStartTime(time);
		nde.setColor("GRAY");
			
		//This is where I test the type of Edges I am dealing with and use the
		//appropriate sort algorightm to order the paths to explore in the proper
		//order specified by the professor.
		if(isEdgesAllInts(gp)){
			quickSortInts(nde.getOutgoingEdges(), 0, nde.getOutgoingEdges().size() - 1); 
		} else { 
			quickSortStrings(nde.getOutgoingEdges(), 0, nde.getOutgoingEdges().size() - 1);
		}
		
		//This for loop calls the recursive visit method on all outgoing nodes
		//that are unexpolred(white).
		for(int i = 0; i <= nde.getOutgoingEdges().size() - 1; i++){
			if((nde.getOutgoingEdges().get(i).getHead().getColor().equals("WHITE"))){
				dFS_Visit(gp, nde.getOutgoingEdges().get(i).getHead());
				nde.getOutgoingEdges().get(i).setType("T");	
			}
			
		}	 	
        
		//When the node is colored Black that signifies that it and all outgoing
		//nodes linked to it are done being explored.  
		nde.setColor("BLACK");
		time = time + 1;
		nde.setFinishTime(time);
	}
	
	//This method just does the formating in printing out to the console and
	//Writing the output to the output file. It is like the 
	//toString of Deliverable B. 	
	private void delivbPrintOut(Graph gp) {	
		
		//This sorts all the nodes by ascending start time so I can print them
		//in the order specified by the professor 
		quickSortByStartTime(gp.nodeList, 0, gp.nodeList.size() - 1);
	
		
		output.printf("%-14s", "Node");        
		output.printf("%-14s", "Start Time");  
		output.printf("%-14s", "Finish Time");
		output.println("");
		
		
				
		for(int i = 0; i <= gp.nodeList.size() - 1; i++){
			output.printf("%-18s", gp.nodeList.get(i).getName());        
			output.printf("%-14s", gp.nodeList.get(i).getStartTime());  
			output.printf("%-14s", gp.nodeList.get(i).getFinishTime());
			output.println("");
		}
		
		output.println("");
		output.printf("%-16s", "Edge");        
		output.printf("%-18s", "Type");  
		output.println("");
		
		for(int i = 0; i <= gp.edgeList.size() - 1; i++){
			output.printf("%-18s", gp.edgeList.get(i).getTail().getAbbrev() + 
			  "-" + gp.edgeList.get(i).getHead().getAbbrev());        
			output.printf("%-16s", gp.edgeList.get(i).getType());  
			output.println("");
		}
		output.close();	
		System.out.printf("%-14s", "Node");        
		System.out.printf("%-14s", "Start Time");  
		System.out.printf("%-14s", "Finish Time");
		System.out.println("");
		
		
				
		for(int i = 0; i <= gp.nodeList.size() - 1; i++){
			System.out.printf("%-18s", gp.nodeList.get(i).getName());        
			System.out.printf("%-14s", gp.nodeList.get(i).getStartTime());  
			System.out.printf("%-14s", gp.nodeList.get(i).getFinishTime());
			System.out.println("");
		}
		
		System.out.println("");
		System.out.printf("%-16s", "Edge");        
		System.out.printf("%-18s", "Type");  
		System.out.println("");
		
		for(int i = 0; i <= gp.edgeList.size() - 1; i++){
			System.out.printf("%-18s", gp.edgeList.get(i).getTail().getAbbrev() + 
			  "-" + gp.edgeList.get(i).getHead().getAbbrev());        
			System.out.printf("%-16s", gp.edgeList.get(i).getType());  
			System.out.println("");
		}
	} 
}
