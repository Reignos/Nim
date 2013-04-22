package Nim;

public class State {

	int[] rows;
	
	public State(){
		setRows(3,5,7);
	}
	
	public State(int r1, int r2, int r3){
		setRows(r1, r2, r3);
	}
	
	public State(int[] row){
		setRows(row[0], row[1], row[2]);
	}
	
	public void setRows(int row1, int row2, int row3){
		rows = new int[3];
		rows[0] = row1;
		rows[1] = row2;
		rows[2] = row3;
	}
	
	public int getRowValue(int r){
		return rows[r-1];
	}
	
	public String toString(){
		String returnString = "1: ";
		for(int i = 0; i < getRowValue(1); i++){
			returnString += "X";
		}
		returnString += "\n2: ";
		for(int i = 0; i < getRowValue(2); i++){
			returnString += "X";
		}
		returnString += "\n3: ";
		for(int i = 0; i < getRowValue(3); i++){
			returnString += "X";
		}
		returnString += "\n";
		return returnString;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		return new State(rows[0], rows[1], rows[2]);
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
		if (rows[0] != other.rows[0])
			return false;
		if (rows[1] != other.rows[1])
			return false;
		if (rows[2] != other.rows[2])
			return false;
		return true;
	}	
}
