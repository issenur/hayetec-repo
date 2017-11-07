class Oak<Rndm>
{
  private class Runningback
  {
    public Rndm nickName;
    private int rush;
    private int recieve;
    private int jerseyNumber;
    private int touchdown;
    private Runningback next;

    public Runningback(Rndm nickName, int rush, int recieve, int touchdown, int jerseyNumber, Runningback next)
    {
      this.nickName = nickName;
      this.rush = rush;
      this.recieve = recieve;
      this.touchdown = touchdown;
      this.jerseyNumber = jerseyNumber;
      this.next = next;

    }
  }

  private Runningback top;

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
      return (top.nickName);
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

  public void push(Rndm nickName, int rush, int recieve, int touchdown, int jerseyNumber)
  {
      top = new Runningback(nickName, rush, recieve, touchdown, jerseyNumber, top);
  }
}

class OakDriver
{
  public static void main(String [] args)
  {
    Oak<Runningback> oak = new Oak<Runningback>();

  oak.push("Zeke", 1000, 240, 11, 24);
  System.out.println(oak.peek());


  }

}
