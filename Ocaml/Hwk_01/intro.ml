let even n = if n mod 2 = 0 then true else false

let rec euclid a b = if a = b then a
                    else if a < b then euclid a (b-a)
                    else euclid (a-b) a

let frac_simplify (n, d) = (n/(euclid n d), d/(euclid n d))

let rec foldr f lst base =
  match lst with
  | [] -> base
  | x::xs -> f x (foldr f xs base)

let f x x2 = if x > x2
		then x
		else x2

let max lst = match lst with
	|[]-> raise(Failure "Input list must not be empty")
	| x::xs -> foldr f lst 

let rec take n lst = match lst with
	|[] ->  raise(Failure "Input list not valid") 
	|x::xs -> if n > 0 then  x::(take (n-1) xs)
	  else []   
