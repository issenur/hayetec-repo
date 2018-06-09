open StreamModules

module type Hwk5Sig = sig
  type 'a stream
  val take: int -> 'a stream -> 'a list
  val head: 'a stream -> 'a
  val zip: ('a -> 'b -> 'c) -> 'a stream -> 'b stream -> 'c stream

  val from: int -> int stream
  val nats: int stream
  val cubes_from: int -> int stream
  val cubes_from_zip: int -> int stream
  val cubes_from_map: int -> int stream
  val drop: int -> 'a stream -> 'a stream
  val drop_until: ('a -> bool) -> 'a stream -> 'a stream
  val sum_positive_prefix: int stream -> int
  val primes: int stream
end

module Hwk5(S: StreamSig) : Hwk5Sig = struct
  let take = S.take
  let head = S.head
  let zip = S.zip
  let demand = S.demand
  let delay = S.delay
  let head = S.head
  let tail = S.tail
  let filter = S.filter
  type 'a stream = 'a S.t
  let rec from n = S.Cons(n, delay(fun () -> from (n + 1)))
  let nats = from 1
  let cube n = (n*n*n)
  let rec cubes_from n = S.Cons(cube(n), delay (fun () -> cubes_from(n+1))) 
  let rec cubes_from_zip n =
    let zipper = zip ( * )  (from n) (from n)
    in 
    zip ( * ) zipper (from n)

  let rec cubes_from_map n = 
    S.map (fun x -> x*x*x) (from n)

  let rec drop n strm =
    match n, strm with
    |0, S.Cons(hd, tl) -> S.Cons(hd, tl)
    |n, S.Cons(hd, tl) -> drop (n-1) (tail strm)

  let rec drop_until f_bool strm =
    match f_bool, strm with
    |f_bool, S.Cons(hd, tl) ->
      (match f_bool hd with
      |true -> S.Cons(hd, tl)
      |false -> drop_until f_bool (tail strm)
      )
 
  let rec foldr f strm  =
    match strm with
    |S.Cons(hd, tl) -> f hd (delay(fun () -> (foldr f (tail strm))))


  let rec and_fold f_bool strm =
    match f_bool, strm with
    |f_bool, S.Cons(hd, tl) ->
      (match f_bool hd with
      |true -> and_fold (f_bool) (tail strm) 
      |false -> false
      ) 

  let sum hd lstrm =
    if ( hd > 0)
    then (hd + demand(lstrm))
    else (0)

  let rec sum_positive_prefix strm = foldr sum strm   
    let sift strm n = 
    let f x = (x mod n <> 0) 
    in  
    filter f strm     
  
  let rec sieve strm  =  
    let newstrm =  S.Cons((head(strm)), delay((fun () -> sift  (tail(strm)) (head(strm)))))
    in 
    match newstrm with
     |S.Cons(hd, tl) ->
       S.Cons(hd, delay(fun () -> sieve (demand(tl))))

  let primes = sieve (from 2) 
 
end
