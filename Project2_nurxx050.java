class Poly
{
  private class Term
  {
    private int coef;
    private int expo;
    private Term next;

    public Term(int coef, int expo, Term next)
    {
      this.coef = coef;
      this.expo = expo;
      this.next = next;
    }
  }
  Term head;

  public Poly()
  {
    head = new Term(5,5,null);
  }
  /* */
  public Poly term(int coef, int expo)
  {

    /* The new Term’s coefficient is coef
     and its exponent is expo.*/

    if(expo < 0)
    {
      /*If expo is negative, then throw an
      IllegalArgumentException.*/
      throw new IllegalArgumentException();
    }
    else
    {
      /*Otherwise, visit each Term in this. then add the new
      Term immediately before that Term*/

      Term left = head;
      Term right = head.next;
      while(right != null)
      {
        /*If you visit a Term whose expo slot
        is less than the parameter expo*/
        if(expo > right.expo)
        {
          /*then add the new
          Term immediately before that Term,*/
          left.next = new Term(coef, expo, right);
          /*and stop visiting Term’s.*/
          return this;
        }
        /*If you visit a Term whose expo slot
        is greater than the parameter expo*/
        else if(expo < right.expo)
        {
          /*Then skip that Term and go on
          to the next one.*/
          left = right;
          right = right.next;
        }
        /*If you visit a Term whose expo slot
        equals the parameter expo*/
        else if(expo == right.expo)
        {
          /*then throw an IllegalArgumentException.*/
          throw new IllegalArgumentException();
        }
        else if(right.next == null)
        {
          /*If you’ve visited all the Term’s, but didn’t add a
          new Term, then add the new Term at the end of the
          list. */
          left = new Term(coef, expo, right);
        }
      /*Finally, return this so that more Term’s
      can be added later*/

      }
      if(right == null)
      {
        left.next = new Term(coef, expo, right);
      }
    }
    return this;
  }

  /*public Poly plus(Poly that)
  {

  }

  private void add(int coef, int expo)
  {

  }

  public Poly minus()
  {

  }*/

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    Term temp = head.next;

    while(temp != null)
    {
      builder.append(temp.coef);
      builder.append("x");
      builder.append(temp.expo);
      if(temp.next!= null)
      {
        builder.append(" + ");
      }
      temp = temp.next;
    }
    return builder.toString();
  }
}

class PollyEsther
{
  public static void main(String[] args)
  {
    //Poly p0 = new Poly();
    Poly p1 = new Poly().term(1, 3).term(1, 1).term(1, 2);
    Poly p2 = new Poly().term(2, 0);
    //Poly p3 = p2.minus();

    //System.out.println(p0);           //  0
    //System.out.println(p1);           //  1x3 + 1x2 + 1x1
    System.out.println(p2);           //  3x2 + 2x1
    //System.out.println(p3);           //  −3x2 − 2x1

    //System.out.println(p1.plus(p2));  //  1x3 + 4x2 + 3x1
    //System.out.println(p1.plus(p3));  //  1x3 − 2x2 − 1x1
  }
}
