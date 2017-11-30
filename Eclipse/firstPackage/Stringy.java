package firstPackage;

import java.util.StringTokenizer;


public class Stringy
{
	public static void main(String[] args) 
	{
		   // creating string tokenizer
		   StringTokenizer st = new StringTokenizer("4+2-8-6+3","+-*");
		      
		   // moving to next element
		  
		   while(st.hasMoreTokens())
		   {
			   System.out.println("Next element is : " + st.nextElement());
		   }
		   // checking next to next element
		  
		      
	}
}


