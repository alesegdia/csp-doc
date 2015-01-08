% adjacency and tiles definition for all levels

tile((X,Y)) :- dim(X), dim(Y).

adj((X1,Y1),(X2,Y2)) :- 
  tile((X1,Y1)),
  tile((X2,Y2)),
  #abs(X1-X2) + #abs(Y1-Y2) == 1.

% we need the constants from level-data in order to construct
% the start and end cell
start((x0,y0)).
finish((xF,yF)).
