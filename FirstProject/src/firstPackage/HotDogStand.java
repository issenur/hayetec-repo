package firstPackage;

public class HotDogStand
{
  public int id;
  public int numberSold =0;
  public static int count=0;

  public HotDogStand(int id, int numberSold)
  {
    setHotDogStand(id,numberSold);
  }

  public static int counter()
  {

    System.out.println("Total sold = " + count);
    return count;
  }

  public void justSold()
  {
    numberSold = numberSold + 1;
    count = count +1;
  }

  public int soldByStand()
  {
    System.out.println("Stand " + id + " Sold " + numberSold);
    return numberSold;
  }

  public void setHotDogStand (int id, int numberSold)
  {
    this.numberSold = numberSold;
    this.id = id;
  }

  public void setHotDogStand (int id)
  {
    setHotDogStand(id, numberSold);
  }

  public int getId()
  {
    return id;
  }

  public int getNumberSold()
  {
    return numberSold;
  }
}
