(*I use an int pair to represent the problem. I did this because 
the end state needs there to be 2 gallons in the right 1 and that is a
whole number so both jugs are to be represented by  int pair*)

type operation = Fill4GallonJugFromTap
               | Fill3GallonJugFromTap
               | Empty4GallonJugOnGround
               | Empty3GallonJugOnGround
               | Fill4GallonJugFrom3GallonJug
               | Fill3GallonJugFrom4GallonJug
               | Empty4GallonJugInto3GallonJug
               | Empty3GallonJugInto4GallonJug

type jugs_state = ( int * int )  

type state = (operation*jugs_state)

let goal s = s = (Empty3GallonJugOnGround, (2,0))

let jug4_need s =
 match s with
 |(oper, (jug4, jug3)) -> (4 - jug4)

let jug3_need s =
 match s with
 |(oper , (jug4, jug3)) -> (3 - jug3)

let rec is_not_elem set v = not (List.mem v set)

let state_tostr s= 
  let state_tostr' amt =
    match amt with
    | 0 -> " is empty"
    | 1 -> " contains 1 gallon"
    | x -> " contains " ^ string_of_int x ^ " gallons"
  in
  match s with
    |(o,(jug4, jug3)) ->(o,("The 4 gallon jug " ^ state_tostr' jug4 
      ^ ", " ^ "The 3 gallon jug " ^ state_tostr' jug3 ^ "." ))



let newstates s = 
  let empty3_toground s = 
    match s with
    |(oper, (jug4, jug3)) ->
      if (jug3 <> 0) 
      then [(Empty3GallonJugOnGround), (jug4,0) ]
      else []
  in                   
  let empty4_toground s =
    match s with
    |(oper, (jug4, jug3)) ->
      if (jug4 <> 0)
      then [ (Empty4GallonJugOnGround),(0, jug3)]
      else []
  in
  let fill4_tap s =
    match s with
    |(oper, (jug4, jug3)) ->
     if (jug4 < 4)
     then [(Fill4GallonJugFromTap), (4, jug3)]
     else []
  in 
  let fill3_tap s =
    match s with
    |(oper, (jug4, jug3)) ->
      if (jug3 < 3)
      then [(Fill3GallonJugFromTap), (jug4, 3)]
      else []
  in
  let fill4_from3 s =
    match s with
    |(oper, (jug4 , jug3)) -> 
      if ((jug3 <> 0) && (jug4_need s >= 3))
      then [(Fill4GallonJugFrom3GallonJug)
                       , ((jug3 + jug4) , 0)]
      else if ((jug3 <> 0) && (jug4_need s > 0 )&& (jug4_need s < jug3))
      then [(Fill4GallonJugFrom3GallonJug)
              ,((jug4 + jug4_need s), (jug3 - jug4_need s))]
      else []
  in
  let fill3_from4 s =
    match s with
   |(oper, (jug4, jug3)) ->
     if (( jug4 <> 0) && (jug3_need s >= jug4))
     then [(Fill3GallonJugFrom4GallonJug), 
                                 (0,(jug4 + jug3))]
     else if ((jug4 <> 0) && (0 < jug3_need s) && (jug3_need s < jug4))
     then [(Fill3GallonJugFrom4GallonJug)
                           ,((jug4 - jug3_need s), (jug3 + jug3_need s))]
     else []
  in
  let empty4_into3 s =
    match s with
    |(oper, (jug4, jug3)) -> 
      if( ((jug4 + jug3) < 3 ) && (jug4 <> 0) )
      then [ (Empty4GallonJugInto3GallonJug), (0,(jug4 + jug3))]
      else []
  in
  let empty3_into4 s =
    match s with
    |(oper, (jug4, jug3)) -> 
      if (((jug4 + jug3) <  4) && (jug3 <> 0))
      then [(Empty3GallonJugInto4GallonJug),((jug4 + jug3), 0)]
      else []
  in 
     (empty3_toground s @ empty4_toground s @  fill4_from3 s @ fill3_from4 s @
      fill4_tap s @ fill3_tap s @ empty3_into4 s @ empty4_into3 s) 
                            
exception FoundStrState of (operation * string) list

let play () = 
 let rec go_froms state statelist  strstate =
   if goal state
   then raise (FoundStrState strstate)
   else
     let new_valid = List.filter (is_not_elem (statelist)) (newstates state)
     in 
     let recurse newv = 
       go_froms newv ( statelist@ [newv])  (strstate @ [state_tostr (newv)]) 
     in 
     List.iter recurse new_valid

in try  go_froms (Fill4GallonJugFromTap, (0,0)) [] [] ; None
  with FoundStrState str_statelst  -> Some str_statelst


