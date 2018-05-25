

let rec ands blst = match blst with
 |[] -> true
 |(true::xs) -> ands xs |(false::xs) -> false  
