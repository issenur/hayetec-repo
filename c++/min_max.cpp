/*Find the min and max and average of 15 numbers that a user will input.
**The numbers range from 0 to 100. 
**We will do it now for practice and again later when we learn arrays and 
**functions. So you do not have to keep all fifteen numbers stored in memory.
**HINT: you will need to use scanf for input text.
*/

#include <iostream>	

int main() 
{
   int number = 0;
   int max = 0;
   int min = 0;
   int sum = 0;
   int average = 0;


   std::cout << "Enter number: "   << "\n";	
   std::cin >> number;
   min = number;
	
   for (int i=0; i < 14; i++)
   {
      std::cout << "Enter number: "  << "\n";
      std::cin >> number;  
   
      if (number >= max) 
        max = number;	
      else
        max = max;
     
      if (number <= min) 
        min = number;	
      else
        min = min;
        
      sum = sum + number;
   }	
   average = sum/15;
   std::cout << "Max = " << max << "\n";
   std::cout << "Min = " << min << "\n";		
   std::cout << "Average = "  << average  <<"\n";	  
   return 0;
}	

