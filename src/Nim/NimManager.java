package Nim;

import java.util.*;


public class NimManager {

	private List<StateScore> stateValues;
	private final State endState = new State(0,0,0);
	
	public NimManager(){
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
			int choice = CommunicateWithUser.getRangedIntegerAnswer(displayMenuQuestions, 1, 3);
			if(choice == 1){
				playerVsComputerSetUp();
			}
			else if(choice == 2){
				String computerChoiceDisplay = "How many times would you like the computers to play?";
				computerVsComputerSetUp(CommunicateWithUser.getRangedIntegerAnswer(computerChoiceDisplay, 1, Integer.MAX_VALUE));
			}
			else{
				running = false;
				break;
			}
		} while(running);
	}
	
	private void playerVsComputerSetUp(){
		HumanPlayer human = new HumanPlayer();
		ComputerPlayer computer = new ComputerPlayer(stateValues, "Computer");
		CommunicateWithUser.println("To make a move, type the row(1-3) and press enter, then type the amount to take and press enter");
		Player winner = playGame(human, computer);
		CommunicateWithUser.println(winner.getName() + " won!");
	}
	
	private void computerVsComputerSetUp(int numberOfTimes){
		int compOneScore = 0;
		int compTwoScore = 0;
		for(int i = 0; i < numberOfTimes; i++){
			ComputerPlayer computer1 = new ComputerPlayer(stateValues, "1");
			ComputerPlayer computer2 = new ComputerPlayer(stateValues, "2");
			Random random = new Random();
			Player winner;
			if(random.nextBoolean()){
				winner = playGame(computer1, computer2);
			}
			else{
				winner = playGame(computer2, computer1);
			}
			if(winner.getName().equals("1")){
				compOneScore++;
			}
			else{
				compTwoScore++;
			}
		}
		CommunicateWithUser.println("Computers have finished playing " + numberOfTimes + " times");
		CommunicateWithUser.println("Computer player 1 won " + compOneScore + " times");
		CommunicateWithUser.println("Computer player 2 won " + compTwoScore + " times");
		String toPrintWhoWon = "Computer player";
		if(compOneScore > compTwoScore){
			toPrintWhoWon += " 1 wins!";
		}
		else if(compOneScore < compTwoScore){
			toPrintWhoWon += " 2 wins!";
		}
		else{
			toPrintWhoWon += "s tied!";
		}
		CommunicateWithUser.println(toPrintWhoWon);
	}
	
	private Player playGame(Player playerOne, Player playerTwo){
		State currentState = new State();
		Player winner = new HumanPlayer();
		boolean gameGoing = true;
		do {
			currentState = playerOne.turn(currentState);
			if(currentState.equals(endState)){
				gameGoing = false;
				subtractValues(playerOne.getTurnsMade());
				addValues(playerTwo.getTurnsMade());
				winner = playerTwo;
				break;
			}
			currentState = playerTwo.turn(currentState);
			if(currentState.equals(endState)){
				gameGoing = false;
				subtractValues(playerTwo.getTurnsMade());
				addValues(playerOne.getTurnsMade());
				winner = playerOne;
				break;
			}
		} while(gameGoing);
		return winner;
	}
	
	private void addValues(State[] stateList) {
		for(State s : stateList){
			for(StateScore ss : stateValues){
				if(ss.getState().equals(s)){
					ss.addScore();
					break;
				}
			}
		}
	}
	
	private void subtractValues(State[] stateList) {
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
	
	

}
