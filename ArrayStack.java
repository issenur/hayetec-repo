class ArrayStack<Base>
{
  private Base[] bases;
  private int count;

  public ArrayStack(int size)
  {
    bases =(Base[]) new Object[size];
    count = 0;
  }

  public boolean isEmpty()
  {
    return count == 0;
  }

  public boolean isFull()
  {
    return count >= bases.length;
  }

  public void push(Base base)
  {
    if (isFull())
    {
      throw new IllegalStateException("Stack is full");
    }
    else
    {
      bases[count] = base;
      count = count + 1;
    }
  }

  public void pop()
  {
    if(isEmpty())
    {
      throw new IllegalStateException("Stack is empty");
    }
    else
    {
      bases[count-1] = null;
      count = count - 1;
    }
  }

  public Base peek()
  {
    if(isEmpty())
    {
      throw new IllegalStateException("Stack is empty");
    }
    else
    {
      return bases[count - 1];
    }
  }
}
class ArrayStackDriver
{
  public static void main(String[] args)
  {
    ArrayStack<String> s = new ArrayStack<String>(20);

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
    s.pop();
    System.out.println(s.peek());
    s.pop();
    System.out.println(s.peek());

  }
}
