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
		outputFile = new File( outputFileName );
		if ( outputFile.exists() ) {    // For retests
			outputFile.delete();
		}
		try {                                         
			output = new PrintWriter(outputFile);     
		}                                             
		catch (Exception x ) {                        
    		System.err.format("Exception: %s%n", x);  
    		System.exit(0);                           
		}        
	
		Graph grph = new Graph();
		grph = g;                                     
		depthFirstOuter(grph);
		setEdgeType2(grph);
		delivbPrintOut(grph);	
	}
	public	void quickSortIntEdge(ArrayList<Edge> outEdges, int low, int high){	
		int pivotInt = 0;
		if(outEdges.equals(null) || outEdges.size() == 0){
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid =(low + high)/2;
		pivotInt = Integer.parseInt(outEdges.get(mid).getLabel());

		int i = low;
		int j = high;
		
		while (i <= j){
			while(Integer.parseInt(outEdges.get(i).getLabel()) < pivotInt){
				i = i + 1;
			}
			while(Integer.parseInt(outEdges.get(j).getLabel()) > pivotInt){
				j = j - 1;
			}	

			if(i <= j){
				swapIntEdge(outEdges, i, j);
				i = i + 1;
				j = j - 1;
			}
		}
		if (low < j){
			quickSortIntEdge(outEdges,low, j);
		}
		if (high  >  i){
			quickSortIntEdge(outEdges, i, high);
		}
	}
	private void swapIntEdge(ArrayList<Edge> outEdges, int i, int j){	
		Edge temp = outEdges.get(i);			
		if(outEdges.get(i).equals(outEdges.get(j))){
			if((outEdges.get(i).getHead().getName().compareTo(
							outEdges.get(j).getHead().getName())) > 0){
			//Do Nothing
			}else{
				outEdges.set(i, outEdges.get(j));
				outEdges.set(j, temp);
			}	
		}	
		outEdges.set(i, outEdges.get(j));
		outEdges.set(j, temp);
	}

	public void quickSortEdge(ArrayList<Edge> outEdges, int low, int high){
		String pivotStr = null;
		if(outEdges.equals(null) || outEdges.size() == 0){
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid =(low + high)/2;
		pivotStr = outEdges.get(mid).getLabel();

		int i = low;
		int j = high;
		
		while (i <= j){
			while(outEdges.get(i).getLabel().compareTo(pivotStr) < 0){
				i = i + 1;
			}
			while(outEdges.get(j).getLabel().compareTo(pivotStr) > 0){
				j = j - 1;
			}
			if(i <= j){
				swapEdge(outEdges, i, j);
				i = i + 1;
				j = j - 1;
			} 
		}
		if (low < j){
			quickSortEdge(outEdges,low, j);
		}
		if (high  >  i){
			quickSortEdge(outEdges, i, high);
		} 
	}
	private void swapEdge(ArrayList<Edge> outEdges, int i, int j){	
		Edge temp = outEdges.get(i);
		outEdges.set(i, outEdges.get(j));
		outEdges.set(j, temp);

	}
	public void quickSortNode(ArrayList<Node> nodes , int low, int high){
		if(nodes == null || nodes.size() == 0) {
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid = (low + high)/2;
		int pivot = nodes.get(mid).getStartTime();
		
		int i = low;
		int j = high;
			
		while (i <= j){
			while(nodes.get(i).getStartTime() < pivot){
				i = i + 1;
			}
			while(nodes.get(j).getStartTime() > pivot){
				j = j - 1;
			}
			
			if(i <= j){
				swapNode(nodes, i, j);
				i = i + 1;
				j = j - 1;
			}

		}
		if (low < j){
			quickSortNode(nodes,low, j);
		}
		if (high  > i){
			quickSortNode(nodes, i, high);
		}
	}
	public void quickSortNodeName(ArrayList<Node> nodes , int low, int high){
		String pivotStr = null;  
		if(nodes == null || nodes.size() == 0) {
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid = (low + high)/2;
		pivotStr = nodes.get(mid).getName();
		
		int i = low;
		int j = high;
			
		while (i <= j){
			while(nodes.get(i).getName().compareTo(pivotStr) < 0){
				i = i + 1;
			}
			while(nodes.get(j).getName().compareTo(pivotStr) > 0){
				j = j - 1;
			}
			if(i <= j){
				swapNode(nodes, i, j);
				i = i + 1;
				j = j - 1;
			}
		}
		if (low < j){
			quickSortNodeName(nodes,low, j);
		}
		if (high  > i){
			quickSortNodeName(nodes, i, high);
		}
	}
	private void swapNode(ArrayList<Node> nodes, int i, int j){
		Node temp = nodes.get(i);
		nodes.set(i,nodes.get(j));
		nodes.set(j,temp);
	}	
	public void setEdgeType2(Graph goo2){	
		for(int i = 0; i < goo2.edgeList.size() - 1; i++){		
			if(goo2.edgeList.get(i).getType().equals("_")){
				
				if((goo2.edgeList.get(i).getTail().getStartTime() 
				   < goo2.edgeList.get(i).getHead().getStartTime())
				   && ( goo2.edgeList.get(i).getTail().getFinishTime() 
				   > goo2.edgeList.get(i).getHead().getFinishTime())){
					
					goo2.edgeList.get(i).setType("F");
				}
				
				if(((goo2.edgeList.get(i).getTail().getStartTime()
				  > goo2.edgeList.get(i).getHead().getStartTime())
				   && (goo2.edgeList.get(i).getTail().getFinishTime()
				   < goo2.edgeList.get(i).getHead().getFinishTime()))||
				goo2.edgeList.get(i).getTail() == goo2.edgeList.get(i).getHead()){
					
					goo2.edgeList.get(i).setType("B");   
				}

				if(goo2.edgeList.get(i).getTail().getStartTime() 
				 > goo2.edgeList.get(i).getHead().getFinishTime()){
					goo2.edgeList.get(i).setType("C");   
				}
			}
		}
	}
	public boolean isInt(String num){
		try{
			int number = Integer.parseInt(num);
		}catch(NumberFormatException nfe){
			return false; 	
		}
		return true;
	} 

	public boolean edgesAllInts(Graph gpp){
		for(int i = 0; i < gpp.getEdgeList().size() - 1; i++){
			if(!(gpp.getEdgeList().get(i).getLabel().equals(null))){
				if(!(isInt(gpp.getEdgeList().get(i).getLabel()))){ 
					return false;
				}
			}	
		}
		return true;	
	}					
	public void depthFirstOuter(Graph goo) {
			time = 0;
			for(int i = 0; i < goo.nodeList.size() - 1; i++){
				goo.nodeList.get(i).setColor("WHITE");
			}
		
			for(int i = 0; i < goo.edgeList.size() - 1; i++){
				goo.edgeList.get(i).setType("_");
			}			
			
			boolean control = true;
			int index = 0;	
		
			while(control && (index < goo.getNodeList().size() - 1)){
				if(goo.getNodeList().get(index).getVal().equals("S")){
					depthFirstRecursive(goo, goo.getNodeList().get(index));
					control = false;
				}	
				index++;
			}
			quickSortNodeName(goo.getNodeList(), 0, goo.getNodeList().size() - 1); 
			for(int i = 0; i < goo.getNodeList().size() - 1; i++){		
				if(goo.nodeList.get(i).getColor().equals("WHITE")){
					depthFirstRecursive(goo, goo.getNodeList().get(i));
				}
			}	
	}

	public void depthFirstRecursive(Graph gee, Node nde){
			time = time + 1;
			nde.setStartTime(time);
			nde.setColor("GRAY");
			
			if(edgesAllInts(gee)){
				quickSortIntEdge(nde.getOutgoingEdges(), 0, nde.getOutgoingEdges().size() - 1); 
			} else { 
				quickSortEdge(nde.getOutgoingEdges(), 0, nde.getOutgoingEdges().size() - 1);
			}
			try{
			if(!(nde.getOutgoingEdges().equals(null))&& !(nde.getOutgoingEdges().size() == 0)){	
				for(int i = 0; i < (nde.getOutgoingEdges().size() - 1); i++){
					if(nde.getOutgoingEdges().get(i).getHead().getColor().equals("WHITE")){
						if(!(nde.getOutgoingEdges().get(i).getHead().equals(null))
						 	&&!(nde.getOutgoingEdges().get(i).getHead().equals(
						  	 nde.getOutgoingEdges().get(i).getTail()))){
							
							depthFirstRecursive(gee, nde.getOutgoingEdges().get(i).getHead());
							nde.getOutgoingEdges().get(i).setType("T");
						}else{
							//Do Nothing
						}		
					}
				}	 	
			} 
			}catch(NullPointerException npe){
				System.out.println("Name" + nde.getName());
				System.out.println(nde.getOutgoingEdges().get(0).getHead().getName());
				System.out.println(nde.getOutgoingEdges().get(1).getHead().getName());
			//	System.out.println(nde.getOutgoingEdges().get(2).getHead().getName());
			//	System.out.println(nde.getOutgoingEdges().get(3).getHead().getName());
			}
			nde.setColor("BLACK");
			time = time + 1;
			nde.setFinishTime(time);
		
	}	
	public void delivbPrintOut(Graph gp) {	
		System.out.printf("%-14s", "Node");        
		System.out.printf("%-14s", "Start Time");  
		System.out.printf("%-14s", "Finish Time");
		System.out.println("");
		
		quickSortNode(gp.nodeList, 0, gp.nodeList.size() - 1);
		
		for(int i = 1; i < gp.nodeList.size() - 1; i++){
			System.out.printf("%-18s", gp.nodeList.get(i).getName());        
			System.out.printf("%-14s", gp.nodeList.get(i).getStartTime());  
			System.out.printf("%-14s", gp.nodeList.get(i).getFinishTime());
			System.out.println("");
		}
		
		System.out.println("");
		System.out.printf("%-16s", "Edge");        
		System.out.printf("%-18s", "Type");  
		System.out.println("");
		
		for(int i = 0; i < gp.edgeList.size() - 2; i++){
			System.out.printf("%-18s", gp.edgeList.get(i).getTail().getAbbrev() + 
			  "-" + gp.edgeList.get(i).getHead().getAbbrev());        
			System.out.printf("%-16s", gp.edgeList.get(i).getType());  
			System.out.println("");
		}
	} 
}
