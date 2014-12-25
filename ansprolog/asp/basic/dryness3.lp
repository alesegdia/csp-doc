
% if it rains or there is a sprinkler on, it's wet
wet :- rain.
wet :- sprinkler(A), turned(A, on), near(A, me).

% if it's dry, it's not wet
dry :- not wet.

% a sprinkler can't be turned on while it's raining man
:- sprinkler(A), rain.

% a sprinkler can only be on and off
validstate(on).
validstate(off).
:- turned(A,B), not validstate(B).

% a sprinkler must be in a valid state
:- sprinkler(A), not validstate(B), turned(A,B).

sprinkler(s).
turned(s,on).
near(s,me).

