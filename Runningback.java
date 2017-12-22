class Runningback
{
  public String nickName;
  public static String whatRaiderFansBelieve = "Bronocos Suck!";
  private int rush;
  private int recieve;
  private int jerseyNumber;
  private int touchdown;


  public Runningback(Runningback originalRunningback)
  {
    nickName = originalRunningback.nickName;
    rush = originalRunningback.rush;
    recieve = originalRunningback.recieve;
    touchdown = originalRunningback.touchdown;
    jerseyNumber = originalRunningback.jerseyNumber;

  }
  public Runningback(String nickName, int rush, int recieve, int touchdown, int jerseyNumber)
  {
    this.nickName = nickName;
    this.rush = rush;
    this.recieve = recieve;
    this.touchdown = touchdown;
    this.jerseyNumber = jerseyNumber;
  }
  public Runningback(String nickName, int jerseyNumber)
  {
    setRunningback(nickName, jerseyNumber);
     this.rush = 666;
     this.recieve = 666;
     this.touchdown  = 666;
  }


  public void setRunningback(String nickName,int jerseyNumber)
  {
    this.nickName = nickName;
    this.jerseyNumber = jerseyNumber;
  }


  public void tellMeAboutHim()
  {
    System.out.print ( nickName +" wears number " + jerseyNumber);
    System.out.println (" and has scored " + touchdown + " Touchdowns " + recieve + " recieving yards this season." );

  }
  public String getNickName()
  {
    return nickName;
  }

  public int getRush()
  {
    return rush;
  }
  public void setRecieve(int Recieve)
  {
    this.recieve = recieve;
  }
  public int getRecieve()
  {
    return recieve;
  }

  public int getJerseyNumber()
  {
    return jerseyNumber;
  }

  public int getTouchdown()
  {
    return touchdown;
  }
  public boolean equals(Object otherObject)
  {
    if (otherObject == null)
    {
      return false;
    }
    else if (this.getClass() != otherObject.getClass())
    {
      return false;
    }
    else
    {
      Runningback otherRunningback = (Runningback)otherObject;
      return(nickName.equals(otherRunningback.nickName))&&(jerseyNumber == otherRunningback.jerseyNumber);
      }
    }

  public boolean equalss(Runningback otherRunningback)
  {
    if (otherRunningback == null)
    {
      return false;
    }
    else
    {
      return nickName.equals(otherRunningback.nickName)&&this.getRecieve()==otherRunningback.getRecieve();
    }
  }
}
class Widereciever extends Runningback
{
  public String hands = "dont know";

  public Widereciever(String nickName, int rush, int recieve, int touchdown, int jerseyNumber, String hands)
  {
    super(nickName, rush, recieve, touchdown, jerseyNumber);
    setWiderciever(hands);
  }

  public Widereciever(Widereciever originalWideReciever)
  {
    super(originalWideReciever);
  }

  public void setWiderciever(String hands)
  {
    this.hands = hands;
  }
  public String getHands()
  {
    return hands;
  }
}

class RunningbackDriver
{
  public static void main(String [] args)
  {
    Runningback adrianPeterson = new Runningback("Big AP ", 0, 0, 20, 28);
    Runningback marshawnLynch = new Runningback("Beast Mode ", 0, 0, 20, 28);
    Widereciever amariCooper = new Widereciever("Killa Coop ", 0, 0, 8, 89, "'s nickname on the field: Mr.Butter Fingers");
    Widereciever travisKelce = new Widereciever("Fake Gronk ", 0, 0, 13, 87, "'s nickname on the field:  Mr.Catchem All");
    Runningback lataviusMurray = new Runningback("Tay Train ", 28);
    Runningback purpleJesus = new Runningback(adrianPeterson);//Copy Constructor, creates a duplicate object with a different refernce varialbe
    Widereciever ac = new Widereciever(amariCooper);


    amariCooper.tellMeAboutHim();

  }
}
