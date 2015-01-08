% make sure the exit is further than height + width - 1
% let's assume the map is squared
#const length = width * 2.
dist(T,0) :- start(T).
dist(T2,D+1) :- dist(T1,D), D < length,
		adj(T1,T2), reach(T2).
too_close :- finish(T), dist(T,D).
:- too_close.
