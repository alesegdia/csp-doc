% minimize horizontal links
horizontal(X,Y) :- parent(X,Y,1,0).
horizontal(X,Y) :- parent(X,Y,-1,0).
#minimize{ horizontal(X,Y) }.
