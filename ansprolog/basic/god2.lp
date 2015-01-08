
concept(god).
concept(asp).

exist(S) :- concept(S), proof(S).
not_exist(S) :- concept(S), not proof(S).

proof(asp).
