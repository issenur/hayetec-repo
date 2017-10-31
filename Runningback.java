class Runningback
{
  public String nickName = "journey Man";
  public static String whatRaiderFansBelieve = "Bronocos Suck!";
  private int rush = 5;
  private int recieve = 0;
  private int jerseyNumber = 99;
  private int touchdown = 0;


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
    setRunningback(nickName, rush, recieve, touchdown, jerseyNumber);
  }
  public Runningback(String nickName, int jerseyNumber)
  {
    setRunningback(nickName, rush, recieve, touchdown, jerseyNumber);
  }

  public void setRunningback(String nickName, int rush, int recieve, int touchdown, int jerseyNumber)
  {
    this.nickName = nickName;
    this.rush = rush;
    this.recieve = recieve;
    this.touchdown = touchdown;
    this.jerseyNumber = jerseyNumber;
  }

  public void setRunningback(String nickName,int jerseyNumber)
  {
    this.nickName = nickName;
    this.jerseyNumber = jerseyNumber;
    /*I wanna say for Runnning back that are started with a bla*/
  }
  public void tellMeAboutHim()
  {
    System.out.print ( nickName +" wears number " + jerseyNumber);
    System.out.println (" and has scored " + touchdown + " Touchdowns during his best season.");

  }
  public String getNickName()
  {
    return nickName;
  }

  public int getRush()
  {
    return rush;
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

}
class Widereciever extends Runningback
{
  public String hands = "dont know";

  public Widereciever(String nickName, int rush, int recieve, int touchdown, int jerseyNumber, String hands)
  {
    super(nickName, rush, recieve, touchdown, jerseyNumber);
    setWiderciever(hands);
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

class Game
{
  public static void main(String [] args)
  {
    Runningback adrianPeterson = new Runningback("Big AP ", 0, 0, 20, 28);
    Runningback marshawnLynch = new Runningback("Beast Mode ", 0, 0, 16, 24);
    Widereciever amariCooper = new Widereciever("Killa Coop ", 0, 0, 8, 89, "'s nickname on the field: Mr.Butter Fingers");
    Widereciever travisKelce = new Widereciever("Fake Gronk ", 0, 0, 13, 87, "'s nickname on the field:  Mr.Catchem All");
    Runningback lataviusMurray = new Runningback("Tay Train ", 28);
    Runningback purpleJesus = new Runningback(adrianPeterson);//Copy Constructor, creates a duplicate object with a different refernce varialbe

    System.out.print(adrianPeterson.getNickName());
    System.out.println(adrianPeterson.getJerseyNumber());
    System.out.print(marshawnLynch.getNickName());
    System.out.println(marshawnLynch.getJerseyNumber());
    System.out.print(travisKelce.getNickName());
    System.out.println(travisKelce.getJerseyNumber());
    System.out.print(amariCooper.getNickName());
    System.out.println(amariCooper.getJerseyNumber());
    System.out.print(travisKelce.getNickName());
    System.out.println(travisKelce.getHands());
    System.out.print(amariCooper.getNickName());
    System.out.println(amariCooper.getHands());
    System.out.print(lataviusMurray.getNickName());
    System.out.println(lataviusMurray.getJerseyNumber());
    System.out.println(adrianPeterson.nickName);
    System.out.println(Runningback.whatRaiderFansBelieve);
    adrianPeterson.tellMeAboutHim();
    amariCooper.tellMeAboutHim();
    travisKelce.tellMeAboutHim();
    marshawnLynch.tellMeAboutHim();
    lataviusMurray.tellMeAboutHim();
    purpleJesus.tellMeAboutHim();
  }
}
