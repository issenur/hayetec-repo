class ArrayQueueIt<Base>
{
	public class Iterator
	{
		private int where;
		private int rearIterator;
		private Base[] objectsIterator;

		private Iterator(int where, int rear, Base[] objects)
		{
			this.where = where;
			this.rearIterator = rear;
			objectsIterator = objects;
		}
		public boolean hasNext()
		{
			return where != rearIterator;
		}

		public Base next()
		{
			if(!hasNext())
			{
				throw new IllegalStateException("Iterator is down");
			}
			else
			{
				where = (where + 1 ) % objectsIterator.length;
				Base temp = objectsIterator[where];
				return temp;
			}
		}
	}

	private int    front;
	private int    rear;
	private Base[] objects;

	public ArrayQueueIt(int size)
	{
		if (size <= 1)
		{
			throw new IllegalArgumentException("Illegal size.");
		}
		else
		{
			front   = 0;
			rear    = 0;
			objects = (Base []) new Object[size];
		}
	}

	public Iterator iterator()
	{
		return new Iterator(front , rear, objects);
	}

  public Base dequeue()
  {
    if (front == rear)
    {
      throw new IllegalStateException("Queue is empty.");
    }
    else
    {
      front = (front + 1) % objects.length;
      Base temp = objects[front];
      objects[front] = null;
      return temp;
    }
  }

  public void enqueue(Base object)
  {
    int nextRear = (rear + 1) % objects.length;
    if (front == nextRear)
    {
      throw new IllegalStateException("Queue is full.");
    }
    else
    {
      rear = nextRear;
      objects[rear] = object;
    }
  }

  public boolean isEmpty()
  {
    return front == rear;
  }
  public boolean isFull()
  {
    return front == (rear + 1) % objects.length;
  }
}

class ArrayQueueItDriver
{
  public static void main(String [] args)
  {
    ArrayQueueIt<String> s = new ArrayQueueIt<String>(4);

    s.enqueue("A");
    s.enqueue("B");
    s.enqueue("C");

		ArrayQueueIt<String>.Iterator first = s.iterator();
    while (first.hasNext())
    {
      System.out.println(first.next());    //  A B C one per line    5 points
    }
    System.out.println(s.isEmpty());   //  false                 1 point
    System.out.println(s.dequeue());   //  A                     1 point
    System.out.println(s.dequeue());   //  B                     1 point
    System.out.println(s.dequeue());   //  C                     1 point
    System.out.println(s.isEmpty());   //  true                  1 point

    s.enqueue("X");
    s.enqueue("Y");
    s.enqueue("Z");

    ArrayQueueIt<String>.Iterator second = s.iterator();
    while (second.hasNext())
    {

      System.out.println(second.next());
    }

    System.out.println(s.isEmpty());   //  false                 1 point
    System.out.println(s.dequeue());   //  X                     1 point
    System.out.println(s.dequeue());   //  Y                     1 point
    System.out.println(s.dequeue());   //  Z                     1 point
    System.out.println(s.isEmpty());   //  true                  1 point
  }
}
