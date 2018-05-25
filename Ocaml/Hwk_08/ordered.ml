module type OrderedSig = sig
 type t
 val eq: t -> t -> bool
 val lt: t -> t -> bool
 val leq: t -> t-> bool
 val elem_to_str : t -> string
end

module Int: (OrderedSig with type t = int) = struct
  type t = int
  let eq = (=)
  let lt = (<)
  let leq = (<=)
  let elem_to_str e = string_of_int e
end
