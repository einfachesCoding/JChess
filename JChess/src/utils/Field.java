package utils;

import java.awt.Color;

public class Field {
	public static Player p1 = new Player(new Color(226, 196, 126));
	public static Player p2 = new Player(new Color(51, 51, 51));
	private static Figure[] p1Figures = {p1.Pawn1, p1.Pawn2, p1.Pawn3, p1.Pawn4, p1.Pawn5, p1.Pawn6, p1.Pawn7, p1.Pawn8,
			p1.Rook1, p1.Knight1, p1.Bishop1, p1.Queen, p1.King, p1.Bishop2, p1.Knight1, p1.Rook2};
	
	private static Figure[] p2Figures = {p2.Rook1, p2.Knight1, p2.Bishop1, p2.Queen, p2.King, p2.Bishop2, p2.Knight2, p2.Rook2, 
			p2.Pawn1, p2.Pawn2, p2.Pawn3, p2.Pawn4, p2.Pawn5, p2.Pawn6, p2.Pawn7, p2.Pawn8};
 	
	public static Figure[][] startField = {{p2.Rook1, p2.Knight1, p2.Bishop1, p2.Queen, p2.King, p2.Bishop2, p2.Knight2, p2.Rook2}, 
			{p2.Pawn1, p2.Pawn2, p2.Pawn3, p2.Pawn4, p2.Pawn5, p2.Pawn6, p2.Pawn7, p2.Pawn8},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{p1.Pawn1, p1.Pawn2, p1.Pawn3, p1.Pawn4, p1.Pawn5, p1.Pawn6, p1.Pawn7, p1.Pawn8},
			{p1.Rook1, p1.Knight1, p1.Bishop1, p1.Queen, p1.King, p1.Bishop2, p1.Knight1, p1.Rook2}};
	public static int[][] backgroundField =  {{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1}};//0 = black 1 = white
		
	public static Figure getFigure(int x, int y){
		return startField[y][x];
	}
	
	public static void addFigure(Figure f, int x, int y) {
		startField[y][x] = f;
	}
	
	public static void removeFigure(int x, int y) {
		startField[y][x] = null;
	}
	
	public static void reset() {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(y == 0 || y == 1) {
					startField[y][x] = p2Figures[x + (y * 8)];
				}
				else if(y == 6) {
					startField[y][x] = p1Figures[x];
				}
				else if(y == 7) {
					startField[y][x] = p1Figures[x+8];
				}
				else {
					startField[y][x] = null;
				}
			}
		}
	}
	
	public static int[] getPosition(Figure f) {
		int[] i = {0,0};
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(startField[x][y] == f) {
					i[0] = y;
					i[1] = x;
					return i;
				}
			}
		}
		return i;
	}
	
	public static void moveFigure(int xOld, int yOld, int xNew, int yNew) {
		Figure f = startField[yOld][xOld];
		startField[yOld][xOld] = null;
		startField[yNew][xNew] = f;
	}
	
	public static boolean fieldEmpty(int x,int y) {
		if(startField[y][x] == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean myFigure(int x, int y, Player p) { 
		for(int i = 0; i < 16; i++) {
			if(p == p1) {
				if(startField[y][x] == p1Figures[i]) {
					return true;
				}
			}
			if(p == p2) {
				if(startField[y][x] == p2Figures[i]) {
					return true;
				}
			}
		}
		return false;
	}
}
