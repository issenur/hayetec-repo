class Sequence<Base>
{
  private Base[] objects;/**/
  private int count;
  private static final int defaultSize = 10;
  private static final int increment = 20;

  public Sequence(int size)
  {
    /*BELOW CODE everytime you are creating
    a new instance you must cast but
    still have Object.

    The rest of places with the
    Object name  must be replaced with
    Base*/
    objects = (Base[])new Object[size];
    count = 0;
  }

  public Sequence()
  {
    this(defaultSize);
  }

  public void add(Base object) {
    if (count >= objects.length) {
      Base[] newObjects = (Base [])new Object[objects.length + increment];
      for (int index = 0; index < objects.length; index = index + 1) {
        newObjects[index] = objects[index];
      }
      /*magic here, taking
      old array name re assiging
      to new larger array*/
      objects = newObjects;
    }
    objects[count] = object;
    count = count + 1;
    /*After this method is done the
    newObjects pointer dissapears*/
  }

  public Base get(int index)
  {
    if(0 <= index && index < count)
    {
      return objects [index];
    }
    else
    {
      throw new IllegalArgumentException("out of range");
    }
  }

  public int find(Base base)
  {
    for (int index = 0; index < count; index++)
    {
      if(objects[index].equals(base))
      {
        return index;
      }
      else if(objects[index] == base)
      {
        return index;
      }
    }
    return -1;
  }

  public int length()
  {
    return count;
  }

  public void remove(int index)
  {
    if(0 <= index && index < count)
    {
      while(index < count - 1)
      {

        objects[index] = objects[index + 1];
        index = index + 1;
        System.out.println(objects[index]);
      }
      count = count - 1;
      objects[count] = null;
    }
    else
    {

      throw new IllegalArgumentException("out of range");
    }
  }
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append('[');
    if (count > 0)
    {
      builder.append(objects[0]);
      for (int index = 1; index < count; index = index + 1)
      {
        builder.append(", ");
        builder.append(objects[index]);
      }
    }
    builder.append(']');
    return builder.toString();
  }
}

class SequenceDriver
{
  public static void main(String[] args)
  {
    Sequence<String> s = new Sequence<String>(10);
    s.add("A");
    s.add("B");
    s.add("C");
    System.out.println(s.get(1));

    System.out.println(s);
  }
}
