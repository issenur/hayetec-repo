/*Delete p, where p has nodes
on both sides*/
p.left.right = p.right;
p.right.left = p.left;

/*Add  a node before p, where p has nodes
on both sides. see the cheat sheet for p*/
q = new Node(X, p.left, p);
p.left.right = q;
p.left = q;

/*Add a node after p, where p has nodes
on both sides. see cheat sheet for q*/
q = new Node(x,p, p.right);
p.right.left = q;
p.right = q;

CircularDoublyLinkedList
/* traverse to the right*/
Node where = head.right;
while(where != head)
{
  //visit where
  where = where.right;
}

/* traverse to the left*/
Node where = head.left;
while (where != head)
{
  //visit where
  where = where.left;
}

/*CircularDoublyLinkedList
creating head node*/
head = new Node(null, null, null);
head.left = head;
head.right = head;
