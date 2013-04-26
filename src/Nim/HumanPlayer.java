package Nim;

import java.util.*;

public class HumanPlayer implements Player {
	
	private List<State> turnsMade;
	
	public HumanPlayer(){
		turnsMade = new ArrayList<State>();
	}
	
	@Override
	public State turn(State currentState) {
		System.out.println(currentState.toString());
		State nextState;
		boolean emptyRow = true;
		int row = 0;
		do {
			row = CommunicateWithUser.getRangedIntegerAnswer("Row: ", 1, 3);
			if(currentState.getRowValue(row) == 0){
				CommunicateWithUser.println("Pick a non empty row");
			}
			else{
				emptyRow = false;
			}
		} while(emptyRow);
		int num = 0;
		num = CommunicateWithUser.getRangedIntegerAnswer("Amount: ", 1, currentState.getRowValue(row));
		int[] newRows = new int[3];
		newRows[0] = currentState.getRowValue(1);
		newRows[1] = currentState.getRowValue(2);
		newRows[2] = currentState.getRowValue(3);
		newRows[row-1] -= num;
		nextState = new State(newRows);
		turnsMade.add(nextState);
		return nextState;
	}

	@Override
	public State[] getTurnsMade() {
		State[] array = new State[turnsMade.size()];
		int count = 0;
		for(State s : turnsMade){
			array[count] = s;
			count++;
		}
		return array;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Human";
	}

}
