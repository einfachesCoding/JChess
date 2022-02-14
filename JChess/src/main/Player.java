package main;

import java.awt.Color;

public class Player {
	Figure Rook1 = new Figure(false, false, true, false, false, false,"T");
	Figure Rook2 = new Figure(false, false, true, false, false, false, "T");
	Figure Knight1 = new Figure(false, false, false, true, false, false, "P");
	Figure Knight2 = new Figure(false, false, false, true, false, false, "P");
	Figure Bishop1 = new Figure(false, false, false, false, true, false, "L");
	Figure Bishop2 = new Figure(false, false, false, false, true, false, "L");
	Figure Queen = new Figure(false, false, true, false, true, false, "D");
	Figure King = new Figure(false, false, false, false, false, true, "K");
	Figure Pawn1 = new Figure(true, true, false, false, false, false, "B");
	Figure Pawn2 = new Figure(true, true, false, false, false, false, "B");
	Figure Pawn3 = new Figure(true, true, false, false, false, false, "B");
	Figure Pawn4 = new Figure(true, true, false, false, false, false, "B");
	Figure Pawn5 = new Figure(true, true, false, false, false, false, "B");
	Figure Pawn6 = new Figure(true, true, false, false, false, false, "B");
	Figure Pawn7 = new Figure(true, true, false, false, false, false, "B");
	Figure Pawn8 = new Figure(true, true, false, false, false, false, "B");
	Figure[] figures = {Rook1, Knight1, Bishop1, Queen, King, Bishop2, Knight2, Rook2, Pawn1, Pawn2, Pawn3, Pawn4, Pawn5, Pawn6, Pawn7, Pawn8};
	Color playColor;
	public Player(Color c) {
		this.playColor = c;
	}
}
