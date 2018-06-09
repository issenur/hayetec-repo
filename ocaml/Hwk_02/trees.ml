(*Part A: *)

type 'a tree = Leaf of 'a
             | Fork of 'a * 'a tree * 'a tree

let t1 = Leaf 5
let t2 = Fork (3, Leaf 3, Fork (2, t1, t1))
let t3 = Fork ("Hello", Leaf "World", Leaf "!")
let t4 = Fork (7, Fork (5, Leaf 1, Leaf 2), Fork (6, Leaf 3, Leaf 4))

let rec t_size (t:_ tree) =
match t with
  |Leaf _ ->  1
  |Fork(_, left, right) -> 1 + t_size(left) + t_size(right)

let rec t_sum (t:int tree) =
match t with
  |Leaf a -> a
  |Fork(a, left, right) -> a + t_sum(left) + t_sum(right)

let rec t_charcount (t: string tree): int =
match t with
  |Leaf a ->  String.length(a)
  |Fork(a, left, right) -> String.length(a) + t_charcount(left) + 
   t_charcount(right)

let rec t_concat (t: string tree): string =
match t with
  |Leaf a ->  a
  |Fork(a, left, right) -> a ^ t_concat(left) ^ 
   t_concat(right)

(*Part B:*)

let t5 : string option tree = Fork (Some "a", Leaf (Some "b"), Fork (Some "c", Leaf None, Leaf (Some "d"))) 
let t7 = Fork (Some 1, Leaf (Some 2), Fork (Some 3, Leaf None, Leaf None))
let t8 = Fork (Some "a", Leaf (Some "b"), Fork (Some "c", Leaf None, Leaf (Some "d")))

let rec t_opt_size (t:'a option tree): int =
match t with
  |Leaf v ->(match v with 
	|Some _-> 1
	|None -> 0)
  |Fork(e,left,right) ->(match e with
	|Some _ -> 1 + t_opt_size(left) + t_opt_size(right)
	|None -> 0 + t_opt_size(left) + t_opt_size(right))

let rec t_opt_sum (t:'a option tree): int =
match t with
  |Leaf v ->(match v with 
	|Some a -> a
	|None -> 0)
  |Fork(e,left,right) ->(match e with
	|Some a -> a + t_opt_sum(left) + t_opt_sum(right)
	|None -> 0 + t_opt_sum(left) + t_opt_sum(right))

let rec t_opt_charcount (t:'a option tree): int =
match t with
  |Leaf v ->(match v with 
	|Some a -> String.length(a)
	|None -> 0)
  |Fork(e,left,right) ->(match e with
	|Some a -> String.length(a) + t_opt_charcount(left) + t_opt_charcount(right)
	|None -> 0 + t_opt_charcount(left) + t_opt_charcount(right))


let rec t_opt_concat (t:'a option tree): string =
match t with
  |Leaf v ->(match v with 
	|Some a -> a
	|None -> "")
  |Fork(e,left,right) ->(match e with
	|Some a -> a ^ t_opt_concat(left) ^ t_opt_concat(right)
	|None -> "" ^ t_opt_concat(left) ^ t_opt_concat(right))

(*Part C:*)

let rec tfold (l:'a -> 'b) (f:'a -> 'b -> 'b -> 'b)  (t:'a tree) : 'b =
         match t with
         | Leaf v -> l v
         | Fork (v, t1, t2) -> f v (tfold l f t1) (tfold l f t2)

let lsize a = 1
let fsize a left right = 1 + left + right
let tf_size t = tfold lsize fsize t

let lsum a = a
let fsum a left right = a + left + right
let tf_sum t = tfold lsum fsum t 


let lcharcount a = String.length(a)
let fcharcount a left right = String.length(a) + left + right
let tf_charcount t = tfold lcharcount fcharcount t


let lconcat a = a
let fconcat a left right = a ^ left ^ right
let tf_concat t = tfold lconcat fconcat t


let loptsize v = (match v with	
		|Some a -> 1
		|None -> 0)
let foptsize e left right = (match e with
		|Some a -> 1 + left + right
		|None -> 0 + left + right)
let tf_opt_size = tfold loptsize foptsize


let loptsum v = (match v with
		|Some a -> a
		|None -> 0)
let foptsum e left right = (match e with
		|Some a -> a + left + right
		|None -> 0 + left + right)
let tf_opt_sum = tfold loptsum foptsum

let loptcharcount v = (match v with
		      |Some a -> String.length(a)
		      |None -> 0)
let foptcharcount e left right = (match e with
		      |Some a -> String.length(a) + left + right
		      |None -> 0 + left + right)
let tf_opt_charcount = tfold loptcharcount foptcharcount

let loptconcat v = (match v with
		   |Some a -> a
		   |None -> "")
let foptconcat e left right = (match e with
		   |Some a -> a ^ left ^ right
		   |None -> left ^ right)

let tf_opt_concat = tfold loptconcat foptconcat 

(*Part D:*)

type 'a btree = Empty
              | Node of 'a btree * 'a * 'a btree

let t6 = Node (Node (Empty, 3, Empty), 4, Node (Empty, 5, Empty))

let rec bt_insert_by  (compare:'a -> 'a-> int) (a:'a)  (bt:'a btree) : 'a btree = 
match bt with
  |Empty -> Node (Empty, a, Empty)
  |Node(left, n, right) -> if (compare a n) < 0 then Node(bt_insert_by compare a left, n, right)
		       else Node(left, n, bt_insert_by compare a right) 
		  
let rec bt_elem_by (c:'a -> 'b -> bool) (b:'b) (bt:'a btree): bool =
match bt with
  |Empty -> false
  |Node(left, n ,right) -> (c n b) || (bt_elem_by c b left || bt_elem_by c b right)

let rec bt_to_list (abtree:'a btree): 'a list =
match abtree with
  |Empty -> []
  |Node(left, n, right) -> (bt_to_list left)@[n]@(bt_to_list right)


let rec btfold (empty:'b) (f:'b -> 'a -> 'b -> 'b) (bt:'a btree): 'b =
match bt with
  |Empty -> empty
  |Node(left, n, right) -> f (btfold empty f left) n (btfold empty f right)


let element_empty = false 

let btf_elem_by(c: 'a -> 'b -> bool) (b:'b) (bt:'a btree) = 
	let element_node left n right = left || (c n b) || right in 
		btfold element_empty element_node bt


let tolist_empty = []

let btf_to_list (bt:'a btree) = 
	let tolist_node left n right = left @[n]@ right in 
		btfold tolist_empty tolist_node bt

 
(*
   It would be difficult to use btfold to create a btf_insert function because
btfold uses recursion, and recursion works best if there  is symmetry to the 
structure of the function's arguments. The symmetry is much harder to nail down
when an if else conditional branches the structure to the left or right on top
of the binary tree thing with Nodes and Emptys.*)
