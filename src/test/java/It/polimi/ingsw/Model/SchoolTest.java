package It.polimi.ingsw.Model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SchoolTest {

	@Test
	public void MoveStudentToDiningRoom() {
		School school = new School();
		school.AddEntranceStudents(Color.BLUE);
		school.AddEntranceStudents(Color.BLUE);
		school.AddEntranceStudents(Color.GREEN);
		school.AddEntranceStudents(Color.RED);
		school.MoveStudentToDiningRoom(0);
		school.MoveStudentToDiningRoom(0);
		school.MoveStudentToDiningRoom(1);
		int BlueStudents = school.getStudentNumber(Color.BLUE);
		assertEquals(2, BlueStudents);
	}

	@Test
	public void MoveStudentToDiningRoomOrder() {
		School school = new School();
		school.AddEntranceStudents(Color.PINK);
		school.AddEntranceStudents(Color.YELLOW);
		school.AddEntranceStudents(Color.RED);
		school.AddEntranceStudents(Color.GREEN);
		school.MoveStudentToDiningRoom(0);
		school.MoveStudentToDiningRoom(0);
		school.MoveStudentToDiningRoom(1);
		int RedStudents = school.getStudentNumber(Color.GREEN);
		assertEquals(1, RedStudents);
	}

	@Test
	public void RemoveStudentsFromDiningRoom() {
		School school = new School();
		for (int i = 0; i < 5; i++) {
			school.AddEntranceStudents(Color.PINK);
			school.MoveStudentToDiningRoom(0);
		}
		school.RemoveStudentFromDiningRoom(Color.PINK);
		int PinkStudents = school.getStudentNumber(Color.PINK);
		assertEquals(4, PinkStudents);
	}

	@Test
	public void RemoveStudents2() {
		School school = new School();
		school.AddStudentToDiningRoom(Color.GREEN);
		school.AddStudentToDiningRoom(Color.BLUE);
		school.RemoveStudentFromDiningRoom(Color.BLUE);
		school.RemoveStudentFromDiningRoom(Color.GREEN);
		assertEquals(0, school.getStudentNumber(Color.GREEN));
	}

	@Test
	public void AddRemoveStudentsFromDiningRoom() {
		School school = new School();
		for (int i = 0; i < 3; i++) {
			school.AddStudentToDiningRoom(Color.PINK);
			school.AddStudentToDiningRoom(Color.YELLOW);
			school.AddStudentToDiningRoom(Color.RED);
			school.AddStudentToDiningRoom(Color.GREEN);
			school.AddStudentToDiningRoom(Color.GREEN);
		}
		school.RemoveStudentFromDiningRoom(Color.RED);
		int RedStudents = school.getStudentNumber(Color.RED);
		assertEquals(2, RedStudents);
		school.RemoveStudentFromDiningRoom(Color.YELLOW);
		int YellowStudents = school.getStudentNumber(Color.YELLOW);
		assertEquals(2, YellowStudents);

	}

	@Test
	public void MoveStudentToIsland() {
		School school = new School();
		school.AddEntranceStudents(Color.PINK);
		school.AddEntranceStudents(Color.YELLOW);
		Color studentColor = school.MoveStudentToIsland(1);
		assertEquals(Color.YELLOW, studentColor);
	}

	@Test
	public void MoveStudentToIslandRemove() {
		School school = new School();
		school.AddEntranceStudents(Color.PINK);
		school.AddEntranceStudents(Color.YELLOW);
		Color RemovedColor = school.MoveStudentToIsland(0);
		Color studentColor = school.GetEntranceStudentColor(0);
		assertEquals(Color.YELLOW, studentColor);
	}

	@Test
	public void RemoveStudentFromEntranceColorControl() {
		School school = new School();
		school.AddEntranceStudents(Color.PINK);
		school.AddEntranceStudents(Color.YELLOW);
		Color studentColor = school.RemoveStudentFromEntrance(0);
		assertEquals(Color.PINK, studentColor);
	}

	@Test
	public void RemoveStudentFromEntrance() {
		School school = new School();
		school.AddEntranceStudents(Color.RED);
		school.AddEntranceStudents(Color.GREEN);
		Color RemovedColor = school.RemoveStudentFromEntrance(0);
		Color studentColor = school.GetEntranceStudentColor(0);
		assertEquals(Color.GREEN, studentColor);
	}

	@Test
	public void EntranceStudentsNumber() {
		School school = new School();
		school.AddEntranceStudents(Color.PINK);
		school.AddEntranceStudents(Color.YELLOW);
		int numberOfStudents = school.getEntranceStudentsNumber();
		assertEquals(2, numberOfStudents);
	}
}
