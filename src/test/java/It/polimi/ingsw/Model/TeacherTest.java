package It.polimi.ingsw.Model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TeacherTest {

	@Test
	public void getControllingPlayerColor() {
		Player player = new Player(TowerColor.BLACK, "Tullio", 0);
		Teacher teacher = new Teacher(Color.BLUE);
		teacher.ChangeController(player);
		TowerColor towerColor = teacher.getControllingPlayerColor();
		assertEquals(towerColor, TowerColor.BLACK);
	}

	@Test
	public void IncreaseHighestNumberOfStudents() {
		Teacher teacher = new Teacher(Color.BLUE);
		for (int i = 0; i < 3; i++) teacher.IncreaseHighestNumberOfStudents();
		int Number = teacher.getHighestNumberOfStudents();
		assertEquals(Number, 3);
	}

	@Test
	public void getControllingPlayer() {
		Teacher teacher = new Teacher(Color.YELLOW);
		Player controllingPlayer = new Player(TowerColor.WHITE, "Giulio", 0);
		teacher.ChangeController(controllingPlayer);
		assertEquals(controllingPlayer, teacher.getControllingPlayer());
	}

	@Test
	public void getTeacherColor() {
		Teacher teacher = new Teacher(Color.RED);
		Color teacherColor = teacher.getTeacherColor();
		assertEquals(Color.RED, teacherColor);
	}
}
