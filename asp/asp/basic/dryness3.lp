
% if it rains or there is a sprinkler on, it's wet
wet :- rain.
wet :- sprinkler(A), turned(A, on), near(A, me).

% if it's dry, it's not wet
dry :- not wet.

% there is a sprinkler, it's turned on but it's not near me, it should be dry
sprinkler(s).
turned(s,on).
-near(s,me).
