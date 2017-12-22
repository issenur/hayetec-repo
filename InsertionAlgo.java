private void insert(int[] array)
{
  for(int count = 1; count < array.length; count = count + 1)
  {
    int element = array[count];
    int index = count - 1;

    while(index >= 0 && element < array[index])
    {
      array[index + 1] = array[index];
      index = index - 1;
    }
    array[index + 1] = element;
  }
}
