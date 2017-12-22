class Hash
{
  private int hash(String s)
  {
    int total = 0;
    for (int index = 0; index < s.length(); index = index + 1)
    {
      total = total + s.charAt(index);
    }
    return;
  }
}
