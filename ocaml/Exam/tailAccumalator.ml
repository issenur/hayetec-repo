let sum_a lst =
  let rec sumaccum acc lst =
     match lst with
     | [] -> acc
     | x::xs -> sumaccum (acc + x) xs
   in sumaccum 0 lst

(*accumulator*) 
let maxAccum lst =
  let rec maxAccum' acc lst =
    match lst with
    | [] -> acc
    | hd::tl -> 
      if (hd > acc) 
      then (maxAccum' hd tl) 
      else (maxAccum' acc tl)
  in maxAccum' 0 lst

