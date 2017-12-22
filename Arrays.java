class Arrays
{
  public static void main(String[] args)
  {
    int[] a = new int[10];
    for(int i = 0; i <= a.length-1; i++)
    {
      a[i] = i*10;
      System.out.println(i + " slot is " + a[i]);
    }
  }
}
