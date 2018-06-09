/*For this program print for each variable
**print the value of the variable, 
**then print the address where it is stored. 
*/
#include<iostream>
#include<string>

int main()
{
    int givenInt = 69;
    float givenFloat = 4.0;
    double givenDouble =  0.44;
    std::string givenString = "aaa";
    char givenChar = d;
	 std::cout<< givenInt << "location: " << &givenInt  << "\n";	
	 std::cout<< givenFloat << "location: " << &givenFloat  << "\n";	
	 std::cout<< givenDouble << "location: " << &givenDouble  << "\n";	
	 std::cout<< givenString << "location: " << &givenString  << "\n";	
	 std::cout<< givenChar << "location: " << &givenChar  << "\n";	
    return 0;
}

