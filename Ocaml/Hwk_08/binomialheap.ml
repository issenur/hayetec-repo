open Ordered

module type BinomialHeapSig = sig
  type elem
  type tree = Node of int * elem * tree list
  type t = tree list
  val empty: t
  val isEmpty: t-> bool
  val insert: elem -> t -> t
  val merge: t -> t -> t
  val findMin: t -> elem
  val deleteMin: t -> t
  val isBinomialTree: tree -> bool
  val isBinomialHeap: t -> bool
end

(*A binomial tree has a root tree. That root tree could have many children or
none. The number of  children it has is called a rank. Let us refer to the 
rank as "r". Out of its children the one with the highest rank will have
(r-1) rank. The second child will have a rank of (r-2) . This will continue
until we run out of children or our rank is zero. A binomial heap could have 
unlimited amount of children.

When the above invariant is maintained our tree will have 2^k number of Nodes.

The Binomial trees elements should be ordered. All the children node should
have a higher value then the parent node.(at least that was the case in our
implentation.)



A binomial Heaps are made up of Binomial trees I am not going to repeat the
invariants since that would be redundant. The rank of the trees that make up
a heap should never equal in rank. The trees in the heaps should be 
ordered by increasing rank.

A heap should consist of at least one binomial tree.
*)

module BinomialHeap
 (Order : OrderedSig): (BinomialHeapSig with type elem = Order.t)= struct
  type elem = Order.t
  type tree = Node of int * elem * tree list
  type t = tree list

  let rank (Node (r, x, c)) = r
  let root (Node (r, x, c)) = x

  let link (Node(r, x1 ,c1) as t1) (Node(r2, x2, c2) as t2) =
     if (x1 <= x2)
     then( Node (r+1, x1, t2::c1))
     else( Node (r+1, x2, t1::c2))


  let isEmpty l = List.length l = 0
  let empty = []

  let rec insTree t1 t2 =
    match t1, t2 with
    |t,[] -> [t]
    |t, ((t'::ts') as ts)  ->
      if (rank t < rank t')
      then (t::ts)
      else (insTree (link t t') ts')

  let insert x ts = insTree (Node (0, x, [])) ts

  let rec merge trees1 trees2 = match trees1, trees2 with
    |ts1, [] -> ts1
    |[], ts2 -> ts2
    |(t1::ts1' as ts1), (t2::ts2' as ts2) ->
      if rank t1 < rank t2
      then t1 :: merge ts1' ts2
      else if rank t2 < rank t1
           then t2 :: merge ts1 ts2'
           else insTree (link t1  t2)
                   (merge ts1' ts2')

  let rec removeMinTree trees =
    match trees with
    |[] -> raise (Failure "Can't remove nothin")
    |[t] -> (t, [])
    |t::ts ->
           let (t', ts') = removeMinTree ts
           in if root t < root t'
                then (t, ts)
                else (t', t::ts')

  let findMin ts =
      let (t, _) = removeMinTree ts
                 in root t

  let deleteMin ts =
    let (Node (_, _, ts1), ts2) = removeMinTree ts
    in merge (List.rev ts1) ts2

  
  let isElementOf b boollist = List.fold_right (fun b rest ->
    if (b = true)
    then (b)
    else rest) boollist false

  let rec elementListMake treelist =
    match treelist with
    |[] -> []
    |(Node(_, e, _))::ts -> e :: (elementListMake ts)

  let rec rankListMake treelist =
    match treelist with
    |[] -> []
    |(Node(r, _, _))::ts -> r :: (rankListMake ts)

   let elementCheck n elementlist =
     ( (List.filter (fun n' -> (Order.lt n' n) )elementlist) = [])
  
   (*this function checks that the rank is r-1 for the highest ranked 
     child node. And it also checks that the rests ranks are decending by
     one, all the way to the zero rank node*)
   let rec rankCheck n ranklist =
     match ranklist with
     |[] -> true
     |[x] -> if (x = n-1) then true else false
     |x1::x2::xs -> if ( ((x1-x2) = 1) && (x1 = (n-1)) )
                    then ( rankCheck (n-1) (x2::xs) )
                    else false
  
  let rec lengthCheck treelist =
    match treelist with
    |[] -> 0
    |t::ts -> 1 + (lengthCheck ts)

   let rec functionMap f b treeslist =
     match treeslist with
     |[] -> []
     |t::ts-> f b t :: (functionMap f b ts)


  let rec isBinomialTree tree =
   let rec isBinomialTree' b tree  =
     match  tree with
     |Node(0, _, [])  -> true
     |Node(r , e, treelist) ->
     if( ( (r = (lengthCheck treelist)) &&
           ( elementCheck e (elementListMake treelist)) ) &&
           (rankCheck r (rankListMake treelist)) )
     then (isElementOf true (functionMap isBinomialTree' true treelist) )
     else false
    in isBinomialTree' true tree

  let rec isBinomialHeap ts =
    let rec isBinomialHeap' b ts =
    match ts with
    |[] -> true
    |[tree] ->
       if (isBinomialTree tree)
       then(true)
       else(false)
    | (tree1::tree2::trees) ->
      if( (isBinomialTree tree1) && (rank tree1 <= rank tree2))
      then (isBinomialHeap' true  (tree2::trees) )
      else (false)
    in isBinomialHeap' true ts

 end

module BHI = BinomialHeap(Int)

