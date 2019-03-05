public class PrintfDemo{
	public static void main(String[] args){
		String str = "-2";
		String ev = "I";
		String a = "a";
		String b = "b";
		String aa = "aa";
		String aaa = "aaa";
		String one = "1";
		String two = "9";
		String one11111 = "111111111";
		String za = "za";
		String z = "z";
		String yb = "yb";
		String negOne = "-1";
	//	System.out.printf("%-20s", "Node");
	//	System.out.printf("%-20s", "Start-" + "Time");
	//	System.out.printf("%-20s", "Finish Time");
	//	System.out.println("");
		
		try {
			System.out.println(Integer.class.isInstance(Integer.parseInt(ev))); 
		}catch(NumberFormatException exception){
			System.out.println(false);	
		}
		try {
			System.out.println(Integer.class.isInstance(Integer.parseInt(str))); 
		}catch(NumberFormatException exception){
			System.out.println(false);	
		}
	
	
		System.out.println(isInt(str));
		System.out.println(isInt(ev));
		System.out.println("----------------------------A-----------------");
		//1<a<z<	
		System.out.println(comparer(two,one11111));
		System.out.println(comparer(a,aa));
		System.out.println(comparer(a,z));	
		System.out.println(comparer(a,a));	
		System.out.println(comparer(one,two));
		System.out.println(comparer(negOne,one));

		System.out.println("----------------------------A-----------------");

		System.out.println(comparer(b,a));
		System.out.println(comparer(aa, a));
		System.out.println(comparer(z,a));
		System.out.println(comparer(a,a));	
		System.out.println(comparer(z,one));
		System.out.println(comparer(two,one));
		System.out.println(comparer(one,negOne));
	
		System.out.println("----------------------------C2-----------------");
		System.out.println(one.compareTo(a));
		System.out.println(a.compareTo(aa));
		System.out.println(a.compareTo(z));
		System.out.println(a.compareTo(a));
		System.out.println(one.compareTo(z));
		System.out.println(one.compareTo(two));
		System.out.println(negOne.compareTo(one));
	
		System.out.println("----------------------------C2-----------------");
		System.out.println(b.compareTo(a));
		System.out.println(aa.compareTo(a));
		System.out.println(z.compareTo(a));	
		System.out.println(a.compareTo(a));
		System.out.println(z.compareTo(one));
		System.out.println(two.compareTo(one));	
		System.out.println(one.compareTo(negOne));
		
	}
	
	public static int comparer(String left, String right) {               
		int index = 0;                                             
																   
		while(index < left.length() && index < right.length()) {   
			if(left.charAt(index) == right.charAt(index)) {        
				index = index + 1;                                 
			}else {                                                
				return(left.charAt(index) - right.charAt(index));  
			}                                                      
		}                                                          
		return(left.length() - right.length());                    
	}                                                              
	
	private static boolean isInt(String string) {
		return(string.matches("-?\\d{1,9}"));
	}
}
