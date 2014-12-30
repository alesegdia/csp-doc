#const t_max = 35.
#const min_solution = 20.
#const max_solution = 35.
#const size = 6.

dim(1..size).

time(0..t_max).

% available colors
color(red;yellow;green;cyan;blue;magenta).

% used for determining transitions
next(red,yellow).
next(yellow,green).
next(green,cyan).
next(cyan,blue).
next(blue,magenta).
next(magenta,red).
% color transitions
ok(C,C) :- color(C).
ok(C1,C2) :- next(C1,C2).
ok(C1,C2) :- next(C2,C1).

% neighboring
adjacent(X,Y,X+1,Y) :- dim(X;Y;X+1).
adjacent(X,Y,X-1,Y) :- dim(X;Y;X-1).
adjacent(X,Y,X,Y+1) :- dim(X;Y;Y+1).
adjacent(X,Y,X,Y-1) :- dim(X;Y;Y-1).

% allowed steps
passable(X,Y,NX,NY) :- 
	adjacent(X,Y,NX,NY),
	cell(C1,X,Y),
	cell(C2,NX,NY),
	ok(C1,C2).	

% only one color per cell
1 {cell(C,X,Y) : color(C)} 1 :- dim(X;Y).

% only one start and finish cell
1 { start(X,Y) : dim(X;Y) } 1.
1 { finish(X,Y) : dim(X;Y) } 1.
% both start and finish must be different
:- start(X,Y), finish(X,Y).

% set the player position at the starting cell
player_at(0,X,Y) :- start(X,Y).
player_at(T,NX,NY) :-
	time(T),
	player_at(T-1,X,Y),
	passable(X,Y,NX,NY),
	0 { player_at(0..T-1,NX,NY) } 0.

% Steps completion
victory_at(T) :- finish(X,Y), player_at(T,X,Y).
% shortest path constraints
:- victory_at(T), T < min_solution.
:- victory_at(T), T > max_solution.

% maze generation completion
completed :- victory_at(T).
:- not completed.

%%%%% Extra rules for debug/visualization purposes %%%%%%%
tile_grid(size,size).

tile_char(X,Y,T#mod10) :- 
	T > 0,
	player_at(T,X,Y),
	not start(X,Y),
	not finish(X,Y).

tile_char(X,Y,s) :- start(X,Y).

tile_char(X,Y,f) :- finish(X,Y).

tile_color(X,Y,C) :- cell(C,X,Y).
