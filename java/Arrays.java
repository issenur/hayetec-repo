class Arrays
{
  public static int[][] a; 
  public static void main(String[] args)
  {
   
	 a = new int[2][10];
   
	 System.out.println("");
    System.out.println("");
	 
	 for(int i = 0; i <= 1; i++)
    {
	   for (int j=0; j <= 9; j++)
		{
		  a[i][j] = i+j*10;
		  System.out.print(a[i][j] + " ");

		}                                                
		System.out.println("");
	 }
    
	
    System.out.println("");
    System.out.println("");

	 sevenTeen(1,8);

	 for(int i = 0; i <= 1; i++)
    {
	   for (int j=0; j <= 9; j++){
		   System.out.print(a[i][j] + " ");
		}                                                
		System.out.println("");
	 }
	 
	 System.out.println("");
    System.out.println("");
  } 
    
  public static  void  sevenTeen(int n, int m)
  {
	a[n][m] = 1738;
  }
}
