
not_exist(S) :- cant_prove(S).
exist(S) :- can_prove(S).

cant_prove(god).
can_prove(asp).

