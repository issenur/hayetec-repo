class Deque<Base>
{
  private class Node
  {
    private Base object;
    private Node left;
    private Node right;

    private Node(Base object, Node left, Node right)
    {
      this.object = object;
      this.left = left;
      this.right = right;
    }
  }
  private Node head;

  public Deque()
  {
    head = new Node(null, null, null);
    head.right = head;
    head.left = head;
  }

  public void enqueueFront(Base object)
  {
    Node temp = new Node(object, head, head.right);
    head.right.left = temp;
    head.right = temp;
  }

  public void enqueueRear(Base object)
  {
    Node temp = new Node( object, head.left, head);
    head.left.right = temp;
    head.left = temp;
  }
  public Base dequeueFront()
  {
    Node temp;
    Base objectOut;


    if(isEmpty())
    {
      throw new IllegalStateException();
    }
    else
    {
      temp = head.right;
      objectOut = head.right.object;
      temp.right.left = temp.left;
      temp.left.right = temp.right;
      return objectOut;
    }
  }

  public Base dequeueRear()
  {
    Node temp;
    Base objectOut;

    if(isEmpty())
    {
      throw new IllegalStateException();
    }
    else
    {
      temp = head.left;
      objectOut = head.left.object;
      temp.left.right = temp.right;
      temp.right.left = temp.left;
      return objectOut;
    }
  }
  public boolean isEmpty()
  {
    return((head.left == head)&&(head.right == head));
  }
}

//  OBSERVATION DEQUE. Test the class DEQUE. 40 points total.

class ObservationDeque
{

//  MAIN. Test the DEQUE on various example arguments.

  public static void main(String [] args)
  {
    Deque<String> deque = new Deque<String>();

    System.out.println(deque.isEmpty());       // true                2 points.

    try
    {
      System.out.println(deque.dequeueFront());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No dequeueFront.");  //  No dequeueFront.   2 points.
    }

    try
    {
      System.out.println(deque.dequeueRear());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No dequeueRear.");   //  No dequeueRear.    2 points.
    }

//  Enqueueing to the rear and dequeueing from the rear makes the DEQUE act
//  like a stack.

    deque.enqueueRear("A");
    deque.enqueueRear("B");
    deque.enqueueRear("C");

    System.out.println(deque.isEmpty());       //  false              2 points.

    System.out.println(deque.dequeueRear());   //  C                  2 points.
    System.out.println(deque.dequeueRear());   //  B                  2 points.
    System.out.println(deque.dequeueRear());   //  A                  2 points.

    System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the rear and dequeueing from the front makes the DEQUE act
//  like a queue.

    deque.enqueueRear("A");
    deque.enqueueRear("B");
    deque.enqueueRear("C");

    System.out.println(deque.dequeueFront());  //  A                  2 points.
    System.out.println(deque.dequeueFront());  //  B                  2 points.
    System.out.println(deque.dequeueFront());  //  C                  2 points.

    System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the front makes the DEQUE act
//  like a stack.

    deque.enqueueFront("A");
    deque.enqueueFront("B");
    deque.enqueueFront("C");

    System.out.println(deque.dequeueFront());  //  C                  2 points.
    System.out.println(deque.dequeueFront());  //  B                  2 points.
    System.out.println(deque.dequeueFront());  //  A                  2 points.

    System.out.println(deque.isEmpty());       //  true               2 points.

//  Enqueueing to the front and dequeueing from the rear makes the DEQUE act
//  like a queue.

    deque.enqueueFront("A");
    deque.enqueueFront("B");
    deque.enqueueFront("C");

    System.out.println(deque.dequeueRear());   //  A                  2 points.
    System.out.println(deque.dequeueRear());   //  B                  2 points.
    System.out.println(deque.dequeueRear());   //  C                  2 points.

    System.out.println(deque.isEmpty());       //  true               2 points.
  }
}
