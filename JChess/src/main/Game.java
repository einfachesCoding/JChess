package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Game extends JFrame implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Figure choosedFigure = null;
	int[] choosedFigurePosition = new int[2];
	JLabel[][] fields = new JLabel[8][8];
	int xStart, yStart;
	JPanel Panel;
	JButton Button = new JButton("Starten");
	Player p1 = new Player(new Color(226, 196, 126));
	Player p2 = new Player(new Color(51, 51, 51));
	boolean player1 = true;
	Figure[][] startField = {{p2.Rook1, p2.Knight1, p2.Bishop1, p2.Queen, p2.King, p2.Bishop2, p2.Knight2, p2.Rook2}, 
			{p2.Pawn1, p2.Pawn2, p2.Pawn3, p2.Pawn4, p2.Pawn5, p2.Pawn6, p2.Pawn7, p2.Pawn8},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{p1.Pawn1, p1.Pawn2, p1.Pawn3, p1.Pawn4, p1.Pawn5, p1.Pawn6, p1.Pawn7, p1.Pawn8},
			{p1.Rook1, p1.Knight1, p1.Bishop1, p1.Queen, p1.King, p1.Bishop2, p1.Knight1, p1.Rook2}};
	Field field;
	int[][] fieldColor =  {{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1}};//0 = black 1 = white
	
	public Game() {
		Panel = new JPanel(null);
		Button.setBounds(900, 10, 100, 30);
		Button.addActionListener(this);
		Panel.add(Button);
		Panel.setBackground(new Color(135,206,235));
		setContentPane(Panel);
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.setExtendedState(MAXIMIZED_BOTH);
		g.setDefaultCloseOperation(EXIT_ON_CLOSE);
		g.setVisible(true);
	}
	/**
	 * xStart = xPosition where the field starts
	 * yStart = yPosition where the field starts
	 * JLabels are placed in a chessbord pattern
	 * two players are created
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Button.setVisible(false);
		xStart = getWidth() / 2 -400;
		yStart = getHeight() / 2 -400;
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				fields[x][y] = new JLabel("",SwingConstants.CENTER);
				fields[x][y].setFont(new Font("Arial", Font.BOLD, 50));
				fields[x][y].setOpaque(true);
				if(fieldColor[x][y] == 0) {
					fields[x][y].setBackground(Color.black);
				}
				if(fieldColor[x][y] == 1) {
					fields[x][y].setBackground(new Color(132, 112, 28));
				}
				fields[x][y].setBounds(xStart + x * 100, yStart + y*100, 100, 100);
				Panel.add(fields[x][y]);
			}
		}
		setContentPane(Panel);
		field = new Field();
		placeFigures();
		addMouseListener(this);
	}
	
	private void repaintField() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(fieldColor[x][y] == 0) {
					fields[x][y].setBackground(Color.black);
				}
				if(fieldColor[x][y] == 1) {
					fields[x][y].setBackground(new Color(132, 112, 28));
				}
			}
		}
	}
	
	/**
	 * 
	 * @param figures all figures that have to be placed
	 * @param top player two is on the top, player one goes first
	 */
	private void placeFigures() {	
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(startField[y][x]!= null) {
					if(y < 2) {
						fields[x][y].setForeground(p2.playColor);
					}
					if(y > 5) {
						fields[x][y].setForeground(p1.playColor);
					} 
					fields[x][y].setText(startField[y][x].displayName);
					field.setFigure(x, y, startField[y][x]);
				}
			}
		}
	}
	
	private boolean fieldEmpty(int x, int y) {
		if(fields[x][y].getText() == "") {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean myFigure(int x, int y, Player p) {
		if(!atWall(x, y)) {
			if(fields[x][y].getForeground() == p.playColor) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param xOld old xPosition
	 * @param yOld old yPosition
	 * @param xNew new xPosition	
	 * @param yNew new yPosition
	 * @param displayName name the figure is displayed
	 * @param p the player where the figure belongs to
	 * moves a figure from an old position to a new position
	 * 
	 */
	private void move(int xOld, int yOld, int xNew, int yNew, Player p,Figure f) {
		if(xOld == xNew && yOld == yNew) {
			return;
		}
		if(f.firstTurn) {
			f.firstTurn = false;
		}
		fields[xOld][yOld].setForeground(Color.black); //needs to be there for the funktion myFigure
		fields[xNew][yNew].setForeground(p.playColor);
		fields[xNew][yNew].setText(fields[xOld][yOld].getText());
		fields[xOld][yOld].setText("");
		field.moveFigure(xOld, yOld, xNew, yNew);
		if(player1) {
			player1 = false;
		}else {
			player1 = true;
		}
		repaintField();
		choosedFigure = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int[] i = inField(x, y);
		if(player1) {
			checkKlick(i[0], i[1], p1);
		}else {
			checkKlick(i[0], i[1], p2);
		}
	}
	
	/**
	 * 
	 * @param xPos x position off the mouse click
	 * @param yPos y position off the mouse click
	 * @return i[0] = x Position of the field i[1] = y Position of the field
	 */
	private int[] inField(int xPos,int yPos) {
		int[] i = new int[2];
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(xPos >= xStart + 100*x && xPos <= xStart +100*x + 100) {
					if(yPos >= yStart + 100*y && yPos <= yStart +100*y + 100) {
						i[0] = x;
						i[1] = y;
					}
				}
			}
		}
		return i;
	}
	
	private void checkKlick(int x, int y, Player p) {
		if(!fieldEmpty(x, y) && myFigure(x, y, p)) {
			repaintField();
			choosedFigure = field.getFigure(x, y);
			choosedFigurePosition[0] = x;
			choosedFigurePosition[1] = y;
			fields[x][y].setBackground(Color.darkGray);
			markFields(choosedFigure, x, y, p);
		}if(choosedFigure != null && !myFigure(x, y, p) && fields[x][y].getBackground() == Color.gray) {
			move(choosedFigurePosition[0], choosedFigurePosition[1], x, y, p, choosedFigure);
		}
	}
	
	private void markFields(Figure f, int x,int y, Player p) {
		if(f.canCross) {
			markCross(x,y, p);
		}
		if(f.canJump) {
			markJump(x,y,p);
		}
		if(f.canLine) {
			markLine(x,y,p);
		}
		if(f.canSideStep) {
			markSideStep(x,y,p);
		}
		if(f.canStep) {
			markStep(x,y,p, f.firstTurn);
		}
		if(f.canSurround) {
			markSurround(x,y,p);
		}
	}
	
	private void markSurround(int x, int y, Player p) {
		if(!atWall(x, y-1) && !myFigure(x, y-1, p)) {
			fields[x][y-1].setBackground(Color.gray);
		}
		if(!atWall(x+1, y-1) && !myFigure(x+1, y-1, p)) {
			fields[x+1][y-1].setBackground(Color.gray);
		}
		if(!atWall(x-1, y-1)&& !myFigure(x-1, y-1, p)) {
			fields[x-1][y-1].setBackground(Color.gray);
		}
		if(!atWall(x+1, y)&& !myFigure(x+1, y, p)) {
			fields[x+1][y].setBackground(Color.gray);
		}
		if(!atWall(x-1, y)&& !myFigure(x-1, y, p)) {
			fields[x-1][y].setBackground(Color.gray);
		}
		if(!atWall(x, y+1)&& !myFigure(x, y+1, p)) {
			fields[x][y+1].setBackground(Color.gray);
		}
		if(!atWall(x+1, y+1)&& !myFigure(x+1, y+1, p)) {
			fields[x+1][y+1].setBackground(Color.gray);
		}
		if(!atWall(x-1, y+1)&& !myFigure(x-1, y+1, p)) {
			fields[x-1][y+1].setBackground(Color.gray);
		}
	}

	private void markStep(int x, int y, Player p, boolean firstTurn) {
		if(p == p1) {
			if(fieldEmpty(x, y-1)) {
				fields[x][y-1].setBackground(Color.gray);
			}	
			if(firstTurn && fieldEmpty(x, y-2)) {
				fields[x][y-2].setBackground(Color.gray);
			}
		}
		if(p == p2) {
			if(fieldEmpty(x, y+1)) {
				fields[x][y+1].setBackground(Color.gray);
			}
			if(firstTurn && fieldEmpty(x, y+2)) {
				fields[x][y+2].setBackground(Color.gray);
			}
		}
	}

	private void markSideStep(int x, int y, Player p) {
		if(p == p1) {
			if(!atWall(x+1, y-1) &&!fieldEmpty(x+1, y-1) && !myFigure(x+1, y-1, p)) {
				fields[x+1][y-1].setBackground(Color.gray);
			}
			if(!atWall(x-1, y-1) &&!fieldEmpty(x-1, y-1) && !myFigure(x-1, y-1, p)) {
				fields[x-1][y-1].setBackground(Color.gray);
			}
		}
		if(p == p2) {
			if(!atWall(x+1, y+1) && !fieldEmpty(x+1, y+1) && !myFigure(x+1, y+1, p)) {
				fields[x+1][y+1].setBackground(Color.gray);
			}
			if(!atWall(x-1, y+1) &&!fieldEmpty(x-1, y+1) && !myFigure(x-1, y+1, p)) {
				fields[x-1][y+1].setBackground(Color.gray);
			}
		}
	}

	private void markLine(int x, int y, Player p) {
		int xPos = x;
		int yPos = y;
		while(xPos+1 <= 7) {
			xPos++;
			if(!fieldEmpty(xPos, yPos)) {
				if(myFigure(xPos, yPos, p)) {
					break;
				}else {
					fields[xPos][yPos].setBackground(Color.gray);
					break;
				}
			}else {
				fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		while(xPos-1 >= 0) {
			xPos--;
			if(!fieldEmpty(xPos, yPos)) {
				if(myFigure(xPos, yPos, p)) {
					break;
				}else {
					fields[xPos][yPos].setBackground(Color.gray);
					break;
				}
			}else {
				fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		while(yPos+1 <= 7) {
			yPos++;
			if(!fieldEmpty(xPos, yPos)) {
				if(myFigure(xPos, yPos, p)) {
					break;
				}else {
					fields[xPos][yPos].setBackground(Color.gray);
					break;
				}
			}else {
				fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		while(yPos-1 >= 0) {
			yPos--;
			if(!fieldEmpty(xPos, yPos)) {
				if(myFigure(xPos, yPos, p)) {
					break;
				}else {
					fields[xPos][yPos].setBackground(Color.gray);
					break;
				}
			}else {
				fields[xPos][yPos].setBackground(Color.gray);
			}
		}
	}

	private boolean atWall(int x, int y) {
		if(0 > x || x >= 8 || 0 > y || y >= 8) {
			return true;
		}
		return false;
	}

	private void markJump(int x, int y, Player p) {
		int xPos = x;
		int yPos = y;
		if(!atWall(xPos-2, yPos+1) && !myFigure(xPos-2, yPos+1, p)) {
			xPos = xPos-2;
			yPos++;
			fields[xPos][yPos].setBackground(Color.gray);
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos-2, yPos-1)&& !myFigure(xPos-2, yPos-1, p)) {
			xPos = xPos-2;
			yPos--;
			fields[xPos][yPos].setBackground(Color.gray);
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos+2, yPos+1) && !myFigure(xPos+2, yPos+1, p)) {
			xPos = xPos+2;
			yPos++;
			fields[xPos][yPos].setBackground(Color.gray);
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos+2, yPos-1) && !myFigure(xPos+2, yPos-1, p)) {
			xPos = xPos+2;
			yPos--;
			fields[xPos][yPos].setBackground(Color.gray);
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos+1, yPos-2) && !myFigure(xPos+1, yPos-2, p)) {
			xPos++;
			yPos = yPos-2;
			fields[xPos][yPos].setBackground(Color.gray);
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos-1, yPos-2)&& !myFigure(xPos-1, yPos-2, p)) {
			xPos--;
			yPos = yPos-2;
			fields[xPos][yPos].setBackground(Color.gray);
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos-1, yPos+2)&& !myFigure(xPos-1, yPos+2, p)) {
			xPos--;
			yPos = yPos+2;
			fields[xPos][yPos].setBackground(Color.gray);
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos+1, yPos+2)&& !myFigure(xPos+1, yPos+2, p)) {
			xPos++;
			yPos = yPos+2;
			fields[xPos][yPos].setBackground(Color.gray);
		}
	}

	private void markCross(int x,int y, Player p) {
		int xPos = x;
		int yPos = y; 
		while(xPos+1 <= 7 && yPos-1 >= 0) {
			xPos++;
			yPos--;
			if(xPos!= x && yPos != y) {
				if(!fieldEmpty(xPos, yPos)) {
					if(myFigure(xPos, yPos, p)) {
						break;
					}else {
						fields[xPos][yPos].setBackground(Color.gray);
						break;
					}
				}else {
					fields[xPos][yPos].setBackground(Color.gray);
				}	
			}
		}
	
		while(xPos-1 >= 0 && yPos+1 <= 7) {
			xPos--;
			yPos++;
			if(xPos!= x && yPos != y) {
				if(!fieldEmpty(xPos, yPos)) {
					if(myFigure(xPos, yPos, p)) {
						break;
					}else {
						fields[xPos][yPos].setBackground(Color.gray);
						break;
					}
				}else {
					fields[xPos][yPos].setBackground(Color.gray);
				}	
			}
		}
		
		xPos = x;
		yPos = y;
		while(xPos-1 >= 0 && yPos-1 >= 0) {
			xPos--;
			yPos--;
			if(xPos!= x && yPos != y) {
				if(!fieldEmpty(xPos, yPos)) {
					if(myFigure(xPos, yPos, p)) {
						break;
					}else {
						fields[xPos][yPos].setBackground(Color.gray);
						break;
					}
				}else {
					fields[xPos][yPos].setBackground(Color.gray);
				}	
			}
		}
		xPos = x;
		yPos = y;
		while(xPos+1 <= 7 && yPos+1 <= 7) {
			xPos++;
			yPos++;
			if(xPos!= x && yPos != y) {
				if(!fieldEmpty(xPos, yPos)) {
					if(myFigure(xPos, yPos, p)) {
						break;
					}else {
						fields[xPos][yPos].setBackground(Color.gray);
						break;
					}
				}else {
					fields[xPos][yPos].setBackground(Color.gray);
				}	
			}
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
