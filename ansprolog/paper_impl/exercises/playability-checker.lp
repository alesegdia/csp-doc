__level_design(sprite(T,Name)) :- sprite(T,Name).

% check if we can get from the finish to the boots cell without passing
% through the lava
%not_lava :- at(T1), finish(T1), not at(T2), sprite(T2,lava).
%:- not_lava.

% start and finish can't be a named sprite
%:- start(P), sprite(P,T).
%:- finish(P), sprite(P,T).

__concept :- boots_puzzle(T,2), sprite(T,lava).
%__concept :-  1{ sprite(T,lava), boots_puzzle(T,2)}.

