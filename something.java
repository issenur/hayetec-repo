/*class BinarySearchTree
{
  private class NodeBinary
  {
    String key;
    NodeLinked value;
    NodeLinked end;
    NodeBinary left;
    NodeBinary right;


    private NodeBinary(String key, int number, NodeBinary left, NodeBinary right)
    {
      this.key = key;
      this.value = new NodeLinked(number,null);
      this.end = this.value;
      this.left = left;
      this.right = right;
    }
    private NodeBinary root;

    public void add(int number, String newString)
    {
      if (root == null)
      {
        root = new NodeBinary(newString,number,null,null);
      }
      else
      {
        NodeBinary temp = root;
        while(temp != null)
        {
          if(temp.key.compareTo(newString) > 0)
          {
            if (temp.right == null)
            {
              temp.right = new NodeBinary(newString,number,null,null);
              return;
            }
            temp = temp.right;
          }
          else if(root.key.compareTo(newString) < 0)
          {
            if(temp.left == null)
            {
              temp.left = new NodeBinary(newString,number,null,null);
              return;
            }
              temp = temp.left;
          }
          else
          {
            temp.end.next = new NodeLinked(number, null);
            temp.end = temp.end.next;
          }
        }
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
  }
}
class BinarySearchTreeDriver
{
  public static void main(String[] args)
  {
    BinarySearchTree tree = new BinarySearchTree();
    Nomenclator nomenclator = new Nomenclator("factorial.java", true);

    while (nomenclator.hasNext())
    {
      tree.add(nomenclator.nextNumber(), nomenclator.nextName());
    }
    /*traverse(tree);

  }
}
*/
