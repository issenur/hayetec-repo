
#include <string>

int main ()
{
   std::string name;                                                      
      std::cout << "Please, enter your full name: ";          
  		   std::getline (std::cin,name);                           

  std::string address;                                     
  std::cout << "Please, enter your address: ";          
  std::getline (std::cin,address);                           
                              
  
  std::string pNumber;                                                            
  std::cout << "Please, enter your phone number: ";          
  std::getline (std::cin,pNumber);                           
                                                             
  std::string name2;                                                      
  std::cout << "Please, enter your full name: ";          
  std::getline (std::cin,name2);                           

  std::string address2;                                     
  std::cout << "Please, enter your address: ";          
  std::getline (std::cin,address2);                           
                              
  
  std::string pNumber2;                                                            
  std::cout << "Please, enter your phone number: ";          
  std::getline (std::cin,pNumber2);                           
 
  std::cout << name << "\n"; 
  std::cout <<"\t\t"<< address << "\n";     
  std::cout <<"\t\t"<< pNumber << "\n";  
 
  std::cout << name2 << "\n"; 
  std::cout <<"\t\t"<< address2 << "\n";     
  std::cout <<"\t\t"<< pNumber2 << "\n";      
  
  return 0;

}                                                      
                                                        
              

