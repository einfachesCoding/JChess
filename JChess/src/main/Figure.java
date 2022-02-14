package main;

public class Figure {
	boolean canStep, canSideStep, canLine, canJump, canCross, canSurround;
	String displayName;
	
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
