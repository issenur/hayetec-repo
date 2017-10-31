public class ShufflerOfArrays
{
  private static int count;

  public static void main(String[] args)
  {
    int[] smallArray1 = new int[10];
    int[] smallArray2 = new int[10];
    int[] bigArray = {4,89,15,72,24,87,10,65,30,74,52,51,53,42,29,27,38,21,97,22};
    count = smallArray2.length;


    for(int i = 0; i < smallArray1.length; i++)
    {
    smallArray1[i] = bigArray[i];
    }


    for(int i = 0; i < smallArray2.length; i++)
    {
    	smallArray2[i] = bigArray[count];
    	count = count + 1;
    }


    System.out.println("");
    System.out.println("Top ten Offensive Players");


    for(int i = 0; i <10; i++)
    {
      System.out.println(smallArray1[i]);
    }

    System.out.println("");
    System.out.println("Top ten Defensive Players");


    for(int i = 0; i <10; i++)
    {
      System.out.println(smallArray2[i]);
    }


  }
}
