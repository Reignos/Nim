import java.util.*;


public class Main {

	private class StateScore{
		
		private State state;
		private int score;
		public StateScore(State s){
			state = s;
			score = 0;
		}
		
		public void addScore(){
			score++;
		}
		
		public void subScore(){
			score--;
		}
		
		public State getState(){
			return state;
		}
		
		public int getScore(){
			return score;
		}
	}
	private State currentState;
	private List<StateScore> stateValues;
	private List<State> playerOne;
	private List<State> playerTwo;
	
	public Main(){
		stateValues = new ArrayList<StateScore>();
		populateValues();
		menu();
	}
	
	private void printState(State s){
		for(int i = 0; i < s.getrow1(); i++){
			System.out.print("X");
		}
		System.out.println();
		for(int i = 0; i < s.getrow2(); i++){
			System.out.print("X");
		}
		System.out.println();
		for(int i = 0; i < s.getrow3(); i++){
			System.out.print("X");
		}
		System.out.println();
	}
	
	private void compVsComp(int numberOfTimes){
		int compOneScore = 0;
		int compTwoScore = 0;
		for(int i = 0; i < numberOfTimes; i++){
			currentState = new State();
			playerOne = new ArrayList<State>();
			playerTwo = new ArrayList<State>();
			State endState = new State(0,0,0);
			boolean gameGoing = true;
			while(gameGoing){
				currentState = compTurn(currentState);
				if(currentState.equals(endState)){
					gameGoing = false;
					subValues(playerOne);
					addValues(playerTwo);
					compTwoScore++;
					break;
				}
				playerOne.add(currentState);
				currentState = compTurn(currentState);
				if(currentState.equals(endState)){
					gameGoing = false;
					subValues(playerTwo);
					addValues(playerOne);
					compOneScore++;
					break;
				}
				playerTwo.add(currentState);
			}
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
	
	private void playerVsComp(){
		currentState = new State();
		playerOne = new ArrayList<State>();
		playerTwo = new ArrayList<State>();
		State endState = new State(0,0,0);
		System.out.println("To make a move, type the row(1-3) and press enter, then type the amount to take and press enter");
		boolean gameGoing = true;
		while(gameGoing){
			currentState = playerTurn(currentState);
			if(currentState.equals(endState)){
				System.out.println("Computer wins!");
				gameGoing = false;
				subValues(playerOne);
				addValues(playerTwo);
				break;
			}
			playerOne.add(currentState);
			currentState = compTurn(currentState);
			if(currentState.equals(endState)){
				System.out.println("Player wins!");
				gameGoing = false;
				subValues(playerTwo);
				addValues(playerOne);
				break;
			}
			playerTwo.add(currentState);
		}
		menu();
	}
	
	private void subValues(List<State> stateList) {
		for(State s : stateList){
			for(StateScore ss : stateValues){
				if(ss.getState().equals(s)){
					ss.subScore();
					break;
				}
			}
		}
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

	private State compTurn(State s){
		List<State> possible = returnStateList(s);
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

	private State playerTurn(State s){
		printState(s);
		State newState;
		boolean emptyRow = true;
		int row = 0;
		while(emptyRow){
			row = getAnswer("Row: ", 1, 3);
			if(row == 1){
				if(s.getrow1() == 0){
					System.out.println("Pick a non empty row");
				}
				else{
					emptyRow = false;
				}
			}
			else if(row == 2){
				if(s.getrow2() == 0){
					System.out.println("Pick a non empty row");
				}
				else{
					emptyRow = false;
				}
			}
			else {
				if(s.getrow3() == 0){
					System.out.println("Pick a non empty row");
				}
				else{
					emptyRow = false;
				}
			}
		}
		int num = 0;
		if(row == 1){
			num = getAnswer("Amount: ", 1, s.getrow1());
			newState = new State(s.getrow1() - num, s.getrow2(), s.getrow3());
		}
		else if(row == 2){
			num = getAnswer("Amount: ", 1, s.getrow2());
			newState = new State(s.getrow1(), s.getrow2() - num, s.getrow3());
		}
		else{
			num = getAnswer("Amount: ", 1, s.getrow3());
			newState = new State(s.getrow1(), s.getrow2(), s.getrow3() - num);
		}
		return newState;
	}
	
	private void populateValues() {
		for(int i = 0; i <= 3; i++){
			for(int j = 0; j <= 5; j++){
				for(int k = 0; k <= 7; k++){
					stateValues.add(new StateScore(new State(i, j, k)));
				}
			}
		}
	}
	
	private ArrayList<State> returnStateList(State s) {
		ArrayList<State> listOfStates = new ArrayList<State>();
		for(int i = 0; i <= s.getrow1(); i++){
			State newState = new State(i, s.getrow2(), s.getrow3());
			if(!newState.equals(s)){
				listOfStates.add(newState);
			}
		}
		for(int j = 0; j <= s.getrow2(); j++){
			State newState = new State(s.getrow1(), j, s.getrow3());
			if(!newState.equals(s)){
				listOfStates.add(newState);
			}
		}
		for(int k = 0; k <= s.getrow3(); k++){
			State newState = new State(s.getrow1(), s.getrow2(), k);
			if(!newState.equals(s)){
				listOfStates.add(newState);
			}
		}
		
		return listOfStates;
	}

	private void menu(){
		boolean running = true;
		while(running){
			String display = "What would you like to do?\n";
			display += "1. Play against a computer\n";
			display += "2. Computer against a computer\n";
			display += "3. Quit\n";
			int choice = getAnswer(display, 1, 3);
			if(choice == 1){
				playerVsComp();
			}
			else if(choice == 2){
				String dis = "How many times would you like the computers to play?";
				compVsComp(getAnswer(dis, 1, Integer.MAX_VALUE));
			}
			else{
				running = false;
				break;
			}
		}
	}
	
	private int getAnswer(String s, int low, int high){
		int answer = 0;
		boolean ci = true;
		System.out.print(s);
		while(ci){
			Scanner scan = new Scanner(System.in);
			String input = scan.next();
			try{
				answer = Integer.parseInt(input);
				if(answer >= low && answer <= high){
					ci = false;
				}
				else{
					System.out.println("Option not availiable");
				}
			}
			catch(Exception e){
				System.out.println("Please enter a number");
			}
		}
		return answer;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}

}
