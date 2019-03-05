public void quickSort(int[] arr, int low, int high){
	int pivot = arr[(low + high)/2];
	
	//maybNOtNecessary	
	if(arr == null || arr.length == 0){
		return;
	}
	
	//maybNOtNecessary	
	if (low >= high){
		return;
	}
	
	int i = low;
	int j = high;
	
	do{
		while(arr[i] < pivot){
			i = i + 1;
		}
		while(arr[j] > pivot){
			j = j - 1;
		}

		if(i >= j){
			swap(arr, i, j);
			i = i + 1;
			j = j -1;
		}
	}while (i <= j);

	if (low < j){
		quickSort(arr,low, j);
	}
	if (i  < high){
		quickSort(arr, i, high)
	}
}

private void swap(int[] arr, int i, int j){
	int temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp
}
