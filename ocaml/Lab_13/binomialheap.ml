module type OrderedSig = sig
 type t
 val eq: t -> t -> bool
 val lt: t -> t -> bool
 val leq: t -> t-> bool
end

module Int: (OrderedSig with type t = int) = struct
  type t = int
  let eq = (=) 
  let lt = (<)
  let leq = (<=)
end


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
end

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

  let rec removeMinTree trees = match trees with 
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

end

module BHI = BinomialHeap(Int)
let h1 = BHI.empty
let h2 = BHI.insert 20 h1
let h3 = BHI.insert 30 h2
let h4 = BHI.insert 10 h3
let h5 = BHI.insert 40 h4

let m1 = BHI.findMin h5

let h6 = BHI.deleteMin h5

let m2 = BHI.findMin h6



