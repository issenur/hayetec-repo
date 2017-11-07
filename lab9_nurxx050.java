import java.util.Iterator;

class ArrayQueue<Item> implements Iterable<Item>
{

	private Item[] q;
	private int head=0,tail=0,capacity;

	//iterator class
	public Iterator<Item> iterator()
  {
    return new ArrayIterator();
  }

  private class ArrayIterator implements Iterator<Item>
  {
		private int i = head;
		public boolean hasNext()
    {
      return i<tail;
    }
		public void remove()
    {

    }
		public Item next()
    {
      return q[i++];
    }
	}


	@SuppressWarnings("unchecked")
	public ArrayQueue(int c)
  {
		q = (Item[]) new Object[c];
		capacity = c;
	}

	public boolean isEmpty()
  {
		return (tail==head);
	}

	public void enqueue(Item item)
  {
		if(tail ==  capacity )
			resize(2*capacity);
		q[tail++] = item;
	}

	public Item dequeue()
  {
		if(isEmpty())
			return null;
		Item item = q[head]; //if we returned this line, then loitering
		q[head++]=null; //this line to avoid loitering
		if ((tail-head)==(capacity/4) && tail-head>1){resize (capacity/2);}
		return item;
	}

	private void resize (int c)
  {

		@SuppressWarnings("unchecked")
		Item[] copy = (Item[]) new Object[c];
		for(int i=0;i<capacity-head;i++)
    {
			copy[i]=q[(i+head)];
			if(copy[i]==null) break;
		}
		tail=tail-head;
		capacity = c;
		head=0;
		q = copy;
	}
}
  class Queterator
{

//  MAIN. Start execution here.

  public static void main(String [] args)
  {

//  Make an ARRAY QUEUE and enqueue some STRINGs.

    ArrayQueue<String> queue = new ArrayQueue<String>(4);

    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

//  Make a FIRST ITERATOR for QUEUE and use it to visit QUEUE's elements.

    ArrayQueue<String>.Iterator first = queue.iterator();
    while (first.hasNext())
    {
      System.out.println(first.next());    //  A B C one per line    5 points
    }

//  The iterator hasn't changed QUEUE.

    System.out.println(queue.isEmpty());   //  false                 1 point
    System.out.println(queue.dequeue());   //  A                     1 point
    System.out.println(queue.dequeue());   //  B                     1 point
    System.out.println(queue.dequeue());   //  C                     1 point
    System.out.println(queue.isEmpty());   //  true                  1 point

//  Let's enqueue more things to QUEUE.

    queue.enqueue("X");
    queue.enqueue("Y");
    queue.enqueue("Z");

//  Now make a SECOND ITERATOR for QUEUE. The FIRST one does not work any more,
//  because QUEUE has changed. Use SECOND to visit QUEUE's new elements.

    ArrayQueue<String>.Iterator second = queue.iterator();
    while (second.hasNext())
    {
      System.out.println(second.next());   //  X Y Z one per line    5 points
    }

//  The new iterator hasn't changed QUEUE either.

    System.out.println(queue.isEmpty());   //  false                 1 point
    System.out.println(queue.dequeue());   //  X                     1 point
    System.out.println(queue.dequeue());   //  Y                     1 point
    System.out.println(queue.dequeue());   //  Z                     1 point
    System.out.println(queue.isEmpty());   //  true                  1 point
  }

}
