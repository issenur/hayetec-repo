class LinkedQueue<Base>
{
  private class Node
  {
    private Base value;
    private Node next;

    private Node(Base value, Node next)
    {

    }
  }
  private Node front;
  private Node rear;


  public void enqueue(Base base)
  {
    if (isEmpty())
    {
      front = rear = new Node(base, null);
    }
    else //special case 1
    {
      rear.next = new Node(base, null);
      rear = rear.next;
    }
  }

  public Base dequeue()
  {
    if(isEmpty())
    {
      throw new IllegalStateException("Empty Queue");
    }
    else
    {
      Base temp = front.value;
      front = front.next;
      if (front == null)//special case 2
      {
        rear = null;
      }
      return temp;
    }

  }

  public boolean isEmpty()
  {
    return((front == null)&&(rear == null));
  }
}

class LinkedQueueDriver
{
  public static void main(String [] args)
  {
    LinkedQueue<String> s = new LinkedQueue<String>();

    s.enqueue("W");
    s.enqueue("A");
    s.enqueue("R");
    s.enqueue("Y");
    s.enqueue("A");

    System.out.println("");
    System.out.println("            FILLED              ");
    System.out.println("       READYING TO EMPTY        ");
    System.out.println("  INITIATING EMPTYING PROCESS...");
    System.out.println("");

    System.out.println(s.dequeue());
    System.out.println(s.dequeue());
    System.out.println(s.dequeue());
    System.out.println(s.dequeue());
    System.out.println(s.dequeue());

  }
}
