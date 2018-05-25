class LinkedStack<Base>
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

class LinkedStackDriver
{
  public static void main(String [] args)
  {
    LinkedStack<String> s = new LinkedStack<String>();

    s.push("W");
    System.out.println(s.peek());
    s.push("A");
    System.out.println(s.peek());
    s.push("R");
    System.out.println(s.peek());
    s.push("Y");
    System.out.println(s.peek());
    s.push("A");
    System.out.println(s.peek());


    System.out.println("");
    System.out.println("FILLED");
    System.out.println("READYING TO EMPTY");
    System.out.println("INITIATING EMPTYING PROCESS...");
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
