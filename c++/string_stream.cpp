/*Goal: practice getting string inputs and 
 **converting them to numeric variables using
 **stringstream.
 **
 **Prompt the user for the length of a room. 
 **Then prompt for the width of the room.
 **Print out the area of the room. 
 */

#include <iostream>         
#include <string>           
#include <sstream>                            

int main ()                 
{ 
  int lengthNumeric = 0;
  int widthNumeric = 0;
  int area = 0;                           
  std::string lengthString; 
  std::string widthString;

  std::cout << "Enter the length of room: ";
  std::getline(std::cin, lengthString);
  std::stringstream(lengthString) >> lengthNumeric;
  
  std::cout << "Enter the width of room: ";
  std::getline(std::cin, widthString);
  std::stringstream(widthString) >> widthNumeric;
  
  area = lengthNumeric * widthNumeric;
  std::cout << "The area of the room is: " <<  area  << "\n";
 return 0;
}

