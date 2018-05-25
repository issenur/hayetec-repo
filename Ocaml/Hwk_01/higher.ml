let eve n =  
  if ((n mod 2) = 0 ) 
  then (true) 
  else (false)

let all_evens lst = List.filter eve (lst)

let add1 n = n + 1

let increment_all lst = List.map add1 lst
 
let great x1 x2 = 
  if (x2 < x1)
  then (x1)
  else (x2)

let max_fold lst = match lst with
  |[]-> raise(Failure "No empty list allowed")
  | x::xs -> List.fold_left great x xs

let prod_sum lst= 
  let prod a b = a * b
  in							
  let add a b = a + b
  in
  match lst with
    |[] -> (0,0)
    |x::xs -> 
      ((List.fold_left add x xs)
        ,(List.fold_left prod x xs))

 
let group_by_3 lst =
  let accum = ([], [], 0)
  in
  let f (sublists, current, size) x =
    if size = 3
    then ( List.rev current :: sublists , [x], 1 )
    else ( sublists, x :: current, size + 1 )
  in
  let (lsts, curr, size) = List.fold_left f accum lst
  in
  List.rev (List.rev curr :: lsts)


let split n lst =
  let accum = ( [], [])
  in
  let f (sublists, current) x =
    if n x
      then ( List.rev current :: sublists , [])
    else ( sublists, x :: current)
  in
  let (lsts, curr) = List.fold_left f accum lst
  in
  List.rev (List.rev curr :: lsts)     
   
