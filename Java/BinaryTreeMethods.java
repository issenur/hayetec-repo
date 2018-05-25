public void preorder(Node root)
{
  if(root != null)
  {
    //visit root
    preorder(root.left);
    preorder(root.right);
  }
}

public void postorder(Node root)
{
  if(root != null)
  {
    postorder(root.left);
    postorder(root.right);
    //visit root
  }
}


public void inorder(Node root)
{
  if(root != null)
  {
    inorder(root.left);
    //visit root
    inorder(root.right);
    //this goes from least to
    //to most for BST
    //flip left and right
    //to go from most to least.
  }
}

  /*to count the number of Nodes*/
  public int count(Node root)
  {
    if(root == null)
    {
      return 0;
    }
    else
    {
      // pre order in disiguise, the 1 means visit node
      //then recursive to root.left and root.right;
      return(1 + count(root.left) + count(root.right));
    }
  }

  public int height(Node root)
  {
    if(root == null)
    {
      return 0;
    }
    else
    {
      // post order in disiguise, recursively vist
      //the root.left and root.right
      //then visit the node at the end;
      int l = height(root.left);
      int r = height(root.right);
      if(l > r)
      {
        return l + 1;
      }
      else
      {
        return r + 1;
      }
    }
  }

public void breadthfirst(Node root)
{
  Queue<Node> queue = new Queue<Node>();
  queue.enqueue(root);
  while(!queue.isEmpty())
  {
    Node subtree = queue.dequeue();
    if(subtree != null)
    {
      queue.enqueue(subtree.left);
      queue.enqueue(subtree.right);
    }
  }
}
