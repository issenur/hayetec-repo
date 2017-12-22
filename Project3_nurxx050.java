import java.text.DecimalFormat;

class BinarySearchTree
{
  private class NodeBinary
  {
    String key;
    NodeLinked value;
    NodeLinked end;
    NodeBinary left;
    NodeBinary right;

    private NodeBinary(String key,
                  int number, NodeLinked value1,
                  NodeBinary left, NodeBinary right,
                  NodeLinked end)
    {
      this.key = key;
      value = new NodeLinked(number,null);
      this.value = value;
      this.end = value;
      this.left = left;
      this.right = right;
    }
  }

  private class NodeLinked
  {
    int lineNumber;
    NodeLinked next;

    private NodeLinked(int lineNumber,
                  NodeLinked next)
    {
      this.lineNumber = lineNumber;
      this.next = next;
    }
  }

  private NodeBinary root;
  public void add(int number, String newString)
  {
    if (root == null)
    {
      root = new NodeBinary(newString,
          number,null,null,null,null);
    }
    else
    {
      NodeBinary temp = root;
      while(temp != null)
      {
        if(temp.key.compareTo(newString) < 0)
        {
          if (temp.right == null)
          {
            temp.right = new NodeBinary(newString,number,
                              null,null,null,null);
            return;
          }
          temp = temp.right;
        }
        else if(temp.key.compareTo(newString) > 0)
        {
          if(temp.left == null)
          {
            temp.left = new NodeBinary(newString,number,
                                null,null,null,null);
            return;
          }
          temp = temp.left;
        }
        else
        {
          if(number != temp.end.lineNumber)
          {
          temp.end.next = new NodeLinked(number, null);
          temp.end = temp.end.next;
          }
          break;
        }
      }
    }
  }

  public void traverse(BinarySearchTree tree)
  {
    inOrder(tree.root);
  }

  public void inOrder(NodeBinary nodeB)
  {
    if(nodeB == null)
    {
      return;
    }
    inOrder(nodeB.left);
    visitNode(nodeB.value, nodeB.key);
    inOrder(nodeB.right);
  }

  public void visitNode(NodeLinked nodeN, String key)
  {
    NodeLinked temp = nodeN;
    DecimalFormat formattingObject;
    String s;
    System.out.printf("%-30.25s", key);
    System.out.print(" ");
    while(temp != null)
    {
      formattingObject = new DecimalFormat("00000.#");
      s = formattingObject.format(temp.lineNumber);
      System.out.print(s + " ");
      temp = temp.next;
    }
    System.out.println("");
  }
}

class BinarySearchTreeDriver
{
  public static void main(String[] args)
  {

    BinarySearchTree tree = new BinarySearchTree();
    Nomenclator nomenclator = new Nomenclator("Factorials.java", true);

    while (nomenclator.hasNext())
    {
      tree.add(nomenclator.nextNumber(), nomenclator.nextName());
    }
    tree.traverse(tree);
  }
}

/*

00001 //  FACTORIALS. Print some factorials.
00002
00003 class Factorials
00004 {
00005
00006 //  FACTORIAL. Return the factorial of N.
00007
00008   private static int factorial(int n)
00009   {
00010     if (n == 0)
00011     {
00012       return 1;
00013     }
00014     else
00015     {
00016       return n * factorial(n - 1);
00017     }
00018   }
00019
00020   //  MAIN. Write the factorials of 0 through 10.
00021
00022   public static void main(String [] args)
00023   {
00024      for (int k = 0; k <= 10; k += 1)
00025     {
00026        System.out.println(k + "! = " + factorial(k));
00027     }
00028   }
00029 }


Factorials                     00003
String                         00022
System                         00026
args                           00022
class                          00003
else                           00014
factorial                      00008 00016 00026
for                            00024
if                             00010
int                            00008 00024
k                              00024 00026
main                           00022
n                              00008 00010 00016
out                            00026
println                        00026
private                        00008
public                         00022
return                         00012 00016
static                         00008 00022
void                           00022

*/
