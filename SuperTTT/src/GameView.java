import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class GameView extends JPanel {
	
	public Color colorDefault  = Color.black;
	public Color colorActive   = Color.red;
	public Color colorInactive = Color.gray;
	public Color colorLastMove = Color.blue;
	
	List<HumanPlayer> players = new ArrayList<HumanPlayer>();
	Game game;
	
	public void setGame(Game g){
		this.game = g;
	}
	
	public GameView() {
		//this.board = board;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				int x=arg0.getX();
				int y=arg0.getY();
				x-=50;
				y-=50;
				x/=(400/9);
				y/=(400/9);
				for (HumanPlayer p: players){
					int superx=x/3;
					int supery=y/3;
					int subx = x-superx*3;
					int suby = y-supery*3;
					Move m = new Move(superx+3*supery, subx+3*suby);
					synchronized(p){	
						p.setMove(m);
						p.notify();
					}	
				}
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (game!=null){
			game.drawBoard(g, new Rectangle(0,0,500,500));			
		}
	}

	public void registerHumanPlayer(HumanPlayer humanPlayer) {
		players.add(humanPlayer);
	}

}