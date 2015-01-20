
empty((X,Y)) :- dim(X), dim(Y), not sprite((X,Y), wall).
:- empty((X,Y)), dim(X), dim(Y), not touch((X,Y),1).


