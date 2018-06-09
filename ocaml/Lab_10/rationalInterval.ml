open Intervals
  let rec euclid a b = 
    if a = b then a
    else if a < b 
    then euclid a (b-a)
    else euclid (a-b) a

  let  frac_simplify (n, d) = (n/(euclid n d), d/(euclid n d))

module Rational_comparable (* (Comparable with type t = (int*int))*) = struct
   type t = (int*int)
   let to_string i : string = 
     match frac_simplify i with 
       |l1,l2 ->  string_of_int l1 ^ "/" ^ string_of_int l2          

   let compare (l1,l2)  (h1, h2 )  = compare (l1*h2) (h1*l2) 
end

module Rational_interval = Make_interval(Rational_comparable)

(* The following line now works. *)
let i = Rational_interval.create (3,3) (8,4) 
                                          
