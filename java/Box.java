public class Location {

   private int row;
   private int col;
	private int numI = 0;
	private int numJ = 0;
	private int i = 0;
	private int j = 0;

	public Location() {}
	
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
   
	public void addLocArray(Location[][] doubLocArray) {
		i = i + 1;
		j = 0;
	}

	public void addLoc(int n, int m, Location[][] doubLocArray) {
	  doubLocArray[i-1][j] = new Location(n,m);
	  j = j + 1;
	}
    	
	public boolean isNotNewLocation(int n, int m, Location[][] doubLocArray){	
	   if(numI < i){
	      numI = i;
		}
		if(numJ < j){
		   numJ = j;
		}
		
		for(int l = 0 ; l < numI; l++) {
			for(int k = 0; k < numJ; k++) {
				if(!(doubLocArray[l][k] == null) && (equalsLocation(n, m, doubLocArray[l][k]))) {
             return true;
				}
			}
		}
		return false;
	}
	
	public  boolean equalsLocation(int n, int m, Location b) {
		return((n == b.row) && (m == b.col ) ); 	
	}
	
	public String onesIslandCount(Location[][] doubLocArray, int[][] oneZeroMap, int n, int m) {
	   for(n = 0 ; n <= 6; n++) {
			for(m = 0; m <= 6; m++) {
				if((oneZeroMap[n][m] == 1) && !(isNotNewLocation(n, m, doubLocArray))) {
					addLocArray(doubLocArray);
					doubLocArray[i][j] = new Location(-1,-1);
					recursiveSrc(doubLocArray, oneZeroMap, n, m);
				}
			}
		}
		return("There are " + i + " islands!");
	}
	
	public void recursiveSrc(Location[][] doubLocArray, int[][] oneZeroMap, int n, int m) {
	 
		try{
			if((oneZeroMap[n][m] == 1) && !(isNotNewLocation(n, m, doubLocArray))){ 
				addLoc(n, m, doubLocArray);
				recursiveSrc(doubLocArray, oneZeroMap, n - 1, m - 1);
				recursiveSrc(doubLocArray, oneZeroMap, n - 1, m);
				recursiveSrc(doubLocArray, oneZeroMap, n - 1, m + 1);
				recursiveSrc(doubLocArray, oneZeroMap, n , m + 1);
				recursiveSrc(doubLocArray, oneZeroMap, n + 1, m + 1);
				recursiveSrc(doubLocArray, oneZeroMap, n + 1, m);
				recursiveSrc(doubLocArray, oneZeroMap, n + 1, m - 1);
				recursiveSrc(doubLocArray, oneZeroMap, n , m - 1);
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
		Location[][] locDoubleArray = new Location[size][size];
	   System.out.println(location.onesIslandCount(locDoubleArray, onesZerosGrid, 0, 0));
	}
}
