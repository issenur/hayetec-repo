class Oak<Rndm>
{
  private class Runningback
  {
    public String nickName;
    private int rush;
    private int recieve;
    private int jerseyNumber;
    private int touchdown;
    private Runningback next;

    public Runningback(String nickName, int rush, int recieve, int touchdown, int jerseyNumber, Runningback next)
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

  public void peek()
  {
    if(isEmpty())
    {
      throw new IllegalStateException();
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
      System.out.println("");
      System.out.print("|Name:" + top.nickName);
      System.out.print("|RushYards: " + top.rush);
      System.out.println("|JerseyNumber: " + top.jerseyNumber + "| ");
      System.out.println("");
      top = top.next;

    }
  }

  public void push(String nickName, int rush, int recieve, int touchdown, int jerseyNumber)
  {
      top = new Runningback(nickName, rush, recieve, touchdown, jerseyNumber, top);
  }
}

class OakDriver
{
  public static void main(String [] args)
  {
    Oak<Runningback> oak = new Oak<Runningback>();

  oak.push("BeastMode ", 1000, 240, 11, 24);
  oak.push("AP ", 1020, 320, 4, 28);
  oak.push("DFad ", 860, 220, 8, 20);
  oak.pop();
  oak.pop();
  oak.pop();

  }

}
