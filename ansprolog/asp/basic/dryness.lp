
% if it rains or there is a sprinkler around, it's wet
{rain, sprinkler}.
wet :- rain.
wet :- sprinkler.

% if it's dry, it's not wet
dry :- not wet.

% there is a sprinkler
sprinkler.
