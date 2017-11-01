class Map<Key, Value>
{
  private Key [] keys;
  private Value[] values;
  private int count;

  public Map(int length)
  {
    count = 0;
    if (length < 0)
      {
        throw new IllegalArgumentException("Length can not be less than 0.");
      }
      keys = (Key[]) new Object[length];
      values = (Value[]) new Object[length];
  }

  public void put(Key key, Value value)
  {
    for (int index = 0; index <= count; index++)
    {
      if (isEqual(key,keys[index]))
      {
        System.out.println("Never Here");
        values[index] = value;
        break;

      }
      else if((keys.length > count) && (values.length > count))
      {
        keys[count]=key;
        values[count]=value;
        count = count + 1;
      
        break;
      }
      else
      {
        break;
      }
    }
  }

  public Value get(Key key)
  {
    if (where(key) ==-1)
    {
    throw new IllegalArgumentException("No element equal to key.");
    }
    else
    {
      return values[where(key)];
    }
  }

  public boolean isIn(Key key)
  {
    for (int index = 0; index < count; index++)
    {
      if (isEqual(key,keys[index]))
      {
        return true;
      }
    }
    return false;
  }

  private boolean isEqual(Key leftKey, Key rightKey)
  {
    if (leftKey == null||rightKey == null)
      {
        return leftKey == rightKey;
      }
    else
      {
        return leftKey.equals(rightKey);
      }
    }

  private int where(Key key)
  {
    for (int index = 0; index < count; index++)
    {
      if (isEqual(key,keys[index]))
      {
        return index;
      }
    }return -1;
  }

  public void display() {
    for (int i = 0; i < keys.length; i++) {
      System.out.print(keys[i] + ": " + values[i] + ", ");
    }
    System.out.println();
  }

}

class Hogwarts
{

//  MAIN. Make an instance of MAP and test it.

  public static void main(String [] args)
  {
    Map<String, String> map;

    try
    {
      map = new Map<String, String>(-5);
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No negatives");       //  No negatives  2 points.
    }

    map = new Map<String, String>(5);

    map.put("Harry",     "Ginny");
    map.put("Ron",       "Lavender");
    map.put("Voldemort", null);
    map.put(null,        "Wormtail");

    System.out.println(map.isIn("Harry"));      //  true          2 points.
    System.out.println(map.isIn("Ginny"));      //  false         2 points.
    System.out.println(map.isIn("Ron"));        //  true          2 points.
    System.out.println(map.isIn("Voldemort"));  //  true          2 points.
    System.out.println(map.isIn(null));         //  true          2 points.
    System.out.println(map.isIn("Joanne"));     //  false         2 points.

    System.out.println(map.get("Harry"));       //  Ginny         2 points.
    System.out.println(map.get("Ron"));         //  Lavender      2 points.
    System.out.println(map.get("Voldemort"));   //  null          2 points.
    System.out.println(map.get(null));          //  Wormtail      2 points.

    try
    {
      System.out.println(map.get("Joanne"));
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No Joanne");          //  No Joanne     2 points.
    }

    map.display();

    map.put("Ron",   "Hermione");
    map.put("Albus", "Gellert");
    map.put(null,    null);

    System.out.println(map.isIn(null));         //  true          2 points.
    System.out.println(map.isIn("Albus"));      //  true          2 points.

    System.out.println(map.get("Albus"));       //  Gellert       2 points.
    System.out.println(map.get("Harry"));       //  Ginny         2 points.
    System.out.println(map.get("Ron"));         //  Hermione      2 points.
    System.out.println(map.get("Voldemort"));   //  null          2 points.
    System.out.println(map.get(null));          //  null          2 points.

    try
    {
      map.put("Draco", "Pansy");
    }
    catch (IllegalStateException minnesota)
    {
      System.out.println("No Draco");           //  No Draco      2 points.
    }
  }
}
