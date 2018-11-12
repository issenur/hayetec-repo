public class Bss<STCK2> {   	                         
   private int countBss;
   public STCK2[] bss;

	public Bss() {
      bss = (STCK2[])new Object[100];
		countBss = 0;
	} 

   public void pushBss(STCK2 stck2) {
	   bss[countBss] = stck2;
	   countBss = countBss + 1; 
   }
 
}



class BssDriver {

   public static int[][] a; 
   private static int l = 10;
	private static int w = 10;
	Bss<Bs> cfcfb = new Bss<Bs>();
	Bs<Bx> cfb = new Bs<Bx>();
   Bx b = new Bx(20,20);
   public static void main(String[] args) {  

		a = new int[][] {
			{0,0,0,1,0,0,0},
			{0,0,0,1,0,0,0},
			{0,0,0,1,0,0,0},
			{1,1,1,1,1,1,1},
			{0,0,0,1,0,0,0},
			{0,0,0,1,0,0,0},
			{0,0,0,1,0,0,0}
	   }; 
      
   }

	public void gridTraverse(int[][] a,int n, int m) {      
		for(n = 0 ; n <= 6; n++) {     
		   for(m = 0; m <= 6; m++) {
		      exploreEightWays(n,m);	
			  cfcfb.pushBss(cfb);  
         } 
		}	
	 //  System.out.println("There are  " + cfcfb.length() + " islands!");
   }
	
	public void  exploreEightWays(int n, int m) {

      if((a[n][m] == 1) && !(cfb.isMember(n,m, cfb))) {
		   Bx b = new Bx(n,m);
		   cfb.pushBs(b);		 			
			
		   if(n != 0) {
				exploreEightWays((n - 1), m);  
			} 
			
			if(n != 6) {  
				exploreEightWays(n + 1, m);
			}	
		  
			if(m != 0) {
				exploreEightWays(n, m - 1);
			}
			
			if(m != 6) {
				exploreEightWays(n, m + 1);
			}
			
			if((n != 0) && (m != 0)) {  
				exploreEightWays(n - 1, m - 1);
			}
									
			if((n != 0) && (m != 6)) {
				exploreEightWays(n - 1, m + 1);	
			}  
		
			if(( n != 6 ) && (m != 0)) {
				exploreEightWays(n + 1, m - 1);
			} 

			if((n != 6) && (m != 6)) {
				exploreEightWays(n + 1, m + 1);
			}
	   /*	System.out.println(" [" + n + "][" + m + "]" 
			+ " islandN = " + (islandN - 1) );	
	   System.out.println("There are  " + (islandN - 2) + " islands!"); */
      }
	}
}
