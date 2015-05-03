% Generado automaticamente
% % % % % % % % % % % % % % % % % % % % % % % % %
% % %       Preguntas del programa
% % % % % % % % % % % % % % % % % % % % % % % % %
:- use_module(library(bounds)).

solucion(Soluc) :-
	Soluc = ['casa1'(X1casa1,Y1casa1,X2casa1,Y2casa1),'casa2'(X1casa2,Y1casa2,X2casa2,Y2casa2)], %% elementos del mapa
	Variables = [X1casa1,Y1casa1,X2casa1,Y2casa1,X1casa2,Y1casa2,X2casa2,Y2casa2],               %% sus variables

	[X1casa1,Y1casa1,X2casa1,Y2casa1] in 1..50,    %% Dimensiones del tablero
	[X1casa2,Y1casa2,X2casa2,Y2casa2] in 1..50,

	X2casa1 #> X1casa1 + 3,  %% anchura de las habitaciones
	Y2casa1 #> Y1casa1 + 10, %% altura de las habitaciones
	X2casa2 #> X1casa2 + 3,
	Y2casa2 #> Y1casa2 + 10,


	%% RESTRICCIONES	
	ec((X1casa1,Y1casa1,X2casa1,Y2casa1),(X1casa2,Y1casa2,X2casa2,Y2casa2)),

	label(Variables),!.
	


	
%--------------------------------------------------
%  7. EC  -> External Conected
%--------------------------------------------------
ec((X1a,Y1a,X2a,Y2a),(X1b,Y1b,X2b,Y2b)) :-
    (Y2b #= Y1a ; Y2a #= Y1b),
     X2b #> X1a+1, X1b #< X2a-1.

ec((X1a,Y1a,X2a,Y2a),(X1b,Y1b,X2b,Y2b)) :-
    (X2b #= X1a ; X2a #= X1b),
     Y2b #> Y1a+1, Y1b #< Y2a-1.


