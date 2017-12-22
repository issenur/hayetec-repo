class LinearSearch
{
  static int[] element = new int[10];
	public static void main(String[] args)
	{
		for (int i = 0; i < element.length; i++)
		{
			element[i] = i;
		}

		System.out.println(linearSearch(0, element));
	}

  public static int linearSearch(int key, int[] elements)
  {
    for(int index = 0; index < elements.length; index = index + 1)
    {
      if(key == elements[index])
      {
        return index;
      }
    }
    return -1;
  }
}
