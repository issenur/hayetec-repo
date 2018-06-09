
let read_file (file_name: string) : char list =
  let ic = open_in file_name
  in
  let rec read_chars ic =
    try
      let next_char = input_char ic
      in next_char :: read_chars ic
    with
      _ -> []
  in read_chars ic


let implode (cs: char list) : string =
  String.concat "" (List.map  (String.make 1) cs)

let split f lst =
  let accum = ( [], [])
  in

  let g (sublists, current) x =
    if f x
      then ( List.rev current :: sublists , [])
    else ( sublists, x :: current)
  in
  let (lsts, curr) = List.fold_left g accum lst
  in
  List.rev (List.rev curr :: lsts)

let d1 = "../../public-class-repo/Homework/Files/words-small.txt"
let d2 = "../../public-class-repo/Homework/Files/words-google-10000.txt"

let answers file =
  let c1 = read_file file
  in
  let c2 = split 
   (fun n ->  n = '\n' 
     || n = ' ') c1
  in
  let c3 = List.map implode c2
  in
  let last = 
   List.filter 
   (fun n -> String.length n = 6) c3
  in
  List.filter 
    (fun n -> String.length 
    (String.sub n 1 4) = 4) last

let pretty_answers comp = 
    List.map (fun n ->
    (String.sub n 1 4, n)) comp
