 public class Bx{

   public int en;
	public int em;
	public Bx(int en, int em) {
		this.en = en;
		this.em = em;
	}
   
   public static boolean equalsBx(int n, int m, STCK bx2) {
      return (n == getN(bx2)) && (m == getM(bx2));
	}
	public int getM(Bx bx){
		return em;
	}

	public int getN(Bx bx){
		return en;
	}

}
