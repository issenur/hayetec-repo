class HashTable
{
/*we skipping the <angle>
bracket stuff because the
teacher doesn't have time

ut he says the keys will
be Strings and the values
will be obects*/
  private class Node
  {
    private String key;
    private Object value;
    private Node next;

    private Node (String key, Object value, Node next)
    {
      this.key = key;
      this.value = value;
      this.next = next;
    }

    private Node[] table;
    /* it is an array of pointers
    to nodes not an array of nodes*/

    public HashTable(int size)
    {
      table = new Node[Size];
      /*we are supposed to this Size
      for negative but the teacher doesn't
      have time*/
      head = new Node(null, null, null);
      //shared head node
    }

    private int hash(String key)
    {
      return Math.abs(key.hashCode()) % table.length;
    }

    public Object get(String key)
    {
      Node temp = table[hash(key)];
      /*above line gives me a temp
      point that tells me where
      the bucket for the key is

      the while loop is to traverse
      the linked list in the bucket*/
      while(temp != null)
      {
        if(key.equals(temp.key))
        {
          return temp.value;
        }
        else
        {
          temp = temp.next;
        }
      }
      /* if you can't find the key*/
      throw new IllegalArgumentException("Key Not Found");
    }
    /*The below method establishes a connection
    between key and value by using the hash of
    the key to find the corresponding key, there
    might be many keys that share the same hash hashCode
    so it will then search the linked list

    if it can't find a node with a k1 v1 it will make
    one, see the end of the method*/
    public void put(String key, Object value)
    {
      int index = hash(key);
      Node temp = table[index];
      while (temp!= null)
      {
        if(key.equals(temp.key))
        {
          temp.value = value;
          return;
        }
        else
        {
          temp = temp.next;
        }
      }
      table[index] = new Node(key,value,)
    }

    public void delete(String key)
    {
      int index = hash(key);
      Node left = head;
      head.next = table[index];
      Node right = head.next;

      while (right != null)
      {
        if (right.key.equals(key))
        {
          left.next = right.next;
          break;
        }
        else
        {
          left = right;
          right = right.next;
        }
      }
      table[index] = head.next;
      head.next = null;
    }

  }
}
