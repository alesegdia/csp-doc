% this style constraint is more appropiate for the maze
horizontal_mismatch(X,Y) :- sprite((X,Y),wall), not sprite((width-X+1,Y),wall).
vertical_mismatch(X,Y) :- sprite((X,Y),wall), not sprite((X,width-Y+1),wall).
:- 1 { horizontal_mismatch(X,Y), vertical_mismatch(X,Y) }.
