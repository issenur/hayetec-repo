/*SinglyLinearListMethods*/

/*How to traverse list
starting with front*/
Node temp = front;
while(temp != null)
{
  System.out.println(temp.value);
  temp = temp.next;
}

/*How to search a list
starting with front*/

Node temp = front;
while(temp != null)
{
  if(temp.value.equals(value))
  {
    return true;
  }
  else
  {
    temp = temp.next;
  }
}
return false;

/*How to delete an item
inside the list using
left right trick*/
left = head;
right = head.next;
if(right.badValue.equals(badValue))
{
  left.next = right.next;
  break;
}
else
{
  /*to keep left and right
  marching on*/
  left = right;
  right = right.next;
}

/*How to add a node
before an existing
node(rigth)... before
meaning towards the front
 of the Queue or top of
 the stack . So the code BELOW
 adds a new node to the left of
 the node which right currently
 points at*/

left.next = new Node(x, right);

/*How to add a node
after an existing
node(rigth)... after
meaning towards the rear
 of the Queue or away from the top of
 the stack . So the code BELOW
 adds a new node to the right of 
 the node which right currently
 points at*/

right.next = new Node(x, right.next);
