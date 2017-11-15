class AssociationList<Key, Value>
{
  private Node top;
  private Node temp;

  public AssociationList()
  {
    top = new Node(null, null, null);
  }

  private class Node
  {
    private Key key;
    private Value value;
    private Node next;

    private Node(Key key, Value value, Node next)
    {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }



  public void delete(Key key)
  {
      Node right = top.next;
      Node left = top;


      while(right != null)
      {
        if(isEqual(right.key,key))
        {
          left.next = right.next;
          return;
        }
        else
        {
          left = right;
          right = left.next;
        }
      }
  }
  public Value get(Key key)
  {
    temp = top.next;
    while(temp != null)
    {
      if(isEqual(temp.key,key))
      {
        return (temp.value);
      }
      else
      {
        temp = temp.next;
      }
    }
    throw new IllegalArgumentException();
  }

  public boolean isEqual(Key leftKey, Key rightKey)
  {
    if ((leftKey != null) && (rightKey != null))
    {
        return leftKey.equals(rightKey);
    }
    else
    {
        return leftKey == rightKey;
    }
  }

  public boolean isIn(Key key)
  {
    temp = top.next;
    while(temp != null)
    {
      if(isEqual(temp.key,key))
      {
        return true;
      }
      else
      {
        temp = temp.next;
      }
    }
    return false;
  }

  public void put(Key key, Value value)
  {
    temp = top.next;
    while(temp != null)
    {
      if(isEqual(temp.key,key))
      {
        temp.value = value;
        return;
      }
      else
      {
        temp = temp.next;
      }
    }
      top.next = new Node(key, value, top.next);
    }
}
//
//  Tests for CSci 1913 Lab 10
//  James Moen
//  14 Nov 17
//
//  The TRY-CATCH statements catch exceptions thrown by ASSOCIATION LIST's
//  methods, so that the program can continue to run even if a method fails.
//  We still haven't talked about TRY-CATCH'es in lecture yet.
//
//  Each test has a comment that shows what it should print and how many points
//  it is worth, for a total of 40 points.

//  HOGWARTS. The Hogwarts dating service again.

class Hogwarts
{

//  MAIN. Make an instance of ASSOCIATION LIST and test it.

  public static void main(String[] args)
  {
    AssociationList<String,String> list = new AssociationList<String,String>();

    System.out.println(list.isIn(null));         //  false         2 points.

    try
    {
      System.out.println(list.get(null));
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No null");             //  No null       2 points.
    }

    list.put(null,        "Wormtail");
    list.put("Ron",       "Lavender");
    list.put("Voldemort", null);
    list.put("Dean",      "Ginny");

    System.out.println(list.isIn("Dean"));       //  true          2 points.
    System.out.println(list.isIn("Ginny"));      //  false         2 points.
    System.out.println(list.isIn("Ron"));        //  true          2 points.
    System.out.println(list.isIn("Voldemort"));  //  true          2 points.
    System.out.println(list.isIn(null));         //  true          2 points.
    System.out.println(list.isIn("Joanne"));     //  false         2 points.

    System.out.println(list.get("Ron"));         //  Lavender      2 points.
    System.out.println(list.get("Dean"));        //  Ginny         2 points.
    System.out.println(list.get("Voldemort"));   //  null          2 points.
    System.out.println(list.get(null));          //  Wormtail      2 points.

    try
    {
      System.out.println(list.get("Joanne"));
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No Joanne");           //  No Joanne     2 points.
    }

    list.delete(null);

    System.out.println(list.isIn(null));         //  false         2 points.

    list.put(null,    null);
    list.put("Harry", "Ginny");
    list.put("Ron",   "Hermione");

    System.out.println(list.isIn(null));         //  true          2 points.
    System.out.println(list.get(null));          //  null          2 points.
    System.out.println(list.get("Harry"));       //  Ginny         2 points.
    System.out.println(list.get("Dean"));        //  Ginny         2 points.
    System.out.println(list.get("Ron"));         //  Hermione      2 points.

    list.delete("Dean");

    try
    {
      System.out.println(list.get("Dean"));
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No Dean");             //  No Dean       2 points.
    }
  }
}
