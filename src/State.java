
public class State {

	private int row1;
	private int row2;
	private int row3;
	
	public State(){
		row1 = 3;
		row2 = 5;
		row3 = 7;
	}
	
	public State(int r1, int r2, int r3){
		row1 = r1;
		row2 = r2;
		row3 = r3;
	}
	
	public int getRowValue(int r){
		int returnRow = 0;
		if(r == 1){
			returnRow = row1;
		}
		else if(r == 2){
			returnRow = row2;
		}
		else if(r == 3){
			returnRow = row3;
		}
		return returnRow;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		return new State(row1, row2, row3);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (row1 != other.row1)
			return false;
		if (row2 != other.row2)
			return false;
		if (row3 != other.row3)
			return false;
		return true;
	}
	
	
}
