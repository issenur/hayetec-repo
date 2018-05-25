type node = N of int

type edge = node*node
type graph = node list * edge list

type color = C of int
type coloring = (node * color) list

exception FoundColoring of coloring

let rec show_colorlst coloring =
  match coloring with
  |[] -> []
  |x::xs ->
    (match x with
    |(node, color) -> [color] @ show_colorlst xs
    )

let rec show_nodelst_color  coloring =
  match coloring with
  |[] -> []
  |x::xs ->
    (match x with
    |(node, color) -> [node] @ show_nodelst_color xs
    )

let show_edgelst_graph grph =
  match grph with
  |(_, edge_list) -> edge_list

let show_nodelst_graph grph =
  match grph with
  |(node_list, _) -> node_list

let make_option options =
  match options with
  |v -> Some v
  |[] -> None

let un_option options =
 match options with
 |Some v -> v
 |None -> []

let rec adjacent_nodelst (n : node) 
                         (graph: (node list * edge list)): node list =
  match n, show_edgelst_graph graph with
  |n ,[] -> []
  |n, (x::xs) ->
    (match n, x  with
     |n, (n1, n2) -> 
       if (n = n2) 
       then ([n1] @ adjacent_nodelst n ((show_nodelst_graph graph), xs))
       else if (n = n1) 
       then ([n2] @ adjacent_nodelst n ((show_nodelst_graph graph), xs))
       else (adjacent_nodelst n ((show_nodelst_graph graph), xs))
     )  


 let rec available_color colorlst =
   match show_colorlst colorlst with
   |[] -> Some(C 1)
   |[C 1] -> Some(C 2)
   |[C 2] -> Some(C 1)
   |[C 3] -> Some(C 1)
   |[C 2 ; C 3] -> Some(C 1)
   |[C 3 ; C 2] -> Some(C 1)
   |[C 1 ; C 3] -> Some(C 2)
   |[C 3 ; C 1] -> Some(C 2)
   |[C 1 ; C 2] -> Some(C 3)
   |[C 2 ; C 1] -> Some(C 3)
   |[C _; C _; C _] -> None
  
 let rec adjacent_coloring node_lst coloring =
   match node_lst, coloring  with
   |[], _  -> []
   |(x::xs), Some(list1) -> (List.filter (fun n -> n = (x, C 1)) list1) 
               @
               (List.filter (fun n -> n = (x, C 2)) list1)
               @ 
               (List.filter (fun n -> n = (x, C 3)) list1)
               @ 
               adjacent_coloring xs coloring
   |(x::xs), None -> []
 
 let color_option grph =                                                 
   let rec helper partial_coloring rest =
   match rest with                                                              
   | [] -> partial_coloring
   | x::xs ->
    (match (available_color(adjacent_coloring 
             (adjacent_nodelst x grph) partial_coloring)
            ) with
    |Some (C 1) -> helper (Some(((un_option (partial_coloring)) @ [x, C 1]))) xs
    |Some (C 2) -> helper (Some(((un_option (partial_coloring)) @ [x, C 2]))) xs
    |Some (C 3) -> helper (Some(((un_option (partial_coloring)) @ [x, C 3]))) xs 
    |None -> None    
    )                                
  in helper None (show_nodelst_graph grph)                         

 let rec adjacent_coloring_x node_lst coloring =
   match node_lst, coloring  with
   |[], _  -> []
   |(x::xs), (list1) -> (List.filter (fun n -> n = (x, C 1)) list1) 
               @
               (List.filter (fun n -> n = (x, C 2)) list1)
               @ 
               (List.filter (fun n -> n = (x, C 3)) list1)
               @ 
               adjacent_coloring_x xs coloring
   |(x::xs), [] -> []


let rec available_color_x colorlst   =
   match show_colorlst colorlst with
   |[] -> Some(C 1)
   |[C 1] -> Some(C 2)
   |[C 2] -> Some(C 1)
   |[C 3] -> Some(C 1)
   |[C 2 ; C 3] -> Some(C 1)
   |[C 3 ; C 2] -> Some(C 1)
   |[C 1 ; C 3] -> Some(C 2)
   |[C 3 ; C 1] -> Some(C 2)
   |[C 1 ; C 2] -> Some(C 3)
   |[C 2 ; C 1] -> Some(C 3)
   |[C _; C _ ; C _] -> None



 let color_exception grph =                                                 
   let rec helper_x partial_coloring rest =
   match rest with                                                              
   | [] -> raise(FoundColoring(partial_coloring))
   | x::xs ->
    (match (available_color_x(adjacent_coloring_x 
             (adjacent_nodelst x grph) partial_coloring)
            ) with
    |Some (C 1) -> helper_x ((partial_coloring) @ [x, C 1]) xs
    |Some (C 2) -> helper_x ((partial_coloring) @ [x, C 2]) xs
    |Some (C 3) -> helper_x ((partial_coloring) @ [x, C 3]) xs 
    |None -> ()
    )                                
  in  helper_x [] (show_nodelst_graph grph) 
             

(*Search space is all the subsets of pairing all the nodes with three color
 values. It has maximum possible outcomes of 3^n  subsets, n being the number
 of nodes. All of the subsets are not correct. 

The steps: First step is I find all the nodes that are connected to the node 
I want to add to my partial coloring. I examine the edgelist to figure out 
what adjacent nodes it has through a function called adjacent coloring. 
I then run that result through a function called availabe color, which reveals
 to me what color I have available to color the new Node. I keep going.
If the available color function returns none, that path is a dead end
and is no longer persued. *)  
