import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;    

// Class DelivC does the work for deliverable DelivC of the Prog340
/*                             THE REPORT

I used Stochastic Local Search Algorithm. It consists of having a greedy-descent
feature, random restarts and random walks. I created a Tour class which is an 
arraylist of nodes all different except the repeated node at index[0] AND 
index[size - 1] which represents the origin node.


The first tour I created  by starting at a random node, and adding its closest 
(by distance) neighboring node to the tour. I did this by sorting all outgoing 
edges by distance and picking the tail node which belongs to that edge.


My random walks were swapping a random two cities in the tour. My random 
restarts were by abandoning a tour with an origin city and starting over with  
a new origin city.


My greedy-descent is by quickly abandoning any tour that does not result in a 
more optimal tour within 50 iterations.


I parametrized the rate of random restarts and random walks. I set the 
algorithm to do one to two swaps/random-walks at most during any tour. I choose 
that range because I believe excessive random walking would not yield a good 
result for this particular  approach to this problem. I did this by setting up 
an integer which has a value equivalent to half the amount of nodes in the tour. 
With every iteration that number gets decremented by one. When it reaches zero 
there will be a random swap. This ensures that swaps will be kept to at most 
two in any tour.


I do however believe that switching origin cities excessively would yield a 
more optimal result. That is why I implemented my algorithm with many random 
restarts. I set it up so the algorithm does a random restart every  50 iteration 
it goes without finding a new optimal tour. 

My results seem very good, I get the very optimal solutions 90% of the time for 
all graphs sometimes under 9,000 iterations.                                                                  
 */                                                                              

public class DelivC {

	File inputFile; 
	File outputFile;
	PrintWriter outputS; 
	Graph g;
	Tour t;
	//this global Tour refrence always points to minimum distance tour
	Tour mostOptimalTour;
	//This is my way of stopping the algorithm from running for every
	//when loopCount == 0 the program halts.
	int loopCount = 50;
	int steps = 0;	
	//this is int is used to sum up the lengths between each two cities.
	int accumulator;
	String str;
	//the next four variables are used in the Walk() method to swap whichever
	//two cities the left and right pointers point at
	Node left;
	Node right;
	int randomLeft;
	int randomRight;
	
	//this is an arbitrary min tour that I start the program with
	//it represents the length of the tour. It will later get over written
	int mostOptimal = 999999;
	//this int holds the count down to when the next swap will happen
	int nextWalk;
	//this int holds the count down to when the next random restart will happen
	int nextRestart = 50;


	public DelivC( File in, Graph gr ) {
		inputFile = in;
		g = gr;
	
		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring
( 0, inputFileName.length()-4 ); // Strip off ".txt"
		String outputFileName = baseFileName.concat( "S.txt" );
		outputFile = new File( outputFileName );
		if ( outputFile.exists() ) {    // For retests
			outputFile.delete();
		}
		
		try {
			outputS = new PrintWriter(outputFile);			
		}
		catch (Exception x ) { 
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		//I am using this area like a main method. This where the stack of 
		//method calls starts.
		stochLocalSrch(g);
		summaryPrint();
	}
	/*This Method will sort an Outgoing EdgeList by ascending integer edge values.
     I must give credit to HowToDoJava.com for this method and also this method is
	 based on quicksort algorithm*/                          
	private	void quickSortInts(ArrayList<Edge> outEdges, int low, int high){	
		if(outEdges.equals(null) || outEdges.size() == 0){
			return;
		}
		
		if(low >= high){
			return;
		}

		int mid = low + (high - low)/2;

		int pivotInt = Integer.parseInt(outEdges.get(mid).getLabel());

		int i = low;
		int j = high;
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
		Edge temp = outEdges.get(i);

       if(outEdges.get(i).getLabel().equals(outEdges.get(j).getLabel())){      
           if((outEdges.get(i).getHead().getName().compareTo(                  
                           outEdges.get(j).getHead().getName())) < 0){         
           }else{                                                              
               outEdges.set(i, outEdges.get(j));                               
               outEdges.set(j, temp);                                          
           }                                                                   
       }else{                                                                  
           outEdges.set(i, outEdges.get(j));                                   
           outEdges.set(j, temp);                                              
       }                                                                       
   }                                                                           
	//this method finds the distance between to given nodes in the graph g.
	//it does this by comparing the head and tail of all edges in graph g
	//then it returns the edge value which represents the distance.
	//the two nodes are passed in as paramaters.
	private int edgeCost(Node ndeTail, Node ndeHead){
		for(int i = 0; i <= g.getEdgeList().size() - 1; i++){
			if((g.getEdgeList().get(i).getTail().getName().equals(ndeTail.getName()))
			  &&(g.getEdgeList().get(i).getHead().getName().equals(ndeHead.getName()))){
				return Integer.parseInt(g.getEdgeList().get(i).getLabel());
			}
		}
		System.out.println("If all nodes in g all connected this will"
							+ "never print");	
		
		return -1;
	}
	//This method takes all the nodes from graph g.nodeList and then
	//assembles all the nodes in a way which makes it a TSP tour.
	//And it will make the origin city/node whatever node is passed into it.
	
	//I use node color attribute to distinguish which nodes are in the 
	//tour and which nodes have yet to be placed in the tour.
	
	//Yellow means it is not in the Tour
	//Green means it is in the Tour.
	//once this method is done making a tour it will return the given tour.
	private Tour makeTour(Graph gp, Node nde){
		Tour tp = new Tour(mostOptimal, nextWalk, nextRestart);	
		makeTourHelper(tp,nde);
		nde.setColor("YELLOW");
		tp.addNodeTourNodes(nde);
		return tp;	
	}
	//This method is a continuation of the makeTour method, it does the same
	//thing.
	private void makeTourHelper(Tour tp, Node nde){	
		if(nde.getColor().equals("GREEN")){	
			quickSortInts(nde.getOutgoingEdges(), 0, nde.getOutgoingEdges().size() -1);
			for(int i = 0; i <= nde.getOutgoingEdges().size() - 1; i++){		    		
				if((nde.getOutgoingEdges().get(i).getHead().getColor().equals("GREEN"))
					|| (i == (nde.getOutgoingEdges().size()-1))){
					tp.addNodeTourNodes(nde);
					nde.setColor("YELLOW");	
					makeTourHelper(tp, nde.getOutgoingEdges().get(i).getHead());
					return;
				}
			}
		}
	}

	//This method is the main method which all the other methods work for
	//it takes a graph g as input, selects a random node out of it.
	
	//it then passes it to makeTour() method to make a tour with the given node
	//as origin city.

	//It then passes that tour to the greedyDescent method which does a bunch of
	//walks and swaps until it can no longer find an optimal tour.
	private void stochLocalSrch(Graph gp){
		int randomStart = (int)(Math.random()*(gp.getNodeList().size() - 1));
		for(int i = 0; i <= gp.nodeList.size() - 1; i++){
			gp.nodeList.get(i).setColor("GREEN");
		}                                                   
		//here I pick the random node out of Graph g
		Node nde = gp.getNodeList().get(randomStart);
		
		//here make tour returns a tour t which has the random node
		//nde as its origin city
		t = makeTour(gp, nde);
		
		t.setNextRestart(nextRestart);
		//here is where I set how often random swaps are to happen
		t.setNextRandomWalk(t.getTourNodes().size()/2);
		
		steps++;
		
		//within greedydescent tour t is mutated and measured for optimal length.
		//if it does not produce a more optimal solution it is terminated and a 
		//new random restart will start a new tour.
		greedyDescent(gp, t);		
		
		//the below while loop just forces recursive calls to this method
		//upto a certain random count.
		while(loopCount > 0){
			loopCount--;
			stochLocalSrch(gp);
		}
	}
		
	//This method takes a tour and does random swaps until the random restart
	//is triggered
	//It has a helper  method called walk(). Walk the swapping of nodes.
	//tour.
	private void greedyDescent(Graph gp, Tour t){	
	
		while(t.getNextRestart() > 0){			
			Tour currentTour = walk(t);
			int currentLength = currentTour.getTourLength();
			
			if(currentLength < mostOptimal){
				mostOptimal = currentLength;
				mostOptimalTour = currentTour;
				t.setNextRestart(nextRestart);
				tourPrint(mostOptimalTour);
				System.out.println("");
				outputS.println("");
			}
		}	
	}	
	//This tour that does the swapping of nodes
	private Tour walk(Tour t){
		accumulator = 0;
		steps++;
		//random left is (t.nodes.size() -3) + 1 so I don't end up swapping the
		//origin nodes.	
		randomLeft = (int)(Math.random()*(t.getTourNodes().size()  - 3) + 1);
		randomRight = randomLeft + 1;
		//the rest of this method is doing a simple swap with the temp node
		//as the place holder for the swapping
		Node temp = t.getTourNodes().get(randomLeft);	
		t.setNextRandomWalk(t.getNextRandomWalk() - 1);
		t.setNextRestart(t.getNextRestart() - 1);

		if(t.getNextRandomWalk() == 0){
			left = t.getTourNodes().get(randomLeft);
			right = t.getTourNodes().get(randomRight);
			t.tourNodes.set(randomLeft, right);
			t.tourNodes.set(randomRight,temp);
			t.setNextRandomWalk(nextWalk);		
		}
		// here is where I calculate the resulting tour lenght.
		for(int i = 0; i <= t.tourNodes.size() - 2; i++){ 
			accumulator = accumulator
			 + edgeCost(t.getTourNodes().get(i), t.getTourNodes().get(i + 1));	 					
		}		
		//here is where I use the setter to stamp it on Tour t.
		t.setTourLength(accumulator);		
		return t;
	}
	//All this Method does is print to the console and the summary
	//output file.
	private void tourPrint(Tour tr){
		str = " ";
		for(int i = 0; i <= tr.getTourNodes().size() - 1; i++){                      
			str = str + tr.getTourNodes().get(i).getVal();
			System.out.print(tr.getTourNodes().get(i).getVal());
			outputS.print(tr.getTourNodes().get(i).getVal());
			
		}
		str = str + " " + Integer.toString(tr.getTourLength());
		System.out.print(" " + tr.getTourLength());		
		outputS.print(" " + tr.getTourLength());		
    }	

	//All this Method does is print to the console and the summary
	//output file.
	private void summaryPrint(){                      
		System.out.print("Shortest path found was");        
		System.out.print(str);	
		System.out.print(" after " + steps + " steps.");
		   
		outputS.print("Shortest path found was");        
		outputS.print(str);	
		outputS.print(" after " + steps + " steps.");
		outputS.close();
	}
}                      

                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 

