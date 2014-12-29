#const width = 10.

% what's the meaning of this instruction?
%param("width",width).

dim(1..width).

tile((X,Y)) :- dim(X), dim(Y).

adj((X1,Y1),(X2,Y2)) :- 
  tile((X1,Y1)),
  tile((X2,Y2)),
  #abs(X1-X2) + #abs(Y1-Y2) == 1.

start((1,1)).
finish((width,width)).

% one named sprite at most
0 { sprite(T,wall;gem;altar;lava;boots) } 1 :- tile(T).

% one altar and one gem
:- not 1 { sprite(T,altar) } 1.
:- not 1 { sprite(T,gem) } 1.

% start and finish can't be a named sprite
:- start(P), sprite(P,T).
:- finish(P), sprite(P,T).
