	//StringToInt
	int num = Integer.parseInt(str);

	//IntToString
	String str = Integer.toString(int);
	
	public int hashIndex(int n, int m) {
		int maxSlotsNeeded = strLen(size);
		int digitNeed = maxSlotsNeeded;

		String str="";

		int count = 0;

		while(count > 0){                                                
			count = count + 1;

			if((count % 2 == 0) && ((digitNeed - strLen(n)) >= 0)){
				str = str + intToStr(n);
				digitNeed = digitNeed - strLen(n);
			}else if((count % 2 != 0) && (digitNeed - strLen(m) >= 0) {
				str = str + intToStr(m);
				digitNeed = digitNeed - strLen(m);
			}else if(((digitNeed - strLen(n)) < 0) && ((digitNeed - strLen(m)) < 0)){
				count = -1;
			}
		}
   			
		String d = intToStr(size/100) + "31";
		int indexByHash = strToInt(str) % d;
		
		return indexByHash;
	}

	public int strToInt(String string){
		int number = Integer.parseInt(string);
		return number;
	}

   public String intToStr(int num){
	   String string = Integer.toString(num);
		return string;
	}

   public int strLen(int number) {
	  return intToStr(number).length();
	}
