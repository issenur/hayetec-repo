class ShufflerOfArrays
{
public static int count = 10;
  public static void main(String[] args)
  {

    int[] smallArray1 = new int[10];
    int[] smallArray2 = new int[10];
    ShufflerOfArrays.printCount();
    int [] bigArray = {4,7,5,2,5,5,6,2,4,7,2,5,7,2,4,4,4,4,5,5};
    System.out.println(count);
    for(int i = 0; i < 10; i++)
    {
    smallArray1[i] = bigArray[i];
    }

    for(int i = 0; i < 10; i++)
    {
      smallArray2[i] = bigArray[i+10];
    }
    //System.out.println("Top ten Offensive Players");
    for(int i = 0; i <10; i++)
    {
      //System.out.println(smallArray1[i]);
    }
    //System.out.println("Top ten Defensive Players");
    for(int i = 0; i <10; i++)
    {
      //System.out.println(smallArray2[i]);
    }

  }
  public static void printCount()
  {
    System.out.println(count);
  }
}
class Shuff
{

    System.out.println(count);
}
