class FamilyTree
{
  private class Node
  {
    private Node father;
    private String name;
    private Node mother;

    private Node(Node father, String name, Node mother)
    {
      this.father = father;
      this.name = name;
      this.mother = mother;
    }
  }
  Node root;

  public FamilyTree(String ego)
  {
    root = new Node(null, ego, null);
  }

  private Node find(String name)
  {
    return(find(name, root));
  }

  private Node find(String name, Node root)
  {


    if(root == null)
    {
      return null;
    }
    else if (name.equals(root.name))
    {
      return root;
    }
    else
    {
      Node t1 =find(name, root.father);

      if(t1 == null)
      {
        return(find(name, root.mother));
      }
      else
      {
        return(t1);
      }
    }
  }

  public void addParents(String ego, String father, String mother)
  {
    Node t = find(ego);
    if (t == null)
    {
      throw new IllegalArgumentException();
    }

    Node f = new Node(null, father , null);
    Node m = new Node(null, mother, null);

    t.father = f;
    t.mother = m;
  }

  public boolean isDescendant(String ego, String ancestor)
  {
    return(isDescendant(find(ego),find(ancestor)));
  }

  private boolean isDescendant(Node root, Node ancestor)
  {
        if(root == null)
        {
          return false;
        }
        else if (root == ancestor)
        {
          return true;
        }

        return(isDescendant(root.father, ancestor) ||
              isDescendant(root.mother, ancestor));
  }
}

//  POTTERY. Driver class, for testing. Each comment shows a point value (for a
//  total of 40 points) and what it should print.

class Pottery
{

//  MAIN. Harry Potter and the Hairier Pottery.

  public static void main(String [] args)
  {
    FamilyTree family = new FamilyTree("Al");

    family.addParents("Al",    "Harry",  "Ginny");
    family.addParents("Harry", "James",  "Lily" );
    family.addParents("Ginny", "Arthur", "Molly");

    try
    {
      family.addParents("Joanne", "Peter", "Anne");
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No Joanne.");  //  2 No Joanne.
    }

    System.out.println(family.isDescendant("Joanne", "Joanne"));  //  2 false

    System.out.println(family.isDescendant("Al", "Al"));          //  2 true
    System.out.println(family.isDescendant("Al", "Harry"));       //  2 true
    System.out.println(family.isDescendant("Al", "Ginny"));       //  2 true
    System.out.println(family.isDescendant("Al", "James"));       //  2 true
    System.out.println(family.isDescendant("Al", "Lily"));        //  2 true
    System.out.println(family.isDescendant("Al", "Arthur"));      //  2 true
    System.out.println(family.isDescendant("Al", "Molly"));       //  2 true
    System.out.println(family.isDescendant("Al", "Joanne"));      //  2 false

    System.out.println(family.isDescendant("Harry", "Harry"));    //  2 true
    System.out.println(family.isDescendant("Harry", "Al"));       //  2 false
    System.out.println(family.isDescendant("Harry", "James"));    //  2 true
    System.out.println(family.isDescendant("Harry", "Lily"));     //  2 true
    System.out.println(family.isDescendant("Harry", "Ginny"));    //  2 false
    System.out.println(family.isDescendant("Harry", "Arthur"));   //  2 false
    System.out.println(family.isDescendant("Harry", "Molly"));    //  2 false
    System.out.println(family.isDescendant("Harry", "Joanne"));   //  2 false

    System.out.println(family.isDescendant("Ginny", "Arthur"));   //  2 true
    System.out.println(family.isDescendant("Arthur", "Ginny"));   //  2 false
  }
}
