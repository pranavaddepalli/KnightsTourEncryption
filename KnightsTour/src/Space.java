import java.util.*;

public class Space {
	private int moveNum, row, col;
	private boolean visited;
	protected Space board[][];

	public Space(int row, int col, Space[][] board) {
		this.row = row;
		this.col = col;
		moveNum = 0;
		this.board = board;
		visited = false;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void assignMoveNum(int num) {

		moveNum = num;
	}

	public int getMoveNum() {
		return moveNum;
	}

	public void visit() {
		visited = true;
	}

	public boolean wasVisited() {
		return visited;
	}

	public List<Space> getNeighbors() {
		// return a List of the neighboring spaces that can be visited
		List<Space> neighbors = new ArrayList<Space>();
		if (row - 2 >= 0 && col + 1 < board.length) {
			if (board[row - 2][col + 1].isLegal(row, col, 8))
				neighbors.add(board[row - 2][col + 1]);
		}
		if (row - 2 >= 0 && col - 1 >= 0) {
			if (board[row - 2][col - 1].isLegal(row, col, 8))
				neighbors.add(board[row - 2][col - 1]);
		}
		if (row - 1 >= 0 && col + 2 < board.length) {
			if (board[row - 1][col + 2].isLegal(row, col, 8))
				neighbors.add(board[row - 1][col + 2]);
		}
		if (row - 1 >= 0 && col - 2 >= 0) {
			if (board[row - 1][col - 2].isLegal(row, col, 8))
				neighbors.add(board[row - 1][col - 2]);
		}
		if (row + 2 < board.length && col + 1 < board.length) {
			if (board[row + 2][col + 1].isLegal(row, col, 8))
				neighbors.add(board[row + 2][col + 1]);
		}
		if (row + 2 < board.length && col - 1 >= 0) {
			if (board[row + 2][col - 1].isLegal(row, col, 8))
				neighbors.add(board[row + 2][col - 1]);
		}
		if (row + 1 < board.length && col + 2 < board.length) {
			if (board[row + 1][col + 2].isLegal(row, col, 8))
				neighbors.add(board[row + 1][col + 2]);
		}
		if (row + 1 < board.length && col - 2 >= 0) {
			if (board[row + 1][col - 2].isLegal(row, col, 8))
				neighbors.add(board[row + 1][col - 2]);
		}

		return neighbors;
	}

	public boolean isLegal(int startRow, int startCol, int boardSize) {
		if (startRow + 2 < boardSize || startRow - 2 >= 0 || startCol + 2 < boardSize || startCol - 2 >= 0) {
			if (visited)
				return false;
			else if (startRow + 2 == row && startCol + 1 == col)
				return true;
			else if (startRow - 2 == row && startCol + 1 == col)
				return true;
			else if (startRow - 2 == row && startCol - 1 == col)
				return true;
			else if (startRow + 2 == row && startCol - 1 == col)
				return true;
			else if (startRow + 1 == row && startCol - 2 == col)
				return true;
			else if (startRow + 1 == row && startCol + 2 == col)
				return true;
			else if (startRow - 1 == row && startCol + 2 == col)
				return true;
			else if (startRow - 1 == row && startCol - 2 == col)
				return true;
		}
		return false;
	}

	public int compareTo(Space other) {
		if (other.getMoveNum() < getMoveNum())
			return -1;
		else if (other.getMoveNum() > getMoveNum())
			return 1;
		else
			return 0;

	}

}
