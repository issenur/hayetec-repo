class Bx {
 public int i;
 public int j;

 public Bx(int a, int b) {
	  this.a = a;
	  this.b = b;
 }

	public Bx[] arrayBox;
	public Bx[][] arrayOfArrayBoxes;

	public void pushBoxToArray(int i, Bx b) {
	  arrayBox[i] = b;
	  i = i + 1;
	}

	public void pushArrayOfBoxesToArray(int j, int i, Bx[][] bb) {
	  arrayOfArrayBoxes[i][j] = bb;
	  j = j + 1;
	}
}

class BxDriver {

}
