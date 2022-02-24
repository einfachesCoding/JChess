package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import utils.Field;
import utils.Figure;
import utils.Moves;
import utils.Player;


public class Listeners implements MouseListener, ActionListener{
	
	Figure choosedFigure;
	int[] choosedFigurePosition = new int[2];
	boolean player1 = true;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int[] i = inField(x, y);
		if(player1) {
			checkKlick(i[0], i[1], Field.p1);
		}else {
			checkKlick(i[0], i[1], Field.p2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Game.placeField();
		Game.placeFigures();
	}
	
	private int[] inField(int xPos,int yPos) {
		int[] i = new int[2];
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(xPos >= Game.xStart + 100*x && xPos <= Game.xStart +100*x + 100) {
					if(yPos >= Game.yStart + 100*y && yPos <= Game.yStart +100*y + 100) {
						i[0] = x;
						i[1] = y;
					}
				}
			}
		}
		return i;
	}
	
	private void checkKlick(int x, int y, Player p) {
		if(!Field.fieldEmpty(x, y) && Field.myFigure(x, y, p)) {
			Game.repaintField();
			choosedFigure = Field.getFigure(x, y);
			choosedFigurePosition[0] = x;
			choosedFigurePosition[1] = y;
			Game.fields[x][y].setBackground(Color.darkGray);
			markFields(choosedFigure, x, y, p);
		}if(choosedFigure != null && !Field.myFigure(x, y, p) && Game.fields[x][y].getBackground() == Color.gray) {
			Game.move(choosedFigurePosition[0], choosedFigurePosition[1], x, y, p);
			Field.moveFigure(choosedFigurePosition[0], choosedFigurePosition[1], x, y);
			Game.repaintField();
			if(player1) {
				player1 = false;
			}else {
				player1 = true;
			}
			choosedFigure.firstTurn = false;
			if(p == Field.p1) {
				if(checkChess(Field.p2)) {
					JOptionPane.showMessageDialog(null, "Schach");
				}
			}else {
				if(checkChess(Field.p1)) {
					JOptionPane.showMessageDialog(null, "Schach");
				}
			}
		}
	}
	
	private boolean checkChess(Player p) {
		int[] kingPos = Field.getPosition(p.King);
		Moves.markCross(kingPos[1], kingPos[0], p, true);
		boolean chess = getChess('C',p);
		if(chess) {
			return true;
		}
		Moves.markedFields.clear();
		Moves.markJump(kingPos[1], kingPos[0], p, true);
		chess = getChess('J',p);
		if(chess) {
			return true;
		}
		Moves.markedFields.clear();
		Moves.markLine(kingPos[1], kingPos[0], p, true);
		chess = getChess('L',p);
		if(chess) {
			return true;
		}
		Moves.markedFields.clear();
		Moves.markSideStep(kingPos[1], kingPos[0], p, true);
		chess = getChess('S',p);
		if(chess) {
			return true;
		}
		Moves.markedFields.clear();
		Game.repaintBackground();
		return false;
	}
	
	private boolean getChess(char c, Player p) {
		for(int nr = 0; nr < Moves.markedFields.size(); nr++) {
			int[] i = Moves.markedFields.get(nr);
			if(!Field.fieldEmpty(i[0], i[1]) && !Field.myFigure(i[0], i[1], p)) {
				Figure f = Field.getFigure(i[0], i[1]);
				if(f.canCross && c == 'C') {
					return true;
				}
				if(f.canJump && c == 'J') {
					return true;
				}
				if(f.canLine && c == 'L') {
					return true;
				}
				if(f.canSideStep && c == 'S') {
					return true;
				}
			}
		}
		return false;
	}
	
	private void markFields(Figure f, int x,int y, Player p) {
		if(f.canCross) {
			Moves.markCross(x,y, p, false);
		}
		if(f.canJump) {
			Moves.markJump(x,y,p, false);
		}
		if(f.canLine) {
			Moves.markLine(x,y,p, false);
		}
		if(f.canSideStep) {
			Moves.markSideStep(x,y,p, false);
		}
		if(f.canStep) {
			Moves.markStep(x,y,p, f.firstTurn);
		}
		if(f.canSurround) {
			Moves.markSurround(x,y,p);
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
