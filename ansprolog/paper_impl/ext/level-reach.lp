reach(T) :- start(T).
{ reach(T2) : adj(T1,T2) } :- reach(T1).
:- sprite(T,wall), reach(T).
:- not reach(T), finish(T).
:- not reach(T), sprite(T,gem).
:- not reach(T), sprite(T,altar).
