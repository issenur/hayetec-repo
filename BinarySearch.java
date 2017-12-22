
class BinarySearch
{
	static int[] element = new int[10];
	public static void main(String[] args)
	{
		for (int i = 0; i < element.length; i++)
		{
			element[i] = i;
		}

		System.out.println(binarySearch(0, element));
	}


	public static int binarySearch(int key, int [] element)
	{
		int left = 0;
		int mid;
		int right = element.length-1;
		while(true)
		{
			if(left > right)
			{

				return -1;
			}
			else
			{
				mid = (left + right)/2;
			}

			if(key < mid)
			{
				right = mid - 1;
			}
			else if (key > mid)
			{
				left = mid + 1;
			}
			else
			{
				return mid;
			}
		}
	}
}
