(* Adding function values, recursive let expressions, lambda
   expressions and function application.

   This language extends the language in ``expr_let.ml``.

   Eric Van Wyk
 *)
type expr
  = Val of value

  | Add of expr * expr
  | Sub of expr * expr
  | Mul of expr * expr
  | Div of expr * expr

  | Lt of expr * expr
  | Eq of expr * expr
  | And of expr * expr
  | Not of expr

  | Let of string * expr * expr
  | Id of string
  | If of expr * expr * expr

  | Lambda of string * expr
  | App of expr * expr
  | LetRec of string * expr * expr

and value
  = Int of int
  | Bool of bool
  | Ref of value ref
  | Closure of string * expr * environment

and environment = (string * value) list


let rec lookup (n:string) (env:environment) :value =
  match env with
  |[] -> raise (Failure
    ("Name \"" ^ n ^ "\" not in scope"))
  |(n',v)::_ when n' = n -> v
  |_::rest -> lookup n rest

let rec serialize (ex:expr) : string = 
  match ex with
  |Val (Int b) -> "Val (Int " ^ string_of_int b ^")"
  |Val (Bool b) -> "Val (Bool " ^ string_of_bool b ^")"
  |Add (v1, v2) -> "Add (" ^ serialize v1 ^ ", " ^ serialize v2 ^ ")"
  |Sub (v1, v2) -> "Sub (" ^ serialize v1 ^ ", " ^ serialize v2 ^ ")"
  |Mul (v1, v2) -> "Mul (" ^ serialize v1 ^ ", " ^ serialize v2 ^ ")"
  |Div (v1, v2) -> "Div (" ^ serialize v1 ^ ", " ^ serialize v2 ^ ")"
  |Lt (v1, v2) -> "Lt (" ^ serialize v1 ^ ", " ^ serialize v2 ^ ")"
  |Eq (v1, v2) -> "Eq (" ^ serialize v1 ^ ", "^ serialize v2 ^ ")"
  |And(v1, v2) -> "And (" ^ serialize v1 ^ ", " ^ serialize v2 ^ ")"
  |Not (b) -> "Not (" ^ serialize b ^ ")"
  |Let (n, dexpr, body) ->
   "Let (" ^ "\""^ n ^ "\"" ^ ", " ^ serialize dexpr ^ ", "
     ^ serialize body ^ ")"
  |Id s -> "Id " ^ "\"" ^ s ^"\""
  |If (c, t, e) ->
    "If ("^ serialize c ^", " ^ serialize t ^", "^ serialize e ^")"
  |Lambda (n, body) ->
    "Lambda ("^ "\""^ n ^"\"" ^  ", " ^ serialize body ^ ")"
  |App (f, e) -> "App (" ^ serialize f ^ ", " ^ serialize e ^")"
  |LetRec (fname, dexpr, idfname) ->
    "LetRec (" ^ "\""^ fname ^"\"" ^ ", " ^
      serialize dexpr ^ ", " ^ serialize idfname ^ ")"
  |Val(_) -> raise
    (Failure ("Val cannot be serialized Incompatible Type "))

 let rec unparse (ex:expr) :string = 
  match ex with
  |Val (Int b) -> string_of_int b
  |Val (Bool b) -> string_of_bool b   
  |Add (v1, v2) -> "(" ^ unparse v1 ^ " + " ^ unparse v2 ^ ")"
  |Sub (v1, v2) -> "(" ^ unparse v1 ^ " - " ^ unparse v2 ^ ")"
  |Mul (v1, v2) -> "(" ^ unparse v1 ^ " * " ^ unparse v2 ^ ")"
  |Div (v1, v2) -> "(" ^ unparse v1 ^ " / " ^ unparse v2 ^ ")"
  |Lt (v1, v2) -> "(" ^ unparse v1 ^ " < " ^ unparse v2 ^ ")"
  |Eq (v1, v2) -> "(" ^ unparse v1 ^ " = " ^ unparse v2 ^ ")"
  |And(v1, v2) -> "(" ^ unparse v1 ^ " && " ^ unparse v2 ^ ")"
  |Not( b ) -> "not " ^ unparse b
  |Let (n, dexpr, body) ->
    "(let " ^ n ^ " = " ^ unparse dexpr ^ " in "^ unparse body ^ ")"
  |Id n -> n
  |If (c, t, e) ->
    "if " ^ unparse c ^ " then " ^ unparse t ^ ", else " ^ unparse e
  |Lambda (n, dexpr) ->
    "(fun " ^ n ^ " -> " ^ unparse dexpr ^ ")"
  |App (f, v) -> "(" ^ unparse f ^ unparse v ^ ")"
  |LetRec (fname, dexpr, body) ->
    "let rec " ^ fname ^ " = " ^ unparse dexpr ^ " in " ^ unparse body ^ ")"
  |Val(_) -> raise
    (Failure ("Will only unparse integer and Boolean values."))

let rec freevars (e: expr) : string list =
  match e with
  |Val _ -> []
  |Add (e1, e2) -> freevars e1 @ freevars e2
  |Sub (e1, e2) -> freevars e1 @ freevars e2
  |Mul (e1, e2) -> freevars e1 @ freevars e2
  |Div (e1, e2) -> freevars e1 @ freevars e2
  |Lt  (e1, e2) -> freevars e1 @ freevars e2
  |Eq  (e1, e2) -> freevars e1 @ freevars e2
  |And (e1, e2) -> freevars e1 @ freevars e2
  |Not(b) -> freevars b
  |Let (n, dexpr, body) ->
    freevars dexpr @
     (List.filter (fun n' -> n' <> n) (freevars body))
  |Id n -> [n]
  |If (c, t, e) -> freevars c @ freevars t @ freevars e
  |Lambda (n, body) ->
    (List.filter (fun n' -> n' <> n) (freevars body))
  |App (f, e) -> freevars f @ freevars e
  |LetRec (n, dexpr, body) ->
     (List.filter (fun n' -> n' <> n) (freevars body))

let rec eval (env: environment) (e: expr) :value =
  match e with
  |Val v -> v
  |Add (e1, e2) ->
    (match eval env e1 , eval env e2 with
      |Int a, Int b -> Int (a + b)
      |(_) -> raise (Failure("Incompatible type,
        can only Add Ints"))
    )
  |Sub (e1, e2) ->
    (match eval env e1 , eval env e2 with
      |Int a, Int b -> Int (a - b)
      |(_) -> raise (Failure("Incompatible type,
       can only Sub Ints"))
    )
  |Mul (e1, e2) ->
    (match eval env e1 , eval env e2 with
      |Int a, Int b -> Int (a * b)
      |(_) -> raise (Failure("Incompatible type, 
       can only Mul Ints"))
    )
  |Div (e1, e2) ->
    (match eval env e1 , eval env e2 with
      |Int a, Int b -> Int (a / b)
      |(_) -> raise (Failure("Incompatible type,
       can only Div Ints"))
    )
  |Lt (e1, e2) ->
    (match eval env e1 , eval env e2 with
      |Int a, Int b -> Bool (a < b)
      |(_) -> raise (Failure("Incompatible type,
       can only Lt Ints"))
    )
  |Eq (e1, e2) ->
    (match eval env e1 , eval env e2 with
      |Int a, Int b -> Bool (a = b)
      |(_) -> raise (Failure("Incompatible type, 
       can only Eq Ints"))
    )
  |And (e1, e2) ->
    (match eval env e1 , eval env e2 with
      |Bool a, Bool b -> Bool (a && b)
      |(_) -> raise (Failure("Incompatible type, 
       can only And Bools"))
    )
  |Not e1 ->
    (match eval env e1 with
        |(Bool b) -> Bool (not b)
        |_ -> raise (Failure ("Incompatible 
         value on Not"))
    )
  |Let (n, dexpr, body) ->
    let v = eval env dexpr
    in
    eval ((n,v)::env) body
  |Id n -> lookup n env
  |If (c, t, e) ->
    (match eval env c with
      |(Bool true)-> eval env t
      |(Bool false)-> eval env e
      |(_) -> raise (Failure("Incompatible type 
       c must be eval to Bool"))
    )
  |Lambda(n, body) -> Closure (n, body, env)
  |App(f, e) ->
    (let funct = match eval env f with
      |Ref r -> !r
      |a -> a
      in
      (match funct with
        |Closure (fname, body, nenv) ->
          eval ((fname, eval env e)::nenv) body
        | _ -> raise (Failure "cannot apply a non function") )
      )
 |LetRec (fname, dexpr, idfname) ->
    (match dexpr with
      |Lambda _ ->
        let recRef =  ref(Int 999) in
        let c = eval ((fname, Ref recRef)::env) dexpr in
        let () = recRef:= c in
        eval ((fname,c)::env) idfname
      |_ -> raise (Failure 
         "dexpr in LetRec should be a Lambda")
    )

let evaluate e = eval [] e

(* Some sample expressions and their values *)

let e1 = Add (Val (Int 1), Mul (Val (Int 2), Val (Int 3)))
let v1 = evaluate e1

let e2 = Sub (Val (Int 10), Div (e1, Val (Int 2)))
let v2 = evaluate e2

let e3 = Eq (e1, e2)
let e4 = Lt (e1, e2)

let e5 = Not e4

(* ``let y = 5 in let x = y + 5 in x + y'' *)
let e6 = Let ("y",
              Val (Int 5),
              Let ("x",
                   Add (Id "y", Val (Int 5)),
                   Add (Id "x", Id "y")
                  )
             )

let () =
  assert (serialize e1 = "Add (Val (Int 1), Mul (Val (Int 2), Val (Int 3)))");
  assert (serialize e6 =
            "Let (\"y\", Val (Int 5), Let (\"x\", " ^
              "Add (Id \"y\", Val (Int 5)), Add (Id \"x\", Id \"y\")))")


(* ``let x = 3 < 5 in x && let x = 1 + 2 in x = 3 *)
let e7 = Let ("x",
              Lt (Val (Int 3), Val (Int 5)),
              And (Id "x",
                   Let ("x",
                        Add (Val (Int 1), Val (Int 2)),
                        Eq (Id "x", Val (Int 3))
                       )
                  )
             )

let () =
  assert (evaluate e1 = Int 7);
  assert (evaluate e2 = Int 7);
  assert (evaluate e3 = Bool true);
  assert (evaluate e4 = Bool false);
  assert (evaluate e5 = Bool true);
  assert (evaluate e6 = Int 15);
  assert (evaluate e7 = Bool true)

(* increment *)
let inc = Lambda ("n", Add(Id "n", Val (Int 1)))

let add = Lambda ("x",
                  Lambda ("y", Add (Id "x", Id "y"))
                 )
let inc' = App (add, Val (Int 1))

(* The add2 closure *)
let add2app =
  Let ("add2",
       Let ("two", Val (Int 2), Lambda ("x", Add (Id "x", Id "two"))),
       App (Id "add2", Val (Int 4)))

let () =
  assert (evaluate (App (inc, Val (Int 4))) = Int 5);
  assert (evaluate (Add (Val (Int 2), Val (Int 3))) = Int 5);
  assert (evaluate (App (inc', Val (Int 4))) = Int 5);
  assert (evaluate add2app = Int 6)


(* sumToN *)
let sumToN : expr =
    LetRec ("sumToN",
            Lambda ("n",
                    If (Eq (Id "n", Val (Int 0)),
                        Val (Int 0),
                        Add (Id "n",
                             App (Id "sumToN",
                                  Sub (Id "n", Val (Int 1))
                                 )
                            )
                       )
                   ), Id "sumToN")

(* factorial *)
let fact : expr =
    LetRec ("fact",
            Lambda ("n",
                    If (Eq (Id "n", Val (Int 0)),
                        Val (Int 1),
                        Mul (Id "n",
                             App (Id "fact",
                                  Sub (Id "n", Val (Int 1))
                                 )
                            )
                       )
                   ),
            Id "fact"
           )

(* Assert expressions to test our functions. *)
let () =
  assert (evaluate (App (sumToN, Val (Int 4))) = Int 10);
  assert (evaluate (App (sumToN, Val (Int 10))) = Int 55);
  assert (evaluate (App (sumToN, Val (Int 100))) = Int 5050);
  assert (evaluate (App (fact, Val (Int 0))) = Int 1);
  assert (evaluate (App (fact, Val (Int 1))) = Int 1);
  assert (evaluate (App (fact, Val (Int 2))) = Int 2);
  assert (evaluate (App (fact, Val (Int 4))) = Int 24)



(* If utop gets to this point without raising an ``assert`` exception
   then all tests have passed. *)
let () =
  print_endline ("Success! All tests passed.")
