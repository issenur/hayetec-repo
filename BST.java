class BST<value>
{
  private class Node
  {
    private int key;
    private Value value;
    private Node left;
    private Node right;

    private Node(int key, Value value)
    {
      this.key = key;
      this.value = value;
      left = null;
      right = null;
    }
    private Node root;

    public BST()
    {
      root = null;
    }

    public Value get(int key)
    {
      Node temp = root;
      while(temp != null)
      {
        if(key < temp.key)
        {
          temp = temp.left;
        }
        else if (key > temp.key)
        {
          temp = temp.right;
        }
        else
        {
          return temp.value;
        }
      }
      throw new Exception("No Value Found");
    }

    public void put(int key, Value value)
    {
      if (root == null)
      {
        root = new Node(key, value);
      }
      else
      {
        Node temp = root;
        while(true)
        {
          if(key< temp.key)
          {
            if(temp.left == null)
            {
              temp.left = new Node(key , value);
              return;
            }
            else
            {
              temp = temp.left;
            }
          }
          else if(key> temp.key)
          {
            if(temp.right == null)
            {
              temp.right = new Node(key, value);
              return;
            }
            else
            {
              temp = temp.right;
            }
          }
          else
          {
            temp.value = value;
            return;
          }
        }
      }
    }
  }
}
class BSTDriver
{
  public static void main(String[] args)
  {
    BST<String> t = new BST<String>();
    t.put(17,"17");
    t.put(13,"13");
    t.put(23,"23");
    t.put(15,"15");
  }
}
