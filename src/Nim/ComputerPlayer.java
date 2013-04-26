package Nim;

import java.util.*;

public class ComputerPlayer implements Player {
	
	private List<StateScore> stateValues;
	private List<State> turnsMade;
	private String name;
	
	public ComputerPlayer(List<StateScore> stateValues, String name){
		this.stateValues = stateValues;
		this.name = name;
		turnsMade = new ArrayList<State>();
	}
	
	@Override
	public State turn(State currentState) {
		List<State> possible = returnPossibleStateList(currentState);
		State nextTurn = new State();
		int score = -11;
		for(State p : possible){
			for(StateScore values : stateValues){
				if(p.equals(values.getState())){
					if(values.getScore() > score){
						score = values.getScore();
						nextTurn = p;
						break;
					}
					else if(values.getScore() == score){
						score = values.getScore();
						Random r = new Random();
						if(r.nextBoolean()){
							nextTurn = p;
						}
						break;
					}
				}
			}
		}
		turnsMade.add(nextTurn);
		return nextTurn;
	}
	
	private ArrayList<State> returnPossibleStateList(State s) {
		ArrayList<State> listOfStates = new ArrayList<State>();
		for(int i = 0; i <= s.getRowValue(1); i++){
			State newState = new State(i, s.getRowValue(2), s.getRowValue(3));
			if(!newState.equals(s)){
				listOfStates.add(newState);
			}
		}
		for(int j = 0; j <= s.getRowValue(2); j++){
			State newState = new State(s.getRowValue(1), j, s.getRowValue(3));
			if(!newState.equals(s)){
				listOfStates.add(newState);
			}
		}
		for(int k = 0; k <= s.getRowValue(3); k++){
			State newState = new State(s.getRowValue(1), s.getRowValue(2), k);
			if(!newState.equals(s)){
				listOfStates.add(newState);
			}
		}
		
		return listOfStates;
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
		return name;
	}

}
