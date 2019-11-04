public class Solution {
	int[] nums = {2, 7, 11, 15};
	int[] index_array = new int[2];
	int target = 9;
	
	
	public Solution(){
	}

	public int[] twoSum(int[] nums, int target){
		for(int i = 0; i <= nums.length - 1; i++){
			for(int j = 0; j <= nums.length - 1; j++){
				if(nums[i] + nums[j] == target){
					index_array[0] = i;
					index_array[1] = j;
					return index_array; 
				}
			}
		}
		 int[] g = new int[] {-1,-1};
		 return g;
	}
	
}
class SolutionDriver{
	public static void main(String[] args){
		Solution solution = new Solution();
		solution.twoSum(solution.nums, solution.target);
	} 
}
