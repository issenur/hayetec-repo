class BST<Key extends Comparable<Key>, Value>
{
  //so we can get an error if we try to
  //plug in something that is not comprable

  //beefed up get message below  here, see BST.java
  public value get(Key key)
  {
    int comp = key.compareTo(temp.key);

    if(comp<0)
    {
      temp = temp.left;
    }
    else if (comp > 0)
    {
      temp = temp.right;
    }
    else
    {
      return temp.value;
    }
  }
  throw new IllegalArgumentException("No Key");
}
