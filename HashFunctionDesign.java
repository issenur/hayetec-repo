  private int hash(String s)
  {
    int total = 0;
    for (int index = 0; index < s.length(); index = index + 1)
    {
      total = 31*total + s.charAt(index);
    }
    return(Math.abs(total) % table.length;
  }
