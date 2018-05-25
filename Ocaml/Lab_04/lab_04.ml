let length lst =
  foldl (fun accum _ -> 1 + accum) 0 lst
list.fold_left
