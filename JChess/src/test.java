
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import gui.Listeners;
import utils.Field;

class test {
	@Test
	void testNotChess() {
		Field.moveFigure(4, 6, 4, 4);
		Field.moveFigure(4, 1, 4, 3);
		Field.moveFigure(5, 7, 1, 3);
		Field.moveFigure(3, 1, 3, 3);
		Field.moveFigure(3, 6, 3, 4);
		Field.moveFigure(2, 1, 2, 2);
		Listeners l = new Listeners();
		assertFalse(l.checkChess(Field.p2, Field.getPosition(Field.p2.King)));
		Field.reset();
	}
	@Test
	void testCrossChess() {
		Field.moveFigure(4, 6, 4, 4);
		Field.moveFigure(4, 1, 4, 3);
		Field.moveFigure(5, 7, 1, 3);
		Field.moveFigure(3, 1, 3, 3);
		Listeners l = new Listeners();
		assertTrue(l.checkChess(Field.p2, Field.getPosition(Field.p2.King)));
		Field.reset();
	}
	@Test
	void testJumpChess() {
		Field.moveFigure(1, 7, 2, 5);
		Field.moveFigure(2, 1, 2, 3);
		Field.moveFigure(2, 5, 1, 3);
		Field.moveFigure(4, 1, 4, 3);
		Field.moveFigure(1, 3, 2, 1);
		Listeners l = new Listeners();
		assertTrue(l.checkChess(Field.p2, Field.getPosition(Field.p2.King)));
		Field.reset();
	}
	
	@Test
	void testLineChess() {
		Field.moveFigure(4, 6, 4, 4);
		Field.moveFigure(6, 0, 5, 2);
		Field.moveFigure(3, 6, 3, 4);
		Field.moveFigure(4, 1, 4, 3);
		Field.moveFigure(3, 4, 4, 3);
		Field.moveFigure(5, 0, 2, 3);
		Field.moveFigure(7, 7, 7, 0);
		Listeners l = new Listeners();
		assertTrue(l.checkChess(Field.p2, Field.getPosition(Field.p2.King)));
		Field.reset();
	}
	
	@Test
	void testSideStepChess() {
		Field.moveFigure(2, 6, 2, 4);
		Field.moveFigure(4, 1, 4, 3);
		Field.moveFigure(2, 4, 2, 3);
		Field.moveFigure(5, 1, 5, 3);
		Field.moveFigure(2, 3, 2, 2);
		Field.moveFigure(5, 3, 5, 4);
		Field.moveFigure(2, 2, 3, 1);
		Listeners l = new Listeners();
		assertTrue(l.checkChess(Field.p2, Field.getPosition(Field.p2.King)));
		Field.reset();
	}
}
