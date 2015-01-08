% states:
% 1 --> initial
% 2 --> 
% 3 --> 

% initial state is 1
boots_puzzle(T,1) :- start(T).

% possible navigation paths
{ boots_puzzle(T2,2) : adj(T1,T2) } :- boots_puzzle(T1,1), sprite(T1,boots).
%{ boots_puzzle(T2,3) : adj(T1,T2) } :- boots_puzzle(T1,2), sprite(T1,lava).
{ boots_puzzle(T2,S) : adj(T1,T2) } :- boots_puzzle(T1,S).

% you can't be in lava before getting the boots
:- sprite(T,lava), boots_puzzle(T,1).
:- sprite(T,wall), boots_puzzle(T,S).

% in order to finish the level we must be in state 3 and in the finish tile
boots_completed :- finish(T),boots_puzzle(T,2).
:- not boots_completed.
