package Nim;


public interface Player {

	public State turn(State currentState);
	
	public State[] getTurnsMade();
	
	public String getName();
}
