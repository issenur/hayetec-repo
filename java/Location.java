public class Location {

   private int row;
   private int col;
	private int numI = 0;
	private int numJ = 0;
	private int i = 0;
	private int j = 0;
	private int islandNum = 0;

	public Location() {}
	
	public Location(int row, int col, int islandNum) {
		this.row = row;
		this.col = col;
		this.islandNum;
	}
   
	public void islandCountUp() {
		islandNum = islandNum + 1;
	}

	public int hashIndex(int n, int m) {
		int maxSlotsNeeded = strLen(size);
		int digitNeed = maxSlotsNeeded;

		String str="";

		int count = 0;

		while(count > 0){                                                
			count = count + 1;

			if((count % 2 == 0) 
			   && ((digitNeed - strLen(n)) >= 0)){
				str = str + intToStr(n);
				digitNeed = digitNeed - strLen(n);
			}else if((count % 2 != 0) 
			   && (digitNeed - strLen(m) >= 0) {
				str = str + intToStr(m);
				digitNeed = digitNeed - strLen(m);
			}else if(((digitNeed - strLen(n)) < 0) 
			   && ((digitNeed - strLen(m)) < 0)){
				count = -1;
			}
		}
   			
		String d = intToStr(size/100) + "31";
		int indexByHash = strToInt(str) % d;
		
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
	
	public void addLoc(int n, int m, Location[] locArray) {
	  locArray[hashIndex(n,m)] = new Location(n,m,islandNum);
	}
	
	public boolean isNotNewLocation(int n, int m, 
	  Location[] locArray){	
		
			if(!(locArray[hashIndex(n,m)] == null) 
			  && (equalsLocation(n, m, locArray[(hashIndex(n,m)]))) {
			return true;
		}
		return false;
	}
	
	public  boolean equalsLocation(int n, int m, Location b) {
		return((n == b.row) && (m == b.col ) ); 	
	}
	
	public String onesIslandCount(Location[] locArray, 
	  int[][] oneZeroMap, int n, int m) {
	   for(n = 0 ; n <= 6; n++) {
			for(m = 0; m <= 6; m++) {
				if((oneZeroMap[n][m] == 1) 
				  && !(isNotNewLocation(n, m, locArray))) {
					islandCountUp();
					locArray[hashIndex(n,m)] = new Location(-1, -1, islandNum);
					recursiveSrc(locArray, oneZeroMap, n, m);
				}
			}
		}
		return("There are " + i + " islands!");
	}
	
	public void recursiveSrc(Location[] locArray, 
	  int[][] oneZeroMap, int n, int m) {
	 
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
		Location[] locDoubleArray = new Location[size];
	   System.out.println(location.onesIslandCount(locDoubleArray, 
			onesZerosGrid, 0, 0));
	}
}
