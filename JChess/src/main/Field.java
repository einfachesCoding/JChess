package main;

public class Field {
	Figure[][] field = new Figure[8][8];
	
	public void setFigure(int x,int y, Figure f) {
		field[x][y] = f;
	}
	
	public Figure getFigure(int x, int y){
		return field[x][y];
	}
	
	public int[] getPosition(Figure f) {
		int[] i = {0,0};
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				if(field[x][y] == f) {
					i[0]= x;
					i[1] = y;
					return i;
				}
			}
		}
		return i;
	}
	
	public void moveFigure(int xOld, int yOld, int xNew, int yNew) {
		Figure f = field[xOld][yOld];
		field[xOld][yOld] = null;
		field[xNew][yNew] = f;
	}
}
