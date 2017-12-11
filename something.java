/*import java.text.DecimalFormat;

class BinarySearchTree
{
  private class NodeBinary
  {
    String key;
    NodeLinked value;
    NodeLinked end;
    NodeBinary left;
    NodeBinary right;

    private NodeBinary(String key, int number, NodeLinked value1, NodeBinary left, NodeBinary right, NodeLinked end)
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
    int lineValue;
    NodeLinked next;

    private NodeLinked(int lineValue, NodeLinked next)
    {
      this.lineValue = lineValue;
      this.next = next;
    }
  }

  private NodeBinary root;
  int count = 1;

  public void add(int number, String newString)
  {
    if (root == null)
    {
      root = new NodeBinary(newString,number,null,null,null,null);
    }
    else
    {
      NodeBinary temp = root;
      while(temp != null)
      {
        //System.out.println("twiceSameLine".compareTo("twice"));
        if(temp.key.compareTo(newString) < 0)
        {
          if (temp.right == null)
          {
            temp.right = new NodeBinary(newString,number,null,null,null,null);
            return;
          }
          temp = temp.right;

        }
        else if(temp.key.compareTo(newString) > 0)
        {
          if(temp.left == null)
          {
            temp.left = new NodeBinary(newString,number,null,null,null,null);
            return;
          }
            temp = temp.left;
        }
        else
        {
          if(number != temp.end.lineValue)
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
    binaryPrinter(tree.root);
  }

  public void binaryPrinter(NodeBinary nodeB)
  {
    if(nodeB == null)
    {
      return;
    }


    NodeLinked tempe;
    tempe = nodeB.value;
    binaryPrinter(nodeB.left);
    System.out.printf("%-15.10s", nodeB.key);
    System.out.print(" ");
    linkedListPrinter(tempe);
    System.out.println("");
    binaryPrinter(nodeB.right);
  }

  public void linkedListPrinter(NodeLinked nodeL)
  {
    String s;
    NodeLinked tempor;
    tempor = nodeL;

    while(tempor != null)
    {
      DecimalFormat formattingObject = new DecimalFormat("00000.#");
      s = formattingObject.format(tempor.lineValue);
      System.out.print(s + " ");
      tempor = tempor.next;
   }
   return;
 }
}

class BinarySearchTreeDriver
{
  public static void main(String[] args)
  {
    BinarySearchTree tree = new BinarySearchTree();
    Nomenclator nomenclator = new Nomenclator("Factorials.java", false);
    while (nomenclator.hasNext())
    {
      tree.add(nomenclator.nextNumber(), nomenclator.nextName());
    }
    tree.traverse(tree);
  }
}
*/
