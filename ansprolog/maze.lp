
#const width = 5.
dim(1..width).

1 { parent(X,Y, 0,-1),
	parent(X,Y, 1, 0),
	parent(X,Y,-1, 0),
	parent(X,Y, 0, 1) } 1 :-
	dim(X), dim(Y), (X,Y) != (1,1).
