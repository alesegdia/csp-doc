% at least half of the map are walls
:- not (width*width)/2 { sprite(T,wall) }.

% altars have a clear area around them
0 { sprite(T3,wall) : adj(T1,T2) : adj(T2,T3) } 0 :- sprite(T1,altar).

% do not place altars in any corner
:- sprite(T1,altar), not 4 { adj(T1,T2) }.

% no isolated walls
2 { sprite(T2,wall) : adj(T1,T2) } :- sprite(T1,wall).

% gems have at least three walls sorrounding it
3 { sprite(T2,wall) : adj(T1,T2) } :- sprite(T1,gem).
