//A Hello World program
#include <iostream>
using namespace std;

void dumb ()
{
 char N = 'A';
 cout << N ;
}


int main ()
{
  int N;
  cin >> N;
  for(int i = 0; N > i ; i++)
  {
    cout << "Hello World!\n";
  }
  dumb();
}

