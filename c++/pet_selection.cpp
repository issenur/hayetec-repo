/*Goal: practice if-else statements in C++
**Write a program to select the best pet. 
*/

#include<iostream>

int main()
{
   char skin, location;
   std::cout<<"Would you like an animal with fur(f), feathers(t), or scales(s)?";
   std::cin>>skin;
   std::cout<<skin<<"\n";
   
   //Use if-else statements to control program flow
   
   std::cout<<"Get a dog"<<"\n";
   std::cout<<"Get a bird"<<"\n";
   
   std::cout<<"Would you like an animal that lives in water(w), land(l), or both(b)?";
   std::cin>>location;
   std::cout<<location<<"\n";
   
   //Use if-else statements to control program flow
   
   std::cout<<"Get a fish"<<"\n";  
   std::cout<<"Get a gecko"<<"\n";
   std::cout<<"Get a frog"<<"\n";
   
   return 0;
}
