
% if it rains or there is a sprinkler near on, it's wet
wet :- rain.
wet :- sprinkler(A, on), near(A, me).

% if it's dry, it's not wet
dry :- not wet.

% a sprinkler can't be turned on while it's raining man
:- sprinkler(A, on), rain.

% a sprinkler can only be in a valid state (on and off)
validstate(on).
validstate(off).
:- sprinkler(A, B), not validstate(B).

% I'm a person
person(me).
person(you).

:- near(A, B), not person(B).

sprinkler(s, on).
near(s, me).

