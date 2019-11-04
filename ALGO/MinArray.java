public class MinArray{
	int[][] vals = new int[][]{
	{6,4,-9,8,-8},
	{19,3,81,4,2},
	};	
	public MinArray() {}
	public int getMinValue(int[] numbers){
		int minValue = numbers[0];
  		for(int i=1;i<numbers.length;i++){
    		if(numbers[i] < minValue){
	  			minValue = numbers[i];
			}
  		}
  		return minValue;
	}
}
class MinArrayDriver {
	public static void main(String[] args){
		MinArray minny = new MinArray();
		System.out.println(minny.getMinValue(minny.vals[0]));	
	}
}
