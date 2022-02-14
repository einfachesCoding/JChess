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
	int[] choosedFigure = new int[2];
	JLabel[][] fields = new JLabel[8][8];
	int xStart, yStart;
	JPanel Panel;
	JButton Button = new JButton("Starten");
	Player p1,p2;
	boolean player1 = true;
	
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
		int[][] field =  {{1,0,1,0,1,0,1,0},
				{0,1,0,1,0,1,0,1},
				{1,0,1,0,1,0,1,0},
				{0,1,0,1,0,1,0,1},
				{1,0,1,0,1,0,1,0},
				{0,1,0,1,0,1,0,1},
				{1,0,1,0,1,0,1,0},
				{0,1,0,1,0,1,0,1}};//0 = black 1 = white
		Button.setVisible(false);
		xStart = getWidth() / 2 -400;
		yStart = getHeight() / 2 -400;
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				fields[x][y] = new JLabel("",SwingConstants.CENTER);
				fields[x][y].setFont(new Font("Arial", Font.BOLD, 50));
				fields[x][y].setOpaque(true);
				if(field[x][y] == 0) {
					fields[x][y].setBackground(Color.black);
				}
				if(field[x][y] == 1) {
					fields[x][y].setBackground(new Color(132, 112, 28));
				}
				fields[x][y].setBounds(xStart + x * 100, yStart + y*100, 100, 100);
				Panel.add(fields[x][y]);
			}
		}
		setContentPane(Panel);
		p1 = new Player(new Color(226, 196, 126));
		p2 = new Player(new Color(51, 51, 51));
		placeFigures(p1.figures, false);
		placeFigures(p2.figures, true);
		choosedFigure[0] = -1;
		choosedFigure[1] = -1;
		addMouseListener(this);
	}
	/**
	 * 
	 * @param figures all figures that have to be placed
	 * @param top player two is on the top, player one goes first
	 */
	private void placeFigures(Figure[] figures, boolean top) {
		if(top) {
			int y = 0;
			for(int x = 0; x < 8; x++){
				fields[x][y].setForeground(p2.playColor);
				fields[x][y].setText(figures[x].displayName);
			}
			y = 1;
			for(int x = 0; x < 8; x++){
				fields[x][y].setForeground(p2.playColor);
				fields[x][y].setText(figures[x+8].displayName);
			}
		}else {
			int y = 6;			
			for(int x = 0; x < 8; x++){
				fields[x][y].setForeground(p1.playColor);
				fields[x][y].setText(figures[x+8].displayName);
			}
			y = 7;
			for(int x = 0; x < 8; x++) {
				fields[x][y].setForeground(p1.playColor);
				fields[x][y].setText(figures[x].displayName);
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
	
	private boolean moveAllowed(Figure f, int x, int y, Player p) {
		
		return false;
	}
	
	private boolean myFigure(int x, int y, Player p) {
		if(fields[x][y].getForeground() == p.playColor) {
			return true;
		}else {
			return false;
		}
	}
	
	private void move(int xOld, int yOld, int xNew, int yNew, String displayName, Player p) {
		if(xOld == xNew && yOld == yNew) {
			return;
		}
		fields[xOld][yOld].setText("");
		fields[xOld][yOld].setForeground(Color.black); //needs to be there for the funktion myFigure
		fields[xNew][yNew].setForeground(p.playColor);
		fields[xNew][yNew].setText(displayName);
		choosedFigure[0] = -1;
		choosedFigure[1] = -1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int[] i = inField(x, y);
		checkKlick(i[0], i[1], p1);
	}
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
		if(choosedFigure[0] == -1 && !fieldEmpty(x, y) && myFigure(x, y, p)) {
			choosedFigure[0] = x;
			choosedFigure[1] = y;
		}else if(choosedFigure[0] != -1){
			move(choosedFigure[0], choosedFigure[1], x, y, fields[choosedFigure[0]][choosedFigure[1]].getText(), p);
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
