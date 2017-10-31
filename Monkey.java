import java.util.Scanner;
class Monkey
{
  public static String sound;
  public static String leader;
  public static int humansKilled;


  public static void main(String [] args)
  {
    Scanner reader = new Scanner(System.in);

    System.out.println("whaat?");

    sound = reader.nextLine();

    System.out.println("who sent  you?");

    leader = reader.nextLine();

    System.out.println("how many humans have you killed?");

    humansKilled = reader.nextInt();

    Monkey laci = new Monkey(sound, leader, humansKilled);
    Monkey robert = new Monkey(laci);



    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("Laci says ");
    System.out.println(laci.getSound());
    System.out.print("Laci's leader is ");
    System.out.println(laci.getLeader());
    System.out.print("Laci has killed ");
    System.out.print(laci.getHumansKilled());
    System.out.println(" Humans.");
    System.out.println();
    System.out.println();
    System.out.println();

    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("Robert says ");
    System.out.println(robert.getSound());
    System.out.print("Robert's leader is ");
    System.out.println(robert.getLeader());
    System.out.print("Rober has killed ");
    System.out.print(robert.getHumansKilled());
    System.out.println(" Humans.");
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();

  }
  public Monkey(String sound, String leader, int humansKilled)
  {
    setMonkey(sound, leader, humansKilled);
  }
  public Monkey(Monkey originalMonkey)
  {
    setMonkey(sound, leader, humansKilled);
  }
 public void setMonkey(String sound, String leader, int humansKilled)
  {
    this.sound = sound;
    this.leader = leader;
    this.humansKilled = humansKilled;
  }
  public String getSound()
  {
    return sound;
  }
  public String getLeader()
  {
    return leader;
  }
  public int getHumansKilled()
  {
    return humansKilled;
  }
}
