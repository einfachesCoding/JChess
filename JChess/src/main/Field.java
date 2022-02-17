package main;

public class Field {
	Figure[][] field = new Figure[8][8];
	
	public void setFigure(int x,int y, Figure f) {
		field[x][y] = f;
	}
	
	public Figure getFigure(int x, int y){
		return field[x][y];
	}
	
	public void moveFigure(int xOld, int yOld, int xNew, int yNew) {
		Figure f = field[xOld][yOld];
		field[xOld][yOld] = null;
		field[xNew][yNew] = f;
	}
}
