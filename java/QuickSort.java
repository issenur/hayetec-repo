public void quickSort(int[] array, int leftEnd, int rightEnd)
{
  int left = leftEnd;
  int right = rightEnd;
  int pivot = array[(leftEnd + rightEnd)/2];
//*************************************
	do{
    while(array[left] < pivot){
      left = left + 1;
    }
    while(array[right] > pivot){
      right = right - 1;
    }

    if(left >= right){
      swap(array, left, right);
      left = left + 1;
      right = right -1;
    }
  }
  while (left <= right);
//^^^part of do while ************************

  if (leftEnd < right)
  {
    quickSort(array,leftEnd, right);
  }
  if (left  < rightEnd)
  {
    quickSort(array, left, rightEnd)
  }
}
///end of sort algorithm ***********************
private void swap(int[] array, int left, int right)
{
  int temp = array[left];
  array[left] = array[right];
  array[right] = temp;
}
