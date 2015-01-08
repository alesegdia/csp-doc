% only one boots
:- not 1 { sprite(T,boots) } 1.

% at least a lake of lava
:- not 1 { sprite(T,lava) }.

:- not reach(T), sprite(T,boots).

step(0,-1 ;; 0,1 ;; 1,0 ;; -1,0).

% you are at the start
at(T) :- start(T).

at((NX,NY)) :- at((X,Y)),
  step(DX,DY),
  NX = X + DX,
  NY = Y + DY,
  reach((NX,NY)).

have_boots :- at(T), sprite(T,boots).

% move it to the playability checker
% in order to reach the exit you should have passed lava
%:- at(F), finish(F), not at(T), sprite(T,lava).
