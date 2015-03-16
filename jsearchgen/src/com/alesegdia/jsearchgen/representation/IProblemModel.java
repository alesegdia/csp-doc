package com.alesegdia.jsearchgen.representation;

public interface IProblemModel {
	
	public ISolution CreateEmptySolution();
	
	public IRoom CreateEmptyRoom();
	
	public void AttachRoomToSolution(IRoom selected_room, ISolution partial_solution);

}
