public class Tuna
{
  public Tuna front;
  public Tuna tunOb;
  public tuna(Str name, int number, Tuna tunOb)
  {
    setTuna(name,number,tunOb);
  }
  public void setTuna(String name, int number, Tuna tunOb)
  {
    this.name = name;
    this.number = number;
    this.tunOb = tunOb;

  }
  public void push(String name, int number, Tuna tunOb)
  {
    if (tunOb == null)
    {
      front = new Tuna(name, number, tunOb);
      tunOb = front;
    }
      front = new Tuna(name, number, tunOb); 
  }
  public void toString()
  {
    System.out.println("Name: " + name + " Jersey Number: " + number + tunOb.name)
  }
}
