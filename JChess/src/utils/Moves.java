package utils;

import java.awt.Color;
import java.util.ArrayList;

import gui.Game;

public class Moves {
	
	public static ArrayList<int[]> markedFields = new ArrayList<>();
	
	public static void markSurround(int x, int y, Player p) {
		if(!atWall(x, y-1) && !Field.myFigure(x, y-1, p)) {
			Game.fields[x][y-1].setBackground(Color.gray);
		}
		if(!atWall(x+1, y-1) && !Field.myFigure(x+1, y-1, p)) {
			Game.fields[x+1][y-1].setBackground(Color.gray);
		}
		if(!atWall(x-1, y-1)&& !Field.myFigure(x-1, y-1, p)) {
			Game.fields[x-1][y-1].setBackground(Color.gray);
		}
		if(!atWall(x+1, y)&& !Field.myFigure(x+1, y, p)) {
			Game.fields[x+1][y].setBackground(Color.gray);
		}
		if(!atWall(x-1, y)&& !Field.myFigure(x-1, y, p)) {
			Game.fields[x-1][y].setBackground(Color.gray);
		}
		if(!atWall(x, y+1)&& !Field.myFigure(x, y+1, p)) {
			Game.fields[x][y+1].setBackground(Color.gray);
		}
		if(!atWall(x+1, y+1)&& !Field.myFigure(x+1, y+1, p)) {
			Game.fields[x+1][y+1].setBackground(Color.gray);
		}
		if(!atWall(x-1, y+1)&& !Field.myFigure(x-1, y+1, p)) {
			Game.fields[x-1][y+1].setBackground(Color.gray);
		}
	}

	public static void markStep(int x, int y, Player p, boolean firstTurn) {
		if(p == Field.p1) {
			if(Field.fieldEmpty(x, y-1)) {
				Game.fields[x][y-1].setBackground(Color.gray);
			}else {
				return;
			}
			if(firstTurn && Field.fieldEmpty(x, y-2)) {
				Game.fields[x][y-2].setBackground(Color.gray);
			}
		}
		if(p == Field.p2) {
			if(Field.fieldEmpty(x, y+1)) {
				Game.fields[x][y+1].setBackground(Color.gray);
			}else {
				return;
			}
			if(firstTurn && Field.fieldEmpty(x, y+2)) {
				Game.fields[x][y+2].setBackground(Color.gray);
			}
		}
	}

	public static void markSideStep(int x, int y, Player p, boolean save) {
		if(p == Field.p1) {
			if(!atWall(x+1, y-1) && !Field.fieldEmpty(x+1, y-1) && !Field.myFigure(x+1, y-1, p)) {
				Game.fields[x+1][y-1].setBackground(Color.gray);
			}
			if(!atWall(x-1, y-1) &&!Field.fieldEmpty(x-1, y-1) && !Field.myFigure(x-1, y-1, p)) {
				Game.fields[x-1][y-1].setBackground(Color.gray);
			}
		}
		if(p == Field.p2) {
			if(!atWall(x+1, y+1) && !Field.fieldEmpty(x+1, y+1) && !Field.myFigure(x+1, y+1, p)) {
				Game.fields[x+1][y+1].setBackground(Color.gray);
			}
			if(!atWall(x-1, y+1) &&!Field.fieldEmpty(x-1, y+1) && !Field.myFigure(x-1, y+1, p)) {
				Game.fields[x-1][y+1].setBackground(Color.gray);
			}
		}
	}

	public static void markLine(int x, int y, Player p, boolean save) {
		int xPos = x;
		int yPos = y;
		while(xPos+1 <= 7) {
			xPos++;
			boolean b = checkMove(xPos, yPos, p, save);
			if(b) {
				break;
			}
		}
		xPos = x;
		yPos = y;
		while(xPos-1 >= 0) {
			xPos--;
			boolean b = checkMove(xPos, yPos, p, save);
			if(b) {
				break;
			}
		}
		xPos = x;
		yPos = y;
		while(yPos+1 <= 7) {
			yPos++;
			boolean b = checkMove(xPos, yPos, p, save);
			if(b) {
				break;
			}
		}
		xPos = x;
		yPos = y;
		while(yPos-1 >= 0) {
			yPos--;
			boolean b = checkMove(xPos, yPos, p, save);
			if(b) {
				break;
			}
		}
	}

	private static boolean atWall(int x, int y) {
		if(0 > x || x >= 8 || 0 > y || y >= 8) {
			return true;
		}
		return false;
	}

	public static void markJump(int x, int y, Player p, boolean save) {
		int xPos = x;
		int yPos = y;
		if(!atWall(xPos-2, yPos+1) && !Field.myFigure(xPos-2, yPos+1, p)) {
			xPos = xPos-2;
			yPos++;
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}	
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos-2, yPos-1)&& !Field.myFigure(xPos-2, yPos-1, p)) {
			xPos = xPos-2;
			yPos--;
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos+2, yPos+1) && !Field.myFigure(xPos+2, yPos+1, p)) {
			xPos = xPos+2;
			yPos++;
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos+2, yPos-1) && !Field.myFigure(xPos+2, yPos-1, p)) {
			xPos = xPos+2;
			yPos--;
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos+1, yPos-2) && !Field.myFigure(xPos+1, yPos-2, p)) {
			xPos++;
			yPos = yPos-2;
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos-1, yPos-2)&& !Field.myFigure(xPos-1, yPos-2, p)) {
			xPos--;
			yPos = yPos-2;
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos-1, yPos+2)&& !Field.myFigure(xPos-1, yPos+2, p)) {
			xPos--;
			yPos = yPos+2;
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		xPos = x;
		yPos = y;
		if(!atWall(xPos+1, yPos+2)&& !Field.myFigure(xPos+1, yPos+2, p)) {
			xPos++;
			yPos = yPos+2;
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}
		}
	}

	public static void markCross(int x,int y, Player p, boolean save) {
		int xPos = x;
		int yPos = y; 
		while(xPos+1 <= 7 && yPos-1 >= 0) {
			xPos++;
			yPos--;
			if(xPos!= x && yPos != y) {
				boolean b = checkMove(xPos, yPos, p, save);
				if(b) {
					break;
				}	
			}
		}
	
		while(xPos-1 >= 0 && yPos+1 <= 7) {
			xPos--;
			yPos++;
			if(xPos!= x && yPos != y) {
				boolean b = checkMove(xPos, yPos, p, save);
				if(b) {
					break;
				}
			}
		}
		
		xPos = x;
		yPos = y;
		while(xPos-1 >= 0 && yPos-1 >= 0) {
			xPos--;
			yPos--;
			if(xPos!= x && yPos != y) {
				boolean b = checkMove(xPos, yPos, p, save);
				if(b) {
					break;
				}
			}
		}
		xPos = x;
		yPos = y;
		while(xPos+1 <= 7 && yPos+1 <= 7) {
			xPos++;
			yPos++;
			if(xPos!= x && yPos != y) {
				boolean b = checkMove(xPos, yPos, p, save);
				if(b) {
					break;
				}
			}
		}
	}
	
	public static boolean checkMove(int xPos, int yPos, Player p, boolean save) {
		if(!Field.fieldEmpty(xPos, yPos)) {
			if(Field.myFigure(xPos, yPos, p)) {
				return true;
			}else {
				if(save) {
					int[] i = {xPos,yPos};
					markedFields.add(i);
				}else {
					Game.fields[xPos][yPos].setBackground(Color.gray);
				}		
				return true;
			}
		}else {
			if(save) {
				int[] i = {xPos,yPos};
				markedFields.add(i);
			}else {
				Game.fields[xPos][yPos].setBackground(Color.gray);
			}
		}
		return false;
	}
}
