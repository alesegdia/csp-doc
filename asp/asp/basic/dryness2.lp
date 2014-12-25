
% if it rains or there is a sprinkler on, it's wet
{rain, sprinkler}.
wet :- rain.
wet :- sprinkler(on).
wet :- not sprinkler(off).

% if it's dry, it's not wet
dry :- not wet.

% there is a sprinkler on
sprinkler(on).
