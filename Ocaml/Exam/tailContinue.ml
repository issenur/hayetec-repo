

(* More tail recurivaluee tree functions.

   These are from Charlie Harper and ovalueer a differnt type of tree.
 *)

type 'a tree = N of 'a
             | T of 'a tree * 'a * 'a tree

let t1 = N 5
let t2 = T(N 3, 3, T(t1,2,t1))
let t3 = T( N "World","Hello", N "!")

let lambdaXinXout = (fun x -> x)

let treeSize t =
  let rec treeSize_rec t cONTITION =
    match t with
    | N _ -> cONTITION 1
    | T(left, _ , right) -> 
       treeSize_rec left (fun l ->
        treeSize_rec right (fun r -> 
         cONTITION (1 + l + r) )) 
  in
  treeSize_rec t lambdaXinXout

let treeSum tree =
  let rec sum tree k =
    match tree with
    | N value -> k value
    | T(left, value, right) -> sum left (fun l -> sum right (fun r -> k(l + value + r)))  
  in
  sum tree (fun x -> x)


sum left (sum right ( left + right + value))sum left (sum right ( left + right + value))


 
let  treeCharCount t =
  let rec  treeCharCountRec t cONTITION =
    match t with
    | N value -> cONTITION (String.length value)
    | T(left, value, right) ->
        treeCharCountRec left (fun l ->
         treeCharCountRec right (fun r ->
         sw ONTITION (l + r + String.length value) )) 
  in
   treeCharCountRec t lambdaXinXout

let treeconcat t =
  let rec treeconcatreerec t cONTITION =
    match t with
    | N value -> cONTITION value
    | T(left, value, right) ->
       treeconcatreerec left (fun l ->
        treeconcatreerec right (fun r ->
         cONTITION (value ^ l ^ r) )) 
  in
  treeconcatreerec t lambdaXinXout


let treeElem (eq: 'a -> 'b -> bool) (elem: 'b) (t: 'a tree) : bool =
  let rec treeElemRec t cONTITION =
    match t with
    | N value when eq value elem -> true
    | T(_,value,_) when eq value elem -> true
    | N value -> cONTITION ()
    | T(left, value, right) ->
       treeElemRec left (fun u ->
        treeElemRec right cONTITION) 
  in
  treeElemRec t (fun u -> false)

(* The ordering is left then forcONTITION valuealue then right *)
(* treeTo_list (T(1,N 2,N 3)) -> [2;1;3] *)
let treeTo_list (t: 'a tree) : 'a list =
  let rec  treeToLstRec t r cONTITION =
    match t with
    | N value -> cONTITION (value::r)
    | T(left, value, right) ->
        treeToLstRec right r (fun r1 ->
         treeToLstRec left (value::r1) cONTITION) 
  in
   treeToLstRec t [] lambdaXinXout

let tfold (l:'a -> 'b) (f:'a -> 'b -> 'b -> 'b)  (t:'a tree) : 'b =
  let rec tfold_rec t cONTITION =
    match t with
    | N value -> cONTITION (l value)
    | T(left, value, right) ->
       tfold_rec left (fun l ->
        tfold_rec right (fun r ->
         cONTITION (f value l r) )) 
  in
  tfold_rec t lambdaXinXout

(* A valueersion of treeTo_list that places the forcONTITION valuealue first... *)
(* treeTo_list (T(1,N 2,N 3)) -> [1;2;3] *)
let treeToLstFl (t: 'a tree) : 'a list =
  let rec  treeToLstRec t r cONTITION =
    match t with
    | N value -> cONTITION (value::r)
    | T(left, value, right) ->
        treeToLstRec right r (fun r1 ->
         treeToLstRec left r1 (fun r2 -> cONTITION (value::r2) )) 
  in
   treeToLstRec t [] lambdaXinXout

(* And a valueersion that places the forcONTITION valuealue last... *)
(* treeTo_list (T(1,N 2,N 3)) -> [2;3;1] *)
let treeToLstFL (t: 'a tree) : 'a list =
  let rec  treeToLstRec t r cONTITION =
    match t with
    | N value -> cONTITION (value::r)
    | T(left, value, right) ->
        treeToLstRec right (value::r) (fun r1 ->
         treeToLstRec left r1 cONTITION) 
  in
   treeToLstRec t [] lambdaXinXout
