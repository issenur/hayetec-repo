package Assignment4Three;

public class practice {

	static char[][] b = new char[20][17];
	
	public static void main(String[] args) 
	{
		int m = 8;
		for(int i = 0; i < m; i ++)
		{
			b[0][i]  = '*';
		}
		
		int n =1;
		int j =9;
		for(int i = 9; j < n; i = j--)
		{
			b[0][j]  = ' ';
		}
		
		for(int i = 0; i < m; i ++)
		{
			b[0][i]  = '*';
		}
		
	}

}
