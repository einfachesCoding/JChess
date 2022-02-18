package main;

public class Figure {
	boolean canStep, canSideStep, canLine, canJump, canCross, canSurround;
	boolean firstTurn = true;
	String displayName;
	/**
	 * 
	 * @param step figure can step one or two forward
	 * @param sideStep figure can step sideways if she hits another figure
	 * @param line figure can move in a line if there is no figure in the way
	 * @param jump figure can jump above other figures
	 * @param cross figure can move in lines but only on white or black
	 * @param surround figure can hit everything that is next to him
	 * @param displayName the name how the figure is displayed
	 */
	public Figure(boolean step, boolean sideStep, boolean line, boolean jump, boolean cross, boolean surround, String displayName) {
		this.canStep = step;
		this.canSideStep = sideStep;
		this.canLine = line;
		this.canJump = jump;
		this.canCross = cross;
		this.canSurround = surround;
		this.displayName = displayName;
	}
}
