package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import utils.*;

public class Game{
	static JPanel Panel;
	static JFrame frame;
	static int xStart, yStart;
	static JButton Button = new JButton("Starten");
	private Listeners l = new Listeners();
	public static JLabel[][] fields = new JLabel[8][8];
	public static boolean player1 = true;
	
	public Game() {
		frame = new JFrame();
		Panel = new JPanel(null);
		Button.setBounds(900, 10, 100, 30);
		Button.addActionListener(l);
		Panel.add(Button);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel.setBackground(new Color(135,206,235));
		frame.addMouseListener(l);
		frame.setContentPane(Panel);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public static void placeField() {
		xStart = frame.getWidth() / 2 -400;
		yStart = frame.getHeight() / 2 -400;
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				fields[x][y] = new JLabel("",SwingConstants.CENTER);
				fields[x][y].setFont(new Font("Arial", Font.BOLD, 50));
				fields[x][y].setOpaque(true);
				if(Field.backgroundField[x][y] == 0) {
					fields[x][y].setBackground(Color.black);
				}
				if(Field.backgroundField[x][y] == 1) {
					fields[x][y].setBackground(new Color(132, 112, 28));
				}
				fields[x][y].setBounds(xStart + x * 100, yStart + y*100, 100, 100);
				Panel.add(fields[x][y]);
			}
		}
		frame.setContentPane(Panel);
	}
	
	public static void help(ArrayList<int[]> t) {
		for(int i = 0; i < t.size(); i++) {
			int[] j = t.get(i);
			fields[j[0]][j[1]].setBackground(Color.gray);
		}
	}
	
	public static void repaintField() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(Field.backgroundField[x][y] == 0) {
					fields[x][y].setBackground(Color.black);
				}
				if(Field.backgroundField[x][y] == 1) {
					fields[x][y].setBackground(new Color(132, 112, 28));
				}
			}
		}
	}
	
	public static void placeFigures() {	
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(Field.startField[y][x]!= null) {
					if(y < 2) {
						fields[x][y].setForeground(Field.p2.playColor);
					}
					if(y > 5) {
						fields[x][y].setForeground(Field.p1.playColor);
					} 
					fields[x][y].setText(Field.startField[y][x].displayName);
				}
			}
		}
	}
	
	public static void repaintBackground() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(Field.backgroundField[x][y] == 0) {
					fields[x][y].setBackground(Color.black);
				}
				if(Field.backgroundField[x][y] == 1) {
					fields[x][y].setBackground(new Color(132, 112, 28));
				}
			}
		}
	}
	
	public static void addFigure(Figure f, int x, int y, Player p) {
		fields[x][y].setText(f.displayName);
		fields[x][y].setForeground(p.playColor);
	}
	
	public static void move(int xOld, int yOld, int xNew, int yNew, Player p) {
		fields[xOld][yOld].setForeground(Color.black); 
		fields[xNew][yNew].setForeground(p.playColor);
		fields[xNew][yNew].setText(fields[xOld][yOld].getText());
		fields[xOld][yOld].setText("");
	}
}	