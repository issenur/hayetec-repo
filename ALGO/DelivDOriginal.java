import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

// Class DelivD does the work for deliverable DelivD of the Prog340

public class DelivD {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	int path_length = 0;
	int[] zValArr;
	int[] zValArr2;
	int temp;
	int min = 9999999;
	
	public DelivD( File in, Graph gr ) {
		inputFile = in;
		g = gr;

		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 );
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
		int size  = g.getNodeList().size();

		zValArr = new int[size];
		zValArr2 = new int[size];
		quickSortLats(g.getNodeList(), 0, g.getNodeList().size() -1);
		zLooper(g.getNodeList().size() - 1);
	}
	
    public int getMinValue(int[] numbers){                                      
 		int minValue = numbers[0];                                              
 		for(int i=1;i<numbers.length;i++){                                      
			if(numbers[i] > 10){
				if(numbers[i] < minValue){                                          
					minValue = numbers[i];                                          
				}
			}else{
			
			}                                                                   
  		}                                                                       
  		return minValue;
	}     
	
	private void quickSortLats(ArrayList<Node> nodes , int low, int high){   	
		if(nodes.equals(null) || nodes.size() == 0) {                           
		  return;                                                             
		}                                                                       
																			  
		if(low >= high){                                                        
		  return;                                                             
		}                                                                       
																			  
		int mid = low + (high - low)/2;                                         
																			  
		float pivot = Float.parseFloat( nodes.get(mid).getVal());                              
																			  
		int i = low;                                                            
		int j = high;                                                           
																			  
		while (i <= j){                                                         
		  while(Float.parseFloat(nodes.get(i).getVal()) < pivot){                         
			  i++;                                                            
		  }                                                                   
		  while(Float.parseFloat(nodes.get(j).getVal()) > pivot){                         
			  j--;                                                            
		  }                                                                   
																			  
		  if(i <= j){                                                         
			  swapNode(nodes, i, j);                                          
			  i++;                                                            
			  j--;                                                            
		  }                                                                   
																			  
		}                                                                       
		if (low < j){                                                           
		  quickSortLats(nodes,low, j);                                 
		}                                                                       
		if (high  > i){                                                         
		  quickSortLats(nodes, i, high);                               
		}                                                                       
	}

	private void swapNode(ArrayList<Node> nodes, int i, int j){    
		Node temp = nodes.get(i);                                  
		nodes.set(i,nodes.get(j));                                 
		nodes.set(j,temp);                                         
	}
	
	private int dz(Node northNde, Node southNde){
		for(int i = 0; i <= g.getEdgeList().size() - 1; i++){                   
			if((g.getEdgeList().get(i).getTail().getName().equals(northNde.getName()))
			  &&(g.getEdgeList().get(i).getHead().getName().equals(southNde.getName()))){
				return Integer.parseInt(g.getEdgeList().get(i).getLabel());     
			}                                                                   
		}                                                                       
		return -1;                                                              
	}
	
	private int dz2(int southCity, int northCity){
		path_length = 0;
		for(int i = southCity; i < northCity ; i++){
			path_length = path_length + 
				dz(g.getNodeList().get(i + 1), g.getNodeList().get(i));		
		}
		return path_length; 
	}

	
	private int z_ofJ(int p, int k){
		
	  for( int i = k; i <= p; i++){
			if(zValArr[i] > 10){
				zValArr2[i] = dz(g.getNodeList().get(p - 1), g.getNodeList().get(i - 2)) + zValArr[i] 
					+ dz2(i - 1, p - 2);
			}else{
				zValArr2[i] = dz(g.getNodeList().get(p - 1), g.getNodeList().get(i - 2)) + z_ofJ2(i,k+1) 
					+ dz2(i - 1, p - 2);
			}
		}
		return getMinValue(zValArr2);
	}
	
	
	private int z_ofJ2(int p, int k){
		if(p == 2){
			zValArr2[p] = dz(g.getNodeList().get(p-1), g.getNodeList().get(p-2));
			return zValArr2[p];
		}else if(p == 3){
			zValArr2[p] = dz(g.getNodeList().get(p-1), g.getNodeList().get(p-3)) 
				+ dz(g.getNodeList().get(p-2), g.getNodeList().get(p-3));
			return zValArr2[p];
		}else{
			zValArr2[p] = z_ofJ( p-1 ,2);
			return getMinValue(zValArr2);
		}
	}
	private void z_ofP(int p){
		if(p == 2){
			System.out.println("P = " + p);
			zValArr[p] = dz(g.getNodeList().get(p-1), g.getNodeList().get(p-2));
			System.out.println("Z(" + p + ") = " + zValArr[p]);
		}else if(p == 3){
			System.out.println("P = " + p);
			zValArr[p] = dz(g.getNodeList().get(p-1), g.getNodeList().get(p-3)) 
				+ dz(g.getNodeList().get(p-2), g.getNodeList().get(p-3));
			System.out.println("Z(" + p + ") = " + zValArr[p]);
		}else{
			System.out.println("P = " + p);
			System.out.println("Z(" + p + ") = " + z_ofJ((p-1),2));
		}
	}
	
	public void zLooper(int p){
		for(int i = 2; i <= p; i++){
			z_ofP(i);
		}
	}				                                                   
}
 
