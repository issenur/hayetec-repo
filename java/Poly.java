import java.lang.*;

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
  private Term head;

  public Poly()
  {
    head = new Term(1,1,null);
  }

  public Poly term(int coef, int expo)
  {
    if(expo < 0)
    {
      throw new IllegalArgumentException();
    }
    else
    {
      Term left = head;
      Term right = head.next;

      if (head.next == null)
      {
        head.next = new Term(coef,expo, right);
      }

      while(right != null)
      {
        if(expo > right.expo)
        {
          left.next = new Term(coef, expo, right);
          return this;
        }
        else if(expo < right.expo)
        {
          left = right;
          right = right.next;
        }
        else if(expo == right.expo)
        {
          throw new IllegalArgumentException();
        }
        else if(right == null)
        {
          left = new Term(coef, expo, right);
        }
      }

      if(right == null)
      {
        left.next = new Term(coef, expo, right);
      }
    }
    return this;
  }

  public Poly plus(Poly that)
  {
    Poly pNew = new Poly ();
    Term temp = head.next;

    while(temp!= null)
    {
      pNew.term(temp.coef, temp.expo);
      temp = temp.next;
    }

    temp = that.head.next;
    while(temp != null)
    {
      pNew.add(temp.coef, temp.expo);
      temp = temp.next;
    }
    return pNew;
  }

  private void add(int coef, int expo)
  {
    if(expo < 0)
    {
      throw new IllegalArgumentException();
    }
    else
    {
      Term left = head;
      Term right = head.next;
      if (head.next == null)
      {
        head.next = new Term(coef,expo, right);
      }

      while(right != null)
      {
        if(expo > right.expo)
        {
          left.next = new Term(coef, expo, right);
          return;
        }
        else if(expo < right.expo)
        {
          left = right;
          right = right.next;
        }
        else if(expo == right.expo)
        {
          if((coef + right.coef)!=0)
          {
            right.coef = coef + right.coef;
            return ;
          }
          else if ((coef + right.coef)==0)
          {
            left.next = right.next;
            right = left.next;
            return;
          }
        }
      }
    }
  }

  public Poly minus()
  {
    Poly pNew = new Poly ();
    Term left = head;
    Term right = head.next;

    while(right != null)
    {
      pNew.term(-(right.coef), right.expo);
      left = right;
      right = right.next;
    }
    return pNew;
  }

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    Term temp = head.next;

    if(temp == null)
    {
      return "0";
    }
    else
    {
      builder.append(temp.coef);
      builder.append("x");
      builder.append(temp.expo);

      temp = temp.next;
      while(temp != null)
      {
        if(temp.coef < 0)
        {
          builder.append(" - ");
          builder.append(Math.abs(temp.coef));
          builder.append("x");
          builder.append(temp.expo);
          temp = temp.next;
        }
        else
        {
          builder.append(" + ");
          builder.append(Math.abs(temp.coef));
          builder.append("x");
          builder.append(temp.expo);
          temp = temp.next;
        }
      }
    }
    return builder.toString();
  }
}

class PollyEsther
{
  public static void main(String[] args)
  {
    Poly p0 = new Poly();
    Poly p1 = new Poly().term(1, 3).term(1, 1).term(1, 2);
    Poly p2 = new Poly().term(2, 1).term(3, 2);
    Poly p3 = p2.minus();

    System.out.println(p0);           //  0
    System.out.println(p1);           //  1x3 + 1x2 + 1x1
    System.out.println(p2);           //  3x2 + 2x1
    System.out.println(p3);           //  −3x2 − 2x1

    System.out.println(p1.plus(p2));  //  1x3 + 4x2 + 3x1
    System.out.println(p1.plus(p3));  //  1x3 − 2x2 − 1x1
  }
}
