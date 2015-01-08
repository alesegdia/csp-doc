
#const width = 10.
dim(1..width).

%tile(0,1) :- figura(c1).
%tile(0,2) :- figura(c1).

% define ELE figure type
5 { tile(l,F,X,Y),
	tile(l,F,X+1,Y),
	tile(l,F,X,Y+1),
	tile(l,F,X,Y+2),
	tile(l,F,X,Y+3)
	: dim(X) : dim(Y) : dim(X+1) : dim(Y+1) : dim(Y+2) : dim(Y+3) } 5 :- ele(F,X,Y).

% define CUADRADO figure type
4 { tile(c,F,X,Y),
	tile(c,F,X,Y+1),
	tile(c,F,X+1,Y),
	tile(c,F,X+1,Y+1)
	: dim(X) : dim(Y) : dim(X+1) : dim(Y+1) } 4 :- cuadrado(F,X,Y).

% two figures can't overlap
:- tile(FT,A,X,Y), tile(FT,B,X,Y), A != B.
:- tile(A,FI,X,Y), tile(B,FI,X,Y), A != B.
:- tile(A,C,X,Y), tile(B,D,X,Y), A != B.

% number of eles
exist_eles(N-1) :- exist_eles(N), N != 1.
1 { ele(N,X,Y) : dim(X) : dim(Y) } 1 :- exist_eles(N).

% number of cuadrados
exist_cuadrados(N-1) :- exist_cuadrados(N), N != 1.
1 { cuadrado(N,X,Y) : dim(X) : dim(Y) } 1 :- exist_cuadrados(N).

exist_eles(7).
exist_cuadrados(3).
% ele(ele1,0,0).
% ele(ele2,1,1).
% cuadrado(cuad1,1,1).
