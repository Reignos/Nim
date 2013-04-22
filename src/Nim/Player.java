package Nim;

import java.util.*;

public interface Player {

	public State turn(State currentState, Scanner scanner);
	
	public State[] getTurnsMade();
	
	public String getName();
}
