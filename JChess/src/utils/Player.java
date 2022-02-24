package utils;

import java.awt.Color;

public class Player {
	public Figure Rook1 = new Figure(false, false, true, false, false, false,"T");
	public Figure Rook2 = new Figure(false, false, true, false, false, false, "T");
	public Figure Knight1 = new Figure(false, false, false, true, false, false, "S");
	public Figure Knight2 = new Figure(false, false, false, true, false, false, "S");
	public Figure Bishop1 = new Figure(false, false, false, false, true, false, "L");
	public Figure Bishop2 = new Figure(false, false, false, false, true, false, "L");
	public Figure Queen = new Figure(false, false, true, false, true, false, "D");
	public Figure King = new Figure(false, false, false, false, false, true, "K");
	public Figure Pawn1 = new Figure(true, true, false, false, false, false, "B");
	public Figure Pawn2 = new Figure(true, true, false, false, false, false, "B");
	public Figure Pawn3 = new Figure(true, true, false, false, false, false, "B");
	public Figure Pawn4 = new Figure(true, true, false, false, false, false, "B");
	public Figure Pawn5 = new Figure(true, true, false, false, false, false, "B");
	public Figure Pawn6 = new Figure(true, true, false, false, false, false, "B");
	public Figure Pawn7 = new Figure(true, true, false, false, false, false, "B");
	public Figure Pawn8 = new Figure(true, true, false, false, false, false, "B");
	
	public Color playColor;
	public Player(Color c) {
		this.playColor = c;
	}
}
