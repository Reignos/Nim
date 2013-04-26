package Nim;

public class StateScore{
	//10 and -10 are magic numbers. Need quick fix. FIXED
	private State state;
	private int score;
	private final int minScore = -10;
	private final int maxScore = 10;
	
	public StateScore(State s){
		state = s;
		score = 0;
	}
	
	public void addScore(){
		if(score < maxScore)
			score++;
	}
	
	public void subScore(){
		if(score > minScore)
			score--;
	}
	
	public State getState(){
		return state;
	}
	
	public int getScore(){
		return score;
	}
}
