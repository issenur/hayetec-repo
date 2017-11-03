class RunnyStack<Base>
{
  private class Run
  {
    private Base object;
    private Run next;
    private int length;

    private Run(Base object, Run next, int length)
    {
      this.object = object;
      this.next = next;
      this.length = length;
    }

  }

  private Run top;
  private int length;
  private int run;

  public RunnyStack()
  {
    top = null;
    length = 0;
    run = 0;
  }

  public int depth()
  {
    return length;
  }

  public boolean isEmpty()
  {
    return top == null;
  }

  public Base peek()
  {
    if(isEmpty())
    {
      throw new IllegalStateException();
    }
    else
    {
      return top.object;
    }
  }

  public void pop()
  {
    if(isEmpty())
    {
      throw new IllegalStateException();
    }
    else
    {
      if (top.length > 1)
  			{
  				top.length = top.length - 1;
  			}
  			else
  			{
  				top = top.next;

          run = run - 1;
  			}
  			length = length - 1;
    }
  }

  public void push(Base object)
  {
    if ((top != null) && (isEqual(object, top.object)))
    {
      top.length = top.length + 1;//path to
    }
    else
    {
      top = new Run(object, top, 1);//path to add an object
      run = run + 1;
    }
    length = length + 1;
  }

  public int runs()
  {
    return run;
  }

  public boolean isEqual(Base object, Base otherObject)
	{
		if (object == null)
		{
			return (object == otherObject);
		}
		else
		{
			return (object.equals(otherObject));
    }
	}
}
//
//  Tests for CSci 1913 Lab 8
//  James Moen
//  20 Mar 17
//
//  The TRY-CATCH statements catch exceptions thrown by RUNNY STACK's methods,
//  so that the program can continue to run even if a method fails. We still
//  haven't talked about TRY-CATCH'es in the lectures yet.
//
//  Most tests have comments that show what they should print, and how many
//  points they are worth, for a total of 40 points.
//
//  Camembert is a soft French cheese. It may be runny. It can be stacked.
//

class Camembert
{
  public static void main(String [] args)
  {
    RunnyStack<String> s = new RunnyStack<String>();

    System.out.println(s.isEmpty());         //  true       1 point
    System.out.println(s.depth());           //  0          1 point
    System.out.println(s.runs());            //  0          1 point

    try
    {
      s.pop();
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No pop");          //  No pop     1 point
    }

    try
    {
      System.out.println(s.peek());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No peek");         //  No peek    1 point
    }

    s.push("A");
    System.out.println(s.peek());            //  A          1 point
    System.out.println(s.depth());           //  1          1 point
    System.out.println(s.runs());            //  1          1 point
    System.out.println(s.isEmpty());         //  false      1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  2          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  3          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  4          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("C");
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  5          1 point
    System.out.println(s.runs());            //  3          1 point

    s.push("C");
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  6          1 point
    System.out.println(s.runs());            //  3          1 point

    s.pop();
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  5          1 point
    System.out.println(s.runs());            //  3          1 point

    s.pop();
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  4          1 point
    System.out.println(s.runs());            //  2          1 point

    s.pop();
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  3          1 point
    System.out.println(s.runs());            //  2          1 point

    s.pop();
    s.pop();
    System.out.println(s.peek());            //  A          1 point
    System.out.println(s.depth());           //  1          1 point
    System.out.println(s.runs());            //  1          1 point

    s.pop();
    System.out.println(s.isEmpty());         //  true       1 point
    System.out.println(s.depth());           //  0          1 point
    System.out.println(s.runs());            //  0          1 point

    try
    {
      System.out.println(s.peek());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No peek");         //  No peek    1 point
    }
  }
}
