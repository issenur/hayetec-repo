#include <iostream>
#include <stdio.h>

int main()
{
	 int searchKey = 0;
	 int searchArray[10] = {324,4567,6789,5421345,7,65,8965,12,342,485};
	 int location = 0;

	 while(1)
	 {
		  std::cout<<"Enter an integer ('-1' to quit): ";
		  scanf("%d", &searchKey);
		  std::cout<< searchKey<<"\n";
		  if(searchKey == -1)
		  {
				break;
		  }
		  for(int i = 0; i < 10; i++)
		  {
				if(searchKey == searchArray[i])
				{
					 location = i;
					 break;
				}
				location = -1;
		  }
		  if(location != -1)
		  {
				std::cout<<searchKey<<" is at location "<<location<<" in the array.\n";
		  }
		  else
		  {
				std::cout<<searchKey<<" is not in the array.\n";
		  }
	}
	 return 0;
}  
