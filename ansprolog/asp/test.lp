
#const width = 5.
dim(1..width).

%tile(0,1) :- figura(c1).
%tile(0,2) :- figura(c1).

% define ELE figure type
5 { tile(F,X,Y),
	tile(F,X+1,Y),
	tile(F,X,Y+1),
	tile(F,X,Y+2),
	tile(F,X,Y+3)
	: dim(X) : dim(Y) : dim(X+1) : dim(Y+1) : dim(Y+2) : dim(Y+3) } 5 :- ele(F,X,Y).

% define CUADRADO figure type
4 { tile(F,X,Y),
	tile(F,X,Y+1),
	tile(F,X+1,Y),
	tile(F,X+1,Y+1)
	: dim(X) : dim(Y) : dim(X+1) : dim(Y+1) } 4 :- cuadrado(F,X,Y).

% two figures can't overlap
:- tile(A,X,Y), tile(B,X,Y), A != B.

% number of eles
exist_eles(N-1) :- exist_eles(N), N != 1.
1 { ele(N,X,Y) : dim(X) : dim(Y) } 1 :- exist_eles(N).

% number of cuadrados
exist_cuadrados(N-1) :- exist_cuadrados(N), N != 1.
1 { cuadrado(N,X,Y) : dim(X) : dim(Y) } 1 :- exist_cuadrados(N).


%1 { ele(ele1,X,Y) } 1.
%ele(X,Y,Z) :- exist_eles(X,N).
%ele(N,X,Y) :- exist_eles(N), dim(X), dim(Y).

exist_eles(2).
exist_cuadrados(1).
%ele(ele1,0,0).
%ele(ele2,1,1).
% cuadrado(cuad1,1,1).
