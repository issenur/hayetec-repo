#include "main.hpp"

     int main ()
      {
        float ftemp = 0;
        float ctemp = 0;

        cout << "Enter a Farenheit temperature: ";
        cin>>ftemp;

        ctemp = (ftemp - 32.0) / (9.0/5.0);
        cout << "\n"<<ftemp <<" in farenheit = " <<ctemp<<" in Celcius\n";
        return 0;
      }
The header file:

main.hpp

/*The header file*/

#include<iostream>

using namespace std;
