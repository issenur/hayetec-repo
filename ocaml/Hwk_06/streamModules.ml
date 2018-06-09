open LazeeModules

module type StreamSig = sig
  type 'a lazee
  val delay: (unit -> 'a) -> 'a lazee
  val demand: 'a lazee -> 'a 

  type 'a t = Cons of 'a * 'a t lazee

  val head : 'a t -> 'a 
  val tail : 'a t -> 'a t 
  val take : int -> 'a t -> 'a list 
  val filter : ('a -> bool) -> 'a t -> 'a t
  val map : ('a -> 'b) -> 'a t -> 'b t 
  val zip : ('a -> 'b -> 'c) -> 'a t -> 'b t -> 'c t
end

module Stream (L: LazeeSig) : StreamSig = struct
  type 'a lazee = 'a L.t
  let delay = L.delay
  let demand = L.demand
  type 'a t = Cons of 'a * 'a t lazee
 
  let head strm = 
    match strm with
    |Cons(v, _) -> v
  
  let tail strm = 
    match strm with
    |Cons(_ , tl) -> demand tl

  let rec take n strm = 
    match n, strm with
    |0, _ -> []
    |_, Cons (v, tl) -> v::take (n-1) (demand tl)
  
  let rec filter p strm =
    match strm with
    |Cons (hd, tl) ->
      let rest = delay (fun () -> filter p (demand tl))
      in if p hd
      then Cons (hd, rest)
      else demand rest
  
  let rec map f strm =
    match strm with
    |Cons (hd, tl) ->
      Cons(f hd, delay (fun () -> map f (demand tl)))
  
  let rec zip f strm1 strm2 =
    match strm1 , strm2 with
    |Cons (h_strm1, tl_strm1), Cons (h_strm2, tl_strm2) ->
      Cons (f h_strm1 h_strm2, 
        delay(fun () -> 
          zip f (demand tl_strm1) (demand tl_strm2)))
end

module Stream_Lazy = Stream(Lazee_v1)
module Stream_Slow = Stream(Lazee_v2)
