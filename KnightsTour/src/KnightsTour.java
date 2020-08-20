
import java.util.*;

public class KnightsTour {
	protected Space[][] board;
	protected int row, col, moveNumber;

	public KnightsTour(int size, int startRow, int startCol) {
		this.row = startRow;
		this.col = startCol;
		constructBoard(size);
		moveNumber = 0;
		board[row][col].assignMoveNum(moveNumber);
		board[row][col].visit();
		moveNumber++;

	}

	public void constructBoard(int size) {
		board = new Space[size][size];
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				board[r][c] = new Space(r, c, board);
			}
		}
	}

	public void showBoard() {
		System.out.println("\n");

		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				System.out.print(board[r][c].getMoveNum() + "  ");
			}
			System.out.println("\n");
		}
	}

	public Space getSpace(int r, int c) {
		return board[r][c];
	}

	public Space[][] getBoard() {
		return board;
	}

	public boolean isSolved() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (board[r][c].getMoveNum() == 0)
					return false;
			}
		}
		return true;
	}

	public Space findSpaceWithMove(int move) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c].getMoveNum() == move)
					return board[r][c];
			}
		}
		return board[0][0];
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

	public Space spark(List<Space> neighbors) {
		Space next = neighbors.get(0);
		for (Space s : neighbors) {
			if (s.getNeighbors().size() <= next.getNeighbors().size())
				next = s;
		}
		return next;
	}

	public void solve() {
		for (int i = 1; i < Math.pow(board.length, 2); i++) {
			Space next = spark(getNeighbors());
			next.assignMoveNum(moveNumber);
			next.visit();
			row = next.getRow();
			col = next.getCol();
			moveNumber++;
		}
	}
}