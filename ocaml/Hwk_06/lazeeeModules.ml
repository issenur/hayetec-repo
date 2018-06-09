module type LazeeSig = sig
  type 'a t
  val delay: (unit -> 'a) -> 'a t
  val demand: 'a t -> 'a  

module Lazee_v1 = struct
  type 'a t = 'a hidden ref
    and  'a hidden = Value of 'a
                   | Thunk of (unit -> 'a)
 
 (*just  makes a hidden ref*)
 let delay (ux) = 
    ref(Thunk ux)
  
  let demand l = 
    force l;
    match !l with
    | Value v -> v
    | Thunk f -> raise (Failure "this should not happen")
   

module Lazee_v2 = struct
  type 'a t = unit -> 'a
  let delay (ux) = ux
  let demand l = l ()


