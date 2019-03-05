import java.io.*;
import java.util.*;

// Class DelivA does the work for deliverable DelivA of the Prog340

public class DelivA {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	
	public DelivA( File in, Graph gr ) {
		inputFile = in;
		g = gr;
		
		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 ); // Strip off ".txt"
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
        /*Since there is an Arraylist with all the edgelists stored inside. I decided to 
         do a for loop which prints out all the tail and head nodes and the edge label. I
	    used string concatination to match the required output format*/

        /* I must give credit to Derek Banas youtube tutorial for introducing me to the 
        size() method of the ArrayList class.*/
       
        for(int i = 0; i < g.edgeList.size(); i++) {
        	System.out.println("Edge from " + g.edgeList.get(i).getTail().getName() 
              + " to " + g.edgeList.get(i).getHead().getName() +
              " labeled " + g.edgeList.get(i).getLabel() + ".");
        	output.println("Edge from " + g.edgeList.get(i).getTail().getName() 
              + " to " + g.edgeList.get(i).getHead().getName() +
              " labeled " + g.edgeList.get(i).getLabel() + ".");
		}
	}
}
