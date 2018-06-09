#include <iostream>	

int main() 
{  
   int num = 0;
   int sum = 0;
   float average = 0;
   int n = 5;
   
   for (int i=0; i < n; i++)
   {
      std::cout << "Enter a number: "  << "\n";	
      std::cin >> num;	
      sum = sum + num;
   }	
   
   std::cout << "the sum is: " << sum  << "\n";	
   average = sum/n;
   std::cout << "the average is: " << average  << "\n";	

   return 0;
}	

