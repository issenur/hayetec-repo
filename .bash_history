class Poly
{
  private class Term
  {
    private int coef;
    private int expo;
    private Term next;
  }


  public Poly()
  {

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
        if(expo > slahblahNode)
        {
          /*then add the new
          Term immediately before that Term,*/
          left.next = new Term(coef, expo, right);
          /*and stop visiting Term’s.*/
          break;
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
        else if(right.expo == right.expo)
        {
          /*then throw an IllegalArgumentException.*/
          throw new IllegalArgumentException();
        }
        else if(right.next = null)
        {
          /*If you’ve visited all the Term’s, but didn’t add a
          new Term, then add the new Term at the end of the
          list. */
          left = new Term(coef, expo, right);
        }
      /*Finally, return this so that more Term’s
      can be added later*/
      return this;
      }
  }

  public Poly plus(Poly that)
  {

  }

  private void add(int coef, int expo)
  {

  }

  public Poly minus()
  {

  }

  public String toString()
  {

  }
}
