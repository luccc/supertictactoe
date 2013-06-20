import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SuperBoard extends TTT {
	
	public SubBoard[] boards;
	private List<Move> history;

	public SuperBoard() {
		this.boards = new SubBoard[9];
		for (int i=0; i<9; i++) {
			this.boards[i] = new SubBoard();
		}
		this.history = new ArrayList<Move>();
	}
	
	public Move getLastMove() {
		return history.get(history.size()-1);
	}
	
	public boolean isOpen() {
		for (int i=0; i<9; i++) {
			if(boards[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Move> getPossibleMoves() {
		List<Move> list = new ArrayList<Move>();
		
		if(this.history.size() == 0) {
			for (int i=0; i<9; i++) {
				boards[i].addMoves(list, i);
			}
		} else {
			int s = this.history.get(this.history.size()-1).SubMove;
			if (boards[s].isOpen()) {
				boards[s].addMoves(list, s);
			} else {
				for (int i=0; i<9; i++) {
					boards[i].addMoves(list, i);
				}
			}
		}
		
		return list;
	}
	
	public boolean makeMove(Move move, TTT.Type player) {
		boolean result = (true && this.boards[move.SuperMove].makeMove(move.SubMove, player));
		if (result) history.add(move);
		return result;
	}

	public void draw(Graphics g, Rectangle rect) {
		drawBoard(g, rect, boards, false);
		drawState(g, rect);
	}
	
}