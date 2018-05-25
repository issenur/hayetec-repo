let f n = if n mod 2 = 0 then true else false
let all_evens lst = List.filter f lst

let add1 n = n + 1
let increment_all lst = List.map add1 lst
 
let f x x2 = if x > x2
                then x
                else x2
let max_fold lst = match lst with
	|[]-> raise(Failure "Input list must not be empty")
	| x::xs -> List.fold_left f x xs

let prod_sum lst (lst: int list):int * int = 
	let prod a b = a * b in							
	let add a b = a + b in
	(List.fold add x xs,List.fold prod x xs)
(*Jake has suggested that I get rid of the pattern matching. Also suggested that I only use the List fold only once. *)
(* Yuchen suggested that I use the plus sign to build the function *)
(*Jake suggested that I shoud insert type annotation to prevent confusion *)
