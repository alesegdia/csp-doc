% states:
% 1 --> initial
% 2 --> after picking up the gem
% 3 --> after putting gem in altar

% initial state is 1
touch(T,1) :- start(T).

% possible navigation paths
{ touch(T2,2) : adj(T1,T2) } :- touch(T1,1), sprite(T1,gem).
{ touch(T2,3) : adj(T1,T2) } :- touch(T1,2), sprite(T1,altar).
{ touch(T2,S) : adj(T1,T2) } :- touch(T1,S).

% you can't be in walls
:- sprite(T,wall), touch(T,S).

% in order to finish the level we must be in state 3 and in the finish tile
completed :- finish(T),touch(T,3).
:- not completed.
