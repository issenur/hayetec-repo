class ArrayQueue<Base>
{
  private int front;
  private int rear;
  private Base[] bases;

  public ArrayQueue(int size)
  {
    if(size >= 1)
    {
      bases = (Base[]) new Object[size];
      front = 0;
      rear = 0;
    }
    else
    {
      throw new IllegalArgumentException("Size has to be at least 1");
    }
  }

  public boolean isEmpty()
  {
    return front == rear;
  }

  public void enqueue(Base base)
  {
    int nextRear = (rear + 1) % bases.length;
    if (nextRear == front)
    {
      throw new IllegalStateException("Queue is full");
    }
    else
    {
      rear = nextRear;
      bases[rear] = base;
    }
  }

  public boolean isFull()
  {
    return (rear + 1)% bases.length == front;
  }

  public Base dequeue()
  {
    if(isEmpty())
    {
      throw new IllegalStateException("Empty Queue");
    }
    else
    {
      front = (front + 1) % bases.length;
      Base temp = bases[front];
      bases[front] = null;
      return temp;
    }
  }
}

class ArrayQueueDriver
{
  public static void main(String[] args)
  {
    ArrayQueue<String> s = new ArrayQueue<String>(6);

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
