public class Bs<STCK> {
	public STCK[] bs;
	private int countBs;
	
	public Bs() {
		bs =(STCK[]) new Object[100];
		countBs = 0;
	}

	public void pushBs(STCK stck){
		bs[countBs] = stck;
		countBs = countBs + 1;
	}
   public int x;
	public int y;
	public Bx peekBs(int k) {
     x = bs[k].Bx.getM();
	  y = bs[k].Bx.getN();
	  return (new Bx(x,y));
	}
	public boolean isMember (int n, int m, STCK[] bs){
		for(int i = 0; i < 100; i++) {
		 if (Bx.equalsBx(n, m, peekBs(i))) {
				return true;
			}
		}	
		return false;
	}
}  
