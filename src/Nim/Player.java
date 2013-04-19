package Nim;

import java.util.Scanner;

public interface Player {

	public State turn(State currentState, Scanner scanner);
}
