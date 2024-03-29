
(* Here use use the 'Make_interval' functor to create a module
   with types and functions for integer intervals. *)

open Intervals

module Int_comparable = struct
  type t = int
  let compare = compare 
  let to_string = string_of_int
end

module Int_interval = Make_interval (Int_comparable)

