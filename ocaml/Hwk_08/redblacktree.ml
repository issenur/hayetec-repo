open Ordered 

module type RedBlackSetSig = sig
  type elem
  type color = R | B
  type t = E | T of color * t * elem * t
  val empty: t
  val member: elem -> t -> bool
  val balance: t -> t
  val insert: elem -> t -> t
  val isRedBlackTree: t -> bool
end

(*No red node can have a child node that is red. This mean that the every red
node must have two black children. Those two children must either be black 
nodes or Empty nodes. An empty Nodes are not red.  Every path must have the 
same amount of black nodes, from the root to the empty node.

If these two invariants are met, it implies  the rest of them.

The rest of them being: The root must always be black. The longest path
must have alternating red and black nodes. The shortest path should have
only black nodes. Length of longest is no more then twice the length
of the shortest*)

module RedBlackTree
 (Order : OrderedSig): (RedBlackSetSig with type elem = Order.t) = struct
 type elem = Order.t
 type color = R | B
 type t = E | T of color * t * elem * t

 let empty = E

 let rec member x s =
   match x, s with
   |(x, T(_, a, y, b)) ->
     if (Order.lt x y)
     then (member x a)
     else if (Order.lt y x)
     then (member x b)
     else true
  |(_, E) -> raise(Failure "Can't search Empty Tree")

let rec balance t =
  match t with
  |T(B,T(R,T(R,a,x,b),y,c),z,d) -> T(R,T(B,a,x,b),y,T(B,c,z,d))
  |T(B,T(R,a,x,T(R,b,y,c)),z,d) -> T(R,T(B,a,x,b),y,T(B,c,z,d))
  |T(B,a,x,T(R,T(R,b,y,c),z,d)) ->  T(R,T(B,a,x,b),y,T(B,c,z,d))
  |T(B,a,x,T(R,b,y,T(R,c,z,d))) ->  T(R,T(B,a,x,b),y,T(B,c,z,d))
  |T (a,b,c,d) -> T (a,b,c,d)
  |E -> E

let  insert  x s =
  let rec ins s =
    match s with
    |E -> T(R, E, x, E)
    |T(color, a, y, b) as s ->
      if (Order.lt x y)
      then balance (T(color, ins a, y, b))
      else if (Order.lt y x)
      then balance (T(color, a, y , ins b))
      else s


   |T(_,a,y,b) -> T(B,a,y,b)
   |E -> E 

let getElem tree = 
  match tree with 
  |(T (c,l,e,r)) -> e
  |E -> raise(Failure "cannot be E")


let isEmpty e = (e = E)

let twoChild tree =
  match tree with
  |(T (c,l,e,r)) -> (l <> E) && (r <> E)
  |E -> raise(Failure "cannot be E")

let noChild tree =
  match tree with
  |(T (c,l,e,r)) -> (l = E) && (r = E)
  |E -> raise(Failure "cannot be E")

let leftChildOnly tree =
  match tree with
  |(T (c,l,e,r)) -> (l <> E) && (r = E)
  |E -> raise(Failure "cannot be E")

let rightChildOnly tree =
  match tree with
  |(T (c,l,e,r)) -> (l = E) && (r <> E)
  |E -> raise(Failure "cannot be E")

 (*this function returns a list of black node count. Every entry in the list
   is nodes in a path*)


let rec blackCount tree n =
  match tree with
  |T(B, l, e ,r) ->
    if (twoChild tree)
    then (blackCount r (n+1) @ blackCount l (n+1))
    else if (leftChildOnly tree)
    then (blackCount l (n+1))
    else if (rightChildOnly tree)
    then (blackCount r (n+1))
    else [n+1]
  |T(R, l, e, r) ->
    if (twoChild tree)
    then (blackCount r (n) @ blackCount l (n))
    else if (leftChildOnly tree)
    then (blackCount l (n))
    else if (rightChildOnly tree)
    then (blackCount r (n))
    else [n]
  |E -> [0]


(* this function takes a list of node count entries. Then it filters the first
 black node count amount from the list if it is Empty at the end that means
all paths have the same amount of black nodes*)

let blackPerPath tree =
   match blackCount tree 0 with
   |[] -> raise(Failure "The tree must have One Black Node")
   |(x::xs) ->
     if((List.filter (fun n -> x <> n) xs = []))
     then true
     else false

let isRed node = 
  match node with 
  |E -> false
  |T(R,_,_,_)  -> true 
  |T(B,_,_,_) -> false

let rec noRedFamily tree =
  match tree with
  |E -> true
  |T(B,l,e,r) -> ((noRedFamily l) &&  (noRedFamily r))
  |T(R,l,e,r) ->
    if ((isRed l) || (isRed r))
    then false
    else ((noRedFamily l) && (noRedFamily r))

let rec isBinarySearch tree =
  match tree with
  |E -> true
  |T(_,l,e,r) ->
    if (noChild tree)||(isEmpty tree) 
    then(true)
    else if ((twoChild tree) && 
      (Order.lt (getElem l) e) && 
      (Order.lt e (getElem r)))
    then ((isBinarySearch l) && (isBinarySearch r))
    else if ((leftChildOnly tree) && (Order.lt (getElem l) e) )
    then (isBinarySearch l)
    else if (Order.lt e (getElem r))
    then (isBinarySearch r)
    else false
    
let  isRedBlackTree tree =
  if((blackPerPath tree) &&
    (noRedFamily tree) &&
    (isBinarySearch tree))
  then true
  else false
 
end

module RBTI = RedBlackTree (Int)

