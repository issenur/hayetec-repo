class Box {

   public int row;
   public int col;
	
	public Box(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public void addBox(int n, int m, Box[][] b) {
	  b[i][j] = Box(n,m);
	  j = j + 1;
	}
    
	public boolean contains (int n, int m, int i, Box[][] b){
		for(int k = 0; k < j; k++) {
		 if (equalsBox(n, m, b[i][k])) {
				return true;
			}
		}
		return false;
   }
	
   public static boolean equalsBox(int n, int m, Box b) {
	   return (n == getRow(b)) && (m == getCol(b));
	}
	public int getCol(Box Box){
	   return col;
	}
	
	public int getRow(Box Box){
	   return row;
	}
}

class BoxDriver {

	public int i = 0;
	public int j = 0;
	public Box[][] box2DArr;
	public static int[][] islandsMap;   
   
	public static void main(String[] args) {         
      box2DArr = new Box[100][100]; 
	
		islandsMap = new int[][] {                                                      
			{0,0,0,1,0,0,0},                                                    
			{0,0,0,1,0,0,0},                                                    
			{0,0,0,1,0,0,0},                                                    
			{1,1,1,1,1,1,1},                                                    
			{0,0,0,1,0,0,0},                                                    
			{0,0,0,1,0,0,0},                                                    
			{0,0,0,1,0,0,0}                                                     
		};                                                                     
                                                                          
   }
	
   public void islandCounter(int[][] islandsMap, int n, int m) {
	   for(n = 0 ; n <= 6; n++) {
		   for(m = 0; m <= 6; m++) {
		     recursiveSrc(n,m);
			  i = i + 1;  
         }                                                                   
      }                                                                      
      //  System.out.println("There are  " + cfcfb.length() + " islands!");    
   }                                                                         
                                                                          
	public void  recursiveSrc(int n, int m) {                             
																									  
		if((islandsMap[n][m] == 1) && !(contains(n, m, i, box2DArr))) {
	      addBox(n,m, box2DArr);													  
			if(n != 0) {                                                        
				recursiveSrc((n - 1), m);                                    
			}                                                                   
																									  
			if(n != 6) {                                                        
				recursiveSrc(n + 1, m);                                      
			}                                                                   
																									  
			if(m != 0) {                                                        
				recursiveSrc(n, m - 1);                                      
			}                                                                   
																									  
			if(m != 6) {                                                        
				recursiveSrc(n, m + 1);                                      
			}                                                                   
																									  
			if((n != 0) && (m != 0)) {                                          
				recursiveSrc(n - 1, m - 1);                                  
			}                                                                   
																									  
			if((n != 0) && (m != 6)) {                                          
				recursiveSrc(n - 1, m + 1);                                  
			}                                                                   
																									  
			if(( n != 6 ) && (m != 0)) {                                        
				recursiveSrc(n + 1, m - 1);                                  
			}                                                                   
																									  
			if((n != 6) && (m != 6)) {                                          
				recursiveSrc(n + 1, m + 1);                                  
			}                                                                   
		/* System.out.println(" [" + n + "][" + m + "]"                        
			+ " islandN = " + (islandN - 1) );                                  
		System.out.println("There are  " + (islandN - 2) + " islands!"); */    
		}                                                                      
	}                                                                         
}
