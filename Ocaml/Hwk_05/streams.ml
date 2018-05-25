(* The code below is from Professor Eric Van Wyk. *)

(* Types and functions for lazy values *)

type 'a lazee = 'a hidden ref

 and 'a hidden = Value of 'a
               | Thunk of (unit -> 'a)

let delay (unit_to_x: unit -> 'a) : 'a lazee = ref (Thunk unit_to_x)

let force (l: 'a lazee) : unit = match !l with
  | Value _ -> ()
  | Thunk f -> l := Value (f ())

let rec demand (l: 'a lazee) : 'a =
   force l;
  match !l with
  | Value v -> v
  | Thunk f -> raise (Failure "this should not happen")

(* Streams, using lazy values *)
type 'a stream = Cons of 'a * 'a stream lazee


let rec take (n:int) (s: 'a stream):('a list) =
  match n, s with
   |0, _ -> []
   |_, Cons (v, tl)-> v :: take (n-1) (demand (tl))

let rec zip (f: 'a -> 'b -> 'c) (strm1: 'a stream) (strm2: 'b stream) : 'c stream =
  (match strm1, strm2 with
  | Cons (h_strm1, tl_strm1), Cons (h_strm2, tl_strm2) ->
     Cons (f h_strm1 h_strm2, delay (fun () -> zip f (demand tl_strm1) (demand tl_strm2) ) )
  )

let rec from n = Cons(n, delay(fun () -> from (n + 1)))

let nats = from 1

let head (s: 'a stream) : 'a = match s with
  | Cons (v, _) -> v

let tail (s: 'a stream) : 'a stream = match s with
  | Cons (_, tl) -> demand tl

let rec take (n:int) (s : 'a stream) : ('a list) =
 match n, s with
 | 0, _ -> []
 | _, Cons (v, tl) -> v :: take (n-1) (demand tl)

let rec map (f: 'a -> 'b) (s: 'a stream) : 'b stream =
  match s with
  |Cons (hd, tl) ->
    Cons (f hd, delay (fun () -> map f (demand tl)))


let rec filter (p: 'a -> bool) (s: 'a stream) : 'a stream =                     
     match s with                                                                  
     |Cons (hd, tl) ->                                                            
       let rest = delay (fun () -> filter p (demand tl) )                         
       in if p hd                                                                 
       then Cons (hd, rest)                                                    
       else demand rest                                                                                                           


(* The code below is from ISSE NUR *)
(**PART 3.1**)
let cube n = (n * n * n)

(* This code is based of Eric Van Wyks from funtion*)
let rec cubes_from n =
  Cons(cube(n), delay (fun () -> cubes_from (n+1)))


(*This zip function was given to us from Professor Eric*)
(**PART 3.2**)
let cubes_from_zip n =
  let zipper = zip ( * ) (from n) (from n)
  in
  zip ( * ) zipper (from n)



(**PART 3.3**)
(*This map function was imported from lazy.ml of Eric Van Wyks*)
let cubes_from_map n = 
  map (fun x -> x*x*x) (from n)



(**PART 3.4**)
let rec drop n strm = 
 match n, strm with
 |0, Cons(hd, tl) -> Cons(hd, tl) 
 |n, Cons(hd, tl) -> drop (n-1) (tail strm)



(**PART 3.5**)
let rec drop_until f_bool strm =
  match f_bool, strm with
  |f_bool, Cons(hd, tl) -> 
    (match f_bool hd with 
    |true -> Cons(hd, tl)
    |false -> drop_until f_bool (tail strm)
    )


 let ns : int stream = zip ( - ) (from 1000) (cubes_from 1)
(**PART 3.6**)
(*foldr takes a two inputs a function and a 'a stream.
It then takes the hd of the stream and the 'b lazee recursive call
on the tail*)
let rec foldr (f: 'a-> 'b lazee -> 'b) (strm:'a stream) :'b =
  match strm with
  |Cons(hd, tl) -> f hd (delay(fun () -> (foldr f (tail strm))))


let rec and_fold f_bool strm =
  match f_bool, strm with
  |f_bool, Cons(hd, tl) ->
   (match f_bool hd with
   |true -> and_fold (f_bool) (tail strm) 
   |false -> false
   ) 

let sum (hd:'a)  (lstrm:'b lazee) :'b =
    if ( hd > 0)
    then (hd + demand(lstrm))
    else (0)

let rec sum_positive_prefix strm = foldr sum strm 

(**PART 3.7**)                                                                            

let sift strm n = 
  let f x = (x mod n <> 0) 
  in  
  filter f strm     
  
let rec sieve (strm:int stream): int stream =  
  let newstrm =  Cons((head(strm)), delay((fun () -> sift  (tail(strm)) (head(strm)))))
  in 
  match newstrm with
   |Cons(hd, tl) ->
     Cons(hd, delay(fun () -> sieve (demand(tl))))


