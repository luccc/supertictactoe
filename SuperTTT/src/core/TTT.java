package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class TTT {
	
	public static Seed Opponent (Seed player) {
		switch (player) {
		case X:
			return Seed.O;
			
		case O:
			return Seed.X;
			
		default:
			return Seed.N;
		}
	}
	
	public static int Point2Field(int x, int y) {
		return y*3 + x;
	}
	
	public static Point Field2Point(int field) {
		return new Point(field%3, field/3);
	}
	
	public static boolean isWon(ISeeded[] board) {
		return false;
	}
	
	public static boolean isWon(ISeeded[] board, int lastmove, Seed player) {
		if (board[lastmove].getState() != player) return false;
		
		boolean won = false;
		
		switch (lastmove) {
		case 0:
			won |= board[1].getState() == player && board[2].getState() == player;
			won |= board[3].getState() == player && board[6].getState() == player;
			won |= board[4].getState() == player && board[8].getState() == player;
			break;
			
		case 1:
			won |= board[0].getState() == player && board[2].getState() == player;
			won |= board[4].getState() == player && board[7].getState() == player;
			break;
			
		case 2:
			won |= board[0].getState() == player && board[1].getState() == player;
			won |= board[5].getState() == player && board[8].getState() == player;
			won |= board[4].getState() == player && board[6].getState() == player;
			break;
			
		case 3:
			won |= board[0].getState() == player && board[6].getState() == player;
			won |= board[4].getState() == player && board[5].getState() == player;
			break;
			
		case 4:
			won |= board[1].getState() == player && board[7].getState() == player;
			won |= board[3].getState() == player && board[5].getState() == player;
			won |= board[0].getState() == player && board[8].getState() == player;
			won |= board[2].getState() == player && board[6].getState() == player;
			break;
			
		case 5:
			won |= board[2].getState() == player && board[8].getState() == player;
			won |= board[3].getState() == player && board[4].getState() == player;
			break;
			
		case 6:
			won |= board[0].getState() == player && board[3].getState() == player;
			won |= board[7].getState() == player && board[8].getState() == player;
			won |= board[4].getState() == player && board[2].getState() == player;
			break;
			
		case 7:
			won |= board[1].getState() == player && board[4].getState() == player;
			won |= board[6].getState() == player && board[8].getState() == player;
			break;
			
		case 8:
			won |= board[6].getState() == player && board[7].getState() == player;
			won |= board[2].getState() == player && board[5].getState() == player;
			won |= board[0].getState() == player && board[4].getState() == player;
			break;
			
		default:
			break;
		}
		
		return won;
	}

	public static void drawState(Graphics g, Rectangle rect, Seed state, Color color, int size) {
		rect.x      += 0.15 * rect.width;
		rect.y      += 0.15 * rect.height;
		rect.width  *= 0.7;
		rect.height *= 0.7;
		
		g.setColor(color);
		
		switch (size) {
		case 1: ((Graphics2D) g).setStroke(new BasicStroke(1f)); break;
		case 2: ((Graphics2D) g).setStroke(new BasicStroke(2f)); break;
		case 3: ((Graphics2D) g).setStroke(new BasicStroke(4f)); break;
		}
		
		switch (state) {
		case X:
			//g.drawLine(rect.x, rect.y, rect.x+rect.width, rect.y+rect.height);
			//g.drawLine(rect.x+rect.width, rect.y, rect.x, rect.y+rect.height);
			g.drawImage(GameView.imgX, rect.x, rect.y, rect.width, rect.height, null);
			break;
			
		case O:
			//g.drawOval(rect.x, rect.y, rect.width, rect.height);
			g.drawImage(GameView.imgO, rect.x, rect.y, rect.width, rect.height, null);
			break;

		default:
			break;
		}
		
		((Graphics2D) g).setStroke(new BasicStroke(1f));
	}
	
	public static void drawBoard(Graphics g, Rectangle rect, Color color, boolean superboard) {
		int x = (int) (rect.x + 0.1 * rect.width);
		int y = (int) (rect.y + 0.1 * rect.height);
		int w = (int) (0.8 * rect.width  / 3);
		int h = (int) (0.8 * rect.height / 3);
		
		int x0 = x+w*0;
		int x1 = x+w*1;
		int x2 = x+w*2;
		int x3 = x+w*3;
		
		int y0 = y+h*0;
		int y1 = y+h*1;
		int y2 = y+h*2;
		int y3 = y+h*3;
		
		int bw=0;
	
		g.setColor(color);
		if (superboard) {
			((Graphics2D)g).setStroke(new BasicStroke(4f));			
		} else {
			((Graphics2D)g).setStroke(new BasicStroke(2f));		
		}
		g.drawLine(x0+bw, y1, x3-bw, y1);
		g.drawLine(x0+bw, y2, x3-bw, y2);
		g.drawLine(x1, y0+bw, x1, y3-bw);
		g.drawLine(x2, y0+bw, x2, y3-bw);
		
		/*int d;
		for (int i=0; i<2; i++) {
			if(i==0) d = -w/40; else d = w/40;
			
			g.drawLine(x0+bw, y1+d, x3-bw, y1+d);
			g.drawLine(x0+bw, y2+d, x3-bw, y2+d);
			g.drawLine(x1+d, y0+bw, x1+d, y3-bw);
			g.drawLine(x2+d, y0+bw, x2+d, y3-bw);
		}*/
		g.setColor(Color.black);
		((Graphics2D)g).setStroke(new BasicStroke(1f));
	}
	
	public static Rectangle[] getSubrects(Rectangle rect) {
		int x = (int) (rect.x + 0.1 * rect.width);
		int y = (int) (rect.y + 0.1 * rect.height);
		int w = (int) (0.8 * rect.width  / 3);
		int h = (int) (0.8 * rect.height / 3);
		
		int x0 = x+w*0;
		int x1 = x+w*1;
		int x2 = x+w*2;
		//int x3 = x+w*3;
		
		int y0 = y+h*0;
		int y1 = y+h*1;
		int y2 = y+h*2;
		//int y3 = y+h*3;
		
		Rectangle[] subrects = new Rectangle[9];
		subrects[0] = new Rectangle(x0,y0,w,h);
		subrects[1] = new Rectangle(x1,y0,w,h);
		subrects[2] = new Rectangle(x2,y0,w,h);
		subrects[3] = new Rectangle(x0,y1,w,h);
		subrects[4] = new Rectangle(x1,y1,w,h);
		subrects[5] = new Rectangle(x2,y1,w,h);
		subrects[6] = new Rectangle(x0,y2,w,h);
		subrects[7] = new Rectangle(x1,y2,w,h);
		subrects[8] = new Rectangle(x2,y2,w,h);
		
		return subrects;
	}

}
