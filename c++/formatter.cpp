#include <iostream>
#include <iomanip>


int main()
{
   int a = 45;
   float b = 45.323;
   double c = 45.5468;
   int aa = a + 9;
   float bb = b + 9;
   double cc = c + 9;
   int aaa = aa + 9;
   float bbb = bb + 9;
   double ccc = cc + 9;

   std::cout<<a<<std::setw(15)<<b<<std::setw(15)<<c<<std::endl;
   std::cout<<aa<<std::setw(15)<<bb<<std::setw(15)<<cc<<std::endl;
   std::cout<<aaa<<std::setw(15)<<bbb<<std::setw(15)<<ccc<<std::endl;
    
   return 0;
}
