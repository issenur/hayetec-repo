class LinkedStackIterator<Base>
{
  private class Node
  {
    private Base value;
    private Node next;

    private Node(Base value, Node next)
    {
      this.value = value;
      this.next = next;
    }
  }
  private Node top;

  private class StackIterator implements Iterator<Base>
  {
    private Node where;
    private StackIterator()
    {
      where = top;
    }

    public boolean hasNext()
    {
      return where != null;
    }

    public Base next()
    {
      if(where == null)
      {
        throw new IllegalStateException("Iterator Down");
      }
      else
      {
        Base temp = where.value;
        where = where.next;
        return temp;
      }
    }

    public void remove()
    {
      //just making the interface happy
    }
  }
  public StackIterator iterator()
  {
    return new StackIterator();
  }
  public void push(Base base)
  {
    top = new Node(base, top);
  }

  public void pop()
  {
    if (isEmpty())
    {
      throw new IllegalStateException("Empty Stack");
    }
    else
    {
      top = top.next;
    }
  }

  public Base peek()
  {
    if(isEmpty())
    {
      throw new IllegalStateException("");
    }
    else
    {
      return top.value;
    }
  }

  public boolean isEmpty()
  {
    return top == null;
  }
}

interface Iterator<Base>
{
  public boolean hasNext();
  public Base next();
  public void remove();
}

class LinkedStackIteratorDriver
{
  public static void main(String [] args)
  {

    LinkedStackIterator<String> s = new LinkedStackIterator<String>();
    s.push("W");
    s.push("A");
    s.push("R");
    s.push("Y");
    s.push("A");

    System.out.println("");
    System.out.println("Iterator will visit each\n node and print its value");
    System.out.println("");

    Iterator<String> i = s.iterator();
    while(i.hasNext())
    {
      System.out.println(i.next());
    }

    System.out.println("");
    System.out.println("                FILLED                  ");
    System.out.println("           READYING TO EMPTY            ");
    System.out.println("   EMPTYING PROCESS WITH PEEK REVEAL    ");
    System.out.println("");

    System.out.println(s.peek());
    s.pop();
    System.out.println(s.peek());
    s.pop();
    System.out.println(s.peek());
    s.pop();
    System.out.println(s.peek());
    s.pop();
    System.out.println(s.peek());
    s.pop();
  }
}
