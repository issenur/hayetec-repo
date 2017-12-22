public int compareTo(String left, String right)
{
  int index = 0;
  while(index < left.length() && index < right.length())
  {
    if(left.charAt(index)== right.charAt(index))
    {
      index = index + 1;
    }
    else
    {
      return(left.charAt(index) - right.charAt(index));
    }
  }
  return(left.length() - right.length());
}
