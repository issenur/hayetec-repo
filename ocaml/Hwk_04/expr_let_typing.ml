(* A definition of arithmetic, relational, logical, and let
   expressions and their evaluation.  This language was originally
   defined in ``expr_let.ml``.

   Here we change the type of ``eval`` and introduce static type
   checking of expressions.

   Eric Van Wyk
 *)

type value
  = Int of int
  | Bool of bool

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

Type environment = (string * value) list

type typ =
  | IntType
  | BoolType

type error =
  (* An unbound name error *)
  | UnboundName of string

  (* An incorrect type error.  The expr has a type (the second
     component) but one of the types in the ``typ list`` was
     expected. *)
  | IncorrectType of expr * typ * (typ list)

  | DivisionByZero of expr


type 'a result = OK of 'a
               | Err of error list

let rec lookup (n:string) (env: (string * 'a) list) : 'a result =
  match env with
  | [] -> Err ( [ UnboundName n ] )
  | (n',v) :: rest when n = n' -> OK v
  | _ :: rest -> lookup n rest


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


let rec unparse (ex:expr) :string  = 
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
  


let rec eval (e:expr) (env: environment) : value result =
  match e with
  |Val v -> OK v
  |Add (e1, e2) ->
    (match eval e1 env with
    |OK (Int a) ->
      (match eval e2 env with
        |OK (Int b) -> OK (Int(a + b))
        |OK (Bool b) -> Err[IncorrectType(e2, BoolType, [IntType])]
        |Err b -> Err b 
      )
    |OK (Bool a) -> Err[IncorrectType(e1, BoolType, [IntType])] 
    |Err a -> Err a   
    )
  |Sub (e1, e2) ->
    (match eval e1 env with
    |OK (Int a) ->
      (match eval e2 env with
        |OK (Int b) -> OK (Int(a - b))
        |OK (Bool b) -> Err[IncorrectType(e2, BoolType, [IntType])]
        |Err b -> Err b
      ) 
    |OK (Bool a) -> Err[IncorrectType(e1, BoolType, [IntType])]
    |Err a -> Err a
    )
  |Mul (e1, e2) ->
    (match eval e1 env with
    |OK (Int a) ->
      (match eval e2 env with
        |OK (Int b) -> OK (Int(a * b))
        |OK (Bool b) -> Err[IncorrectType(e2, BoolType, [IntType])]
        |Err b -> Err b
      ) 
    |OK (Bool a) -> Err[IncorrectType(e1, BoolType, [IntType])]
    |Err a -> Err a
    )
  |Div (e1, e2) ->
    (match eval e2 env with
    |OK (Int 0) -> Err[DivisionByZero(Div(e1, e2))]
    |OK (Int a) ->
      (match eval e1 env with
       |OK (Int b) -> OK (Int(b / a))
       |OK (Bool b) -> Err[IncorrectType(e1, BoolType, [IntType])]
       |Err b -> Err b
      )
    |OK (Bool a) -> Err[IncorrectType(e2, BoolType, [IntType])]
    |Err a -> Err a 
    )
  |Eq (e1, e2) ->
    (match eval e1 env with
    |OK (Int a) ->
      (match eval e2 env with
        |OK (Int b) -> OK (Bool(a = b)) 
        |OK (Bool b) -> Err[IncorrectType(e2, BoolType, [IntType])]
        |Err b -> Err b
      )
    |OK (Bool a) ->
      (match eval e2 env with
      |OK (Bool b) -> OK (Bool(a = b))
      |OK (Int b) -> Err[IncorrectType(e2, IntType, [BoolType])] 
      |Err b -> Err b
      )
    |Err a -> Err a
    )
  |Lt (e1, e2) ->
     (match eval e1 env with
     |OK (Int a) ->
       (match eval e2 env with
       |OK (Int b) -> OK (Bool(a < b)) 
       |OK (Bool b) -> Err[IncorrectType(e2, BoolType, [IntType])]
       |Err b -> Err b
       )
     |OK (Bool a) -> Err[IncorrectType(e1, BoolType, [IntType])]
     |Err a -> Err a
     )
  |Not (e) ->
    (match eval e env with
    |OK (Bool a) -> OK (Bool(not a))
    |OK (Int a) -> Err[IncorrectType(e, BoolType, [IntType])]
    |Err a -> Err a
    )
  |Let (n, dexpr, body) ->
    (match eval dexpr env with
    |OK a -> eval body ((n,a)::env) 
    |e -> e
    )
  |Id n -> (lookup n env)
  |And (e1, e2) ->
    (match eval e1 env with
    |OK (Bool a) ->
      (match eval e2 env with
      |OK (Bool b) -> OK (Bool(a && b))
      |OK (Int b) -> Err[IncorrectType(e2, IntType, [BoolType])]
      |Err b -> Err b
      )
    |OK (Int a) -> Err[IncorrectType(e1, IntType, [BoolType])]
    |Err a -> Err a
    )
  

(*  helper function to start evaluation with the empty environment. *)
let evaluate e = eval e []


(* Some sample expressions and their values *)
let e1 = Add (Val (Int 1), Mul (Val (Int 2), Val (Int 3)))
let v1 = eval e1

let e2 = Sub (Val (Int 10), Div (e1, Val (Int 2)))
let v2 = eval e2

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

(* Assert expressions to test the evaluate function. *)

let () =
  assert (evaluate e1 = OK (Int 7));
  assert (evaluate e2 = OK (Int 7));
  assert (evaluate e3 = OK (Bool true));
  assert (evaluate e4 = OK (Bool false));
  assert (evaluate e5 = OK (Bool true));
  assert (evaluate e6 = OK (Int 15));
  assert (evaluate e7 = OK (Bool true)) 


let er1 = Add (Val (Int 1), Mul (Val (Bool true), Val (Int 3)))
let er2 = Eq (Val (Bool true), Val (Int 3))
let er3 = Eq (e1, e4)

let er4 = Let ("y",
               Val (Int 5),
               And (Val (Bool true), Id "y")
              )

let er5 = And (Val (Bool true), Id "y")

let er6 = Let ("y",
               Val (Int 0),
               Div (Val (Int 5), Id "y")
              )

let er7 = Let ("x",
              Add (Val (Int 5), Val (Bool true)),
              Add (Id "x", Val (Int 5))
              )

let has_eval_errors (e:expr) : bool =
  match evaluate e with
  | OK _ -> false
  | Err _ -> true

let () =
  assert (has_eval_errors er1);
  assert (has_eval_errors er2);
  assert (has_eval_errors er3);
  assert (has_eval_errors er4);
  assert (has_eval_errors er5);
  assert (has_eval_errors er6);
  assert (has_eval_errors er7)


(* To check the type correctness of expressions by infering their
   type, we use the following data types. *)

type context = (string * typ) list

let rec check (e:expr) (ctxt:context) : typ result =
  match e with
  |Val (Int _) -> OK IntType
  |Val (Bool _) -> OK BoolType
  |Add (e1, e2) ->
    (match check e1 ctxt with
    |OK IntType ->
      (match check e2 ctxt with
      |OK IntType -> OK IntType
      |OK BoolType -> Err[IncorrectType(e2, BoolType, [IntType])]
      |Err b -> Err b
      )
    |OK BoolType ->
      (match check e2 ctxt with
      |OK BoolType -> 
        Err[(IncorrectType(e1, BoolType,[IntType]));(IncorrectType(e2,BoolType,[IntType]))]
      |OK IntType -> Err[(IncorrectType(e1, BoolType,[IntType]))]
      |Err b -> Err b
      ) 
    |Err a -> Err a
    ) 
  |Sub (e1, e2) ->
    (match check e1 ctxt with
    |OK IntType ->
      (match check e2 ctxt with
      |OK IntType -> OK IntType
      |OK BoolType -> Err[IncorrectType(e2, BoolType, [IntType])]
      |Err b -> Err b
      )
    |OK BoolType ->
      (match check e2 ctxt with
      |OK BoolType -> 
        Err[(IncorrectType(e1, BoolType,[IntType]));(IncorrectType(e2,BoolType,[IntType]))]
      |OK IntType -> Err[(IncorrectType(e1, BoolType,[IntType]))]
      |Err b -> Err b
      ) 
    |Err a -> Err a
    ) 
  |Mul (e1, e2) ->
    (match check e1 ctxt with
    |OK IntType ->
      (match check e2 ctxt with
      |OK IntType -> OK IntType
      |OK BoolType -> Err[IncorrectType(e2, BoolType, [IntType])]
      |Err b -> Err b
      )
    |OK BoolType ->
      (match check e2 ctxt with
      |OK BoolType -> 
        Err[(IncorrectType(e1, BoolType,[IntType]));(IncorrectType(e2,BoolType,[IntType]))]
      |OK IntType -> Err[(IncorrectType(e1, BoolType,[IntType]))]
      |Err b -> Err b
      ) 
    |Err a -> Err a
    ) 
  |Div (e1, e2) ->
    (match check e1 ctxt with
    |OK IntType ->
      (match check e2 ctxt with
      |OK IntType -> OK IntType
      |OK BoolType -> Err[IncorrectType(e2, BoolType, [IntType])]
      |Err b -> Err b
      )
    |OK BoolType ->
      (match check e2 ctxt with
      |OK BoolType -> 
        Err[(IncorrectType(e1, BoolType,[IntType]));(IncorrectType(e2,BoolType,[IntType]))]
      |OK IntType -> Err[(IncorrectType(e1, BoolType,[IntType]))]
      |Err b -> Err b
      ) 
    |Err a -> Err a
    ) 
  |Lt (e1, e2) ->
    (match check e1 ctxt with
    |OK IntType ->
      (match check e2 ctxt with
      |OK IntType -> OK BoolType
      |OK BoolType -> Err[IncorrectType(e2, BoolType, [IntType])]
      |Err b -> Err b
      )
    |OK BoolType ->
      (match check e2 ctxt with
      |OK IntType -> Err[IncorrectType(e1, BoolType,[IntType])]
      |OK BoolType -> 
        Err[(IncorrectType(e1, BoolType,[IntType]));(IncorrectType(e2,BoolType,[IntType]))]
      |Err b -> Err b
      ) 
    |Err a -> Err a
    ) 
  |Eq (e1, e2) ->
    (match check e1 ctxt with
    |OK IntType ->
      (match check e2 ctxt with
      |OK IntType -> OK BoolType
      |OK BoolType -> Err[IncorrectType(e2, BoolType, [IntType])]
      |Err b -> Err b
      )
    |OK BoolType ->
      (match check e2 ctxt with
      |OK BoolType -> 
        Err[(IncorrectType(e1, BoolType,[IntType]));(IncorrectType(e2,BoolType,[IntType]))]
      |OK IntType -> Err[(IncorrectType(e1, BoolType,[IntType]))]
      |Err b -> Err b
      ) 
    |Err a -> Err a
    ) 
  |And (e1, e2) ->
    (match check e1 ctxt with
    |OK BoolType ->
      (match check e2 ctxt with
      |OK BoolType -> OK BoolType
      |OK IntType -> Err[IncorrectType(e2, IntType, [BoolType])]
      |Err b -> Err b
      )
    |OK IntType ->
      (match check e2 ctxt with
      |OK IntType -> 
        Err[(IncorrectType(e1, IntType,[BoolType]));(IncorrectType(e2,IntType,[BoolType]))]
      |OK BoolType -> Err[(IncorrectType(e1, IntType,[BoolType]))]
      |Err b -> Err b
      ) 
    |Err a -> Err a
    )  
  |Id e -> Err[UnboundName(e)] 
  |Not e -> 
     (match check e ctxt with
      |OK IntType -> Err[(IncorrectType(e, BoolType, [IntType]))]
      |OK BoolType -> OK BoolType
     )
   |Let(n, dexpr, body) ->
      OK IntType


let has_type_errors (e:expr) : bool =
  match check e [] with
  | OK _ -> false
  | Err _ -> true



