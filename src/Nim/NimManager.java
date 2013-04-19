package Nim;

import java.util.*;


public class NimManager {

	private State currentState;
	private List<StateScore> stateValues;
	private List<State> playerOne;
	private List<State> playerTwo;
	private Scanner scanner;
	
	
	public NimManager(){
		scanner = new Scanner(System.in);
		stateValues = new ArrayList<StateScore>();
		populateStateValues();
	}
	
	public void run(){
		displayMenu();
	}
	
	private void populateStateValues() {
		for(int i = 0; i <= 3; i++){
			for(int j = 0; j <= 5; j++){
				for(int k = 0; k <= 7; k++){
					stateValues.add(new StateScore(new State(i, j, k)));
				}
			}
		}
	}
	
	private void displayMenu(){
		boolean running = true;
		do {
			String displayMenuQuestions = "What would you like to do?\n";
			displayMenuQuestions += "1. Play against a computer\n";
			displayMenuQuestions += "2. Computer against a computer\n";
			displayMenuQuestions += "3. Quit\n";
			int choice = GetInputFromUser.getRangedIntegerAnswer(displayMenuQuestions, 1, 3, scanner);
			if(choice == 1){
				playerVsComputer();
			}
			else if(choice == 2){
				String computerChoiceDisplay = "How many times would you like the computers to play?";
				computerVsComputer(GetInputFromUser.getRangedIntegerAnswer(computerChoiceDisplay, 1, Integer.MAX_VALUE, scanner));
			}
			else{
				running = false;
				break;
			}
		} while(running);
	}
	
	private void playerVsComputer(){
		currentState = new State();
		playerOne = new ArrayList<State>();
		playerTwo = new ArrayList<State>();
		State endState = new State(0,0,0);
		System.out.println("To make a move, type the row(1-3) and press enter, then type the amount to take and press enter");
		boolean gameGoing = true;
		do {
			currentState = playerTurn(currentState);
			if(currentState.equals(endState)){
				System.out.println("Computer wins!");
				gameGoing = false;
				subtractValues(playerOne);
				addValues(playerTwo);
				break;
			}
			playerOne.add(currentState);
			currentState = computerTurn(currentState);
			if(currentState.equals(endState)){
				System.out.println("Player wins!");
				gameGoing = false;
				subtractValues(playerTwo);
				addValues(playerOne);
				break;
			}
			playerTwo.add(currentState);
		} while(gameGoing);
		displayMenu();
	}
	
	private void computerVsComputer(int numberOfTimes){
		int compOneScore = 0;
		int compTwoScore = 0;
		for(int i = 0; i < numberOfTimes; i++){
			currentState = new State();
			playerOne = new ArrayList<State>();
			playerTwo = new ArrayList<State>();
			State endState = new State(0,0,0);
			boolean gameGoing = true;
			do {
				currentState = computerTurn(currentState);
				if(currentState.equals(endState)){
					gameGoing = false;
					subtractValues(playerOne);
					addValues(playerTwo);
					compTwoScore++;
					break;
				}
				playerOne.add(currentState);
				currentState = computerTurn(currentState);
				if(currentState.equals(endState)){
					gameGoing = false;
					subtractValues(playerTwo);
					addValues(playerOne);
					compOneScore++;
					break;
				}
				playerTwo.add(currentState);
			} while(gameGoing);
		}
		System.out.println("Computers have finished playing " + numberOfTimes + " times");
		System.out.println("Computer player 1 won " + compOneScore + " times");
		System.out.println("Computer player 2 won " + compTwoScore + " times");
		if(compOneScore > compTwoScore){
			System.out.println("\nComputer player 1 wins!");
		}
		else if(compOneScore < compTwoScore){
			System.out.println("\nComputer player 2 wins!");
		}
		else{
			System.out.println("The computer players tied!");
		}
	}
	
	private State playerTurn(State s){
		System.out.println(s.toString());
		State newState;
		boolean emptyRow = true;
		int row = 0;
		do {
			row = GetInputFromUser.getRangedIntegerAnswer("Row: ", 1, 3, scanner);
			if(s.getRowValue(row) == 0){
				System.out.println("Pick a non empty row");
			}
			else{
				emptyRow = false;
			}
		} while(emptyRow);
		int num = 0;
		num = GetInputFromUser.getRangedIntegerAnswer("Amount: ", 1, s.getRowValue(row), scanner);
		int[] newRows = new int[3];
		newRows[0] = s.getRowValue(1);
		newRows[1] = s.getRowValue(2);
		newRows[2] = s.getRowValue(3);
		newRows[row] -= num;
		newState = new State(newRows);
		return newState;
	}
	
	private State computerTurn(State currentState){
		List<State> possible = returnPossibleStateList(currentState);
		State nextTurn = new State();
		int score = Integer.MIN_VALUE;
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
	
	private void addValues(List<State> stateList) {
		for(State s : stateList){
			for(StateScore ss : stateValues){
				if(ss.getState().equals(s)){
					ss.addScore();
					break;
				}
			}
		}
	}
	
	private void subtractValues(List<State> stateList) {
		for(State s : stateList){
			for(StateScore ss : stateValues){
				if(ss.getState().equals(s)){
					ss.subScore();
					break;
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NimManager nm = new NimManager();
		nm.run();
	}
	
	@Override
	protected void finalize() throws Throwable {
		scanner.close();
	};

}
