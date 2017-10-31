class Map<Key, Value>
{
  private Key keys;
  private Value values;
  private int count;

  public Map(int length)
  {
    count = 0;
    if (length == 0)
      {
        throw new IllegalArgumentException("Length can not be less than 0.");
      }
      keys = newwwww (Key[])Object[20];
      values = new (Value[])Object[20];
  }
  public void put(Key key, Value value)
  {
    for (int index = 0; index < count; index++)
    {
      if (key == keys[index])
      {
        (value = values[index]);
      }
      else if((keys.length < count) && (values.length < count))
      {
        keys[index]=key;
        values[index]=value;
      }
      else
      {
        throw new IllegalArgumentException("Array is full");
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
      where(key);
    }
  }

  public boolean isIn(String key)
  {
    for (int index = 0; (index < count); index++)
    {
      if (isEqual(key,keys[index]))
      {
        return True;
      }
    }
    return False;
  }
  private boolean isEqual(Key leftKey, Key rightKey)
  {
    if (leftkey == null||rightKey == null)
      {
        return leftkey == rightkey;
      }
    else
      {
        leftkey.equals(rightkey);
      }
    }


  private int where(Key key)
  {
    for (int index = 0; index <count; index++)
    {
      if (isEqual(key,keys))
      {
        return index;
      }
    }return -1;
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
