public class Location {

   private int row;
   private int col;
	private int size;
	private int islandNum = 0;


	public Location() {}
	
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
   
	public void sizeSet(int size) {
	   this.size = size;
	}
	
	public void islandCountUp() {
		islandNum = islandNum + 1;
	}
   
	public int hashIndex(int n, int m) {
	   System.out.println("( " + n + "," + m + " )");
		int maxSlotsNeeded = strLen(size);
		int digitNeed = maxSlotsNeeded;


		int count = 0;
	   String str = "";

		while(count >= 0 ){                                                
			count = count + 1;
	      System.out.println("digitNeed =" + digitNeed);
         System.out.println( "str[" + str + "]" );
			if((count % 2 == 0) && ((digitNeed - strLen(n)) >= 0)) {
				str = str + intToStr(n);
				System.out.println("str = str + n ");
				digitNeed = digitNeed - strLen(n);
			}else if((count % 2 != 0) && (digitNeed - strLen(m) >= 0)) {
				str = str + intToStr(m);
				System.out.println("str = str + m ");
			digitNeed = digitNeed - strLen(m);
			}else if(((digitNeed - strLen(n))<0)&&((digitNeed - strLen(m))<0)){
				count = -1;
				System.out.println("exiting");
			}
		}
   			
		String d = intToStr(size/100) + "31";
		int indexByHash = strToInt(str) % strToInt(d);
		
		System.out.println ("MADE INDEX [" + hashIndex(n,m) + "]");
		return indexByHash;
	}
  

	public int strToInt(String string){
		int number = Integer.parseInt(string);
		return number;
	}

   public String intToStr(int num){
	   String string = Integer.toString(num);
		return string;
	}

   public int strLen(int number) {
	  return intToStr(number).length();
	}
	
	public void addLoc(int n, int m, Location[][] locArray) {
	   int index = 1;
	   
		if((locArray[hashIndex(n,m)][index] == null) && (index < 6)) {
	      locArray[hashIndex(n,m)][index] = new Location(n,m);
			System.out.println("ADDED (" + n + "," + m + ") TO INDEX [" + hashIndex(n,m) + "][" + index + "]");
		}
		else {
		   index = index + 1;
		} 
	  
	}
	
	public boolean isNotNewLocation(int n, int m, Location[][] locArray){	
	   for(int i = 1; i <= 5; i++) {
        System.out.println("B");		 
		  if(!(locArray[hashIndex(n,m)][i] == null) 
			  && (equalsLocation(n, m, locArray[hashIndex(n,m)][i]))) {
			  System.out.println(" isNotNewLocationTrue" ); 
			  return true;
		   }   
		}
		System.out.println(" isNotNewLocationFalse "); 
		return false;
	}
	
	public  boolean equalsLocation(int n, int m, Location b) {
		return((n == b.row) && (m == b.col )); 	
	}
	
	public String onesIslandCount(Location[][] locArray, 
	  int[][] oneZeroMap, int n, int m) {
	   for(n = 0 ; n <= 6; n++) {
			for(m = 0; m <= 6; m++) {

			   System.out.println("Beg onesIsland(" + n + "," + m + ")");
				if((oneZeroMap[n][m] == 1) 
				  && !(isNotNewLocation(n, m, locArray))) {
				   
			   System.out.println("Inside onesIsland(" + n + "," + m + ")");
					islandCountUp();
					locArray[hashIndex(n,m)][0] = new Location(-1, -1);
					recursiveSrc(locArray, oneZeroMap, n, m);
				}
			}
			System.out.println("End onesIsland");
		}
		return("There are " + islandNum + " islands!");
	}
	
	public void recursiveSrc(Location[][] locArray, int[][] oneZeroMap, 
	   int n, int m) {
      
		try{
			if((oneZeroMap[n][m] == 1) 
			  && !(isNotNewLocation(n, m, locArray))){ 

			   addLoc(n, m, locArray);
				recursiveSrc(locArray, oneZeroMap, n - 1, m - 1);
				recursiveSrc(locArray, oneZeroMap, n - 1, m);
				recursiveSrc(locArray, oneZeroMap, n - 1, m + 1);
				recursiveSrc(locArray, oneZeroMap, n , m + 1);
				recursiveSrc(locArray, oneZeroMap, n + 1, m + 1);
				recursiveSrc(locArray, oneZeroMap, n + 1, m);
				recursiveSrc(locArray, oneZeroMap, n + 1, m - 1);
				recursiveSrc(locArray, oneZeroMap, n , m - 1);
			}
		}  
		catch (ArrayIndexOutOfBoundsException exception) {
		   //Do Nothing
		}          
	} 

}

class LocationDriver {
	
   public static void main(String[] args) {         
		Location location = new Location();

		int[][] onesZerosGrid = new int[][] {
			{1,0,1,1,0,1,0},
			{1,0,1,1,0,0,0},
			{0,1,0,0,1,0,0},
			{0,0,0,0,0,1,0},
			{0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0},
			{0,0,0,1,0,0,0}
		};

	   int size = (onesZerosGrid.length)*(onesZerosGrid[0].length);
		location.sizeSet(size);
		Location[][] locDoubleArray = new Location[size][6];
	   System.out.println(location.onesIslandCount(locDoubleArray, 
	      onesZerosGrid, 0, 0));
	}
}
