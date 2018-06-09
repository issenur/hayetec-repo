class Oak<Rndm>
{
  private class Node
  {
    private Rndm name;
    private Node next;

    private Node(Rndm name, Node next)
    {
      this.name = name;
      this.next = next;
    }
  }

  private Node top;

  public Oak()
  {
    top = null;
  }

  public boolean isEmpty()
  {
    return top == null;
  }

  public Rndm peek()
  {
    if(isEmpty())
    {
      throw new IllegalStateException();
    }
    else
    {
      return (top.name);
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
      top = top.next;
    }
  }

  public void push(Rndm name)
  {
      top = new Node(name, top);
  }
}

class OakDriver
{
  public static void main(String [] args)
  {
    Oak<String> oak = new Oak<String>();

    oak.push("Janikowksi");
    System.out.println(oak.peek());
    oak.push("Mack");
    System.out.println(oak.peek());
    oak.push("Penn");
    System.out.println(oak.peek());
    oak.push("Carr");
    System.out.println(oak.peek());
    oak.push("King");
    System.out.println(oak.peek());
    oak.push("Jamize");
    System.out.println(oak.peek());
    oak.push("Autry");
    System.out.println(oak.peek());
    oak.push("Crabtree");
    System.out.println(oak.peek());
    oak.push("Cooper");
    System.out.println(oak.peek());
    oak.push("Nelson");
    System.out.println(oak.peek());
    oak.push("Smith");
    System.out.println(oak.peek());
    oak.push("Hudson");
    System.out.println(oak.peek());
    oak.push("Seth");
    System.out.println(oak.peek());
    oak.push("Irvin");
    System.out.println(oak.peek());
    oak.push("Jackson");
    System.out.println(oak.peek());
    oak.push("Carrie");
    System.out.println(oak.peek());
    oak.push("Joseph");
    System.out.println(oak.peek());
    oak.push("Conley");
    System.out.println(oak.peek());
    oak.push("Melifonwu");
    System.out.println(oak.peek());

    System.out.println("");
    System.out.println("FILLED");
    System.out.println("READYING TO EMPTY");
    System.out.println("INITIATING EMPTYING PROCESS...");
    System.out.println("");
    System.out.println("");

    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());
    oak.pop();
    System.out.println(oak.peek());


  }

}
