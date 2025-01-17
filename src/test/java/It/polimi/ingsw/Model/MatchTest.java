package It.polimi.ingsw.Model;

import org.junit.Test;

import java.sql.Blob;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MatchTest {

	@Test
	public void NumberOfPlayers() {
		Match match = new Match(3, 0);
		Player[] players = new Player[3];
		players = match.getPlayers();
		assertNotNull(players[2]);
	}

	@Test
	public void MoveStudentsBagToIsland() {
		Match match = new Match(2, 1);
		match.MoveStudentsBagToIsland(0);
		int Blues = match.getTable().get(0).CountStudents(Color.BLUE);
		int Reds = match.getTable().get(0).CountStudents(Color.RED);
		int Yellows = match.getTable().get(0).CountStudents(Color.YELLOW);
		int Pinks = match.getTable().get(0).CountStudents(Color.PINK);
		int Green = match.getTable().get(0).CountStudents(Color.GREEN);
		int students = Blues + Reds + Yellows + Pinks + Green;
		assertEquals(1, students);
	}

	@Test
	public void MoveStudentBagToCloud() {
		Match match = new Match(2, 1);
		match.MoveStudentsBagToCloud(1);
		int cloudstudents = match.getClouds().get(1).CloudSize();
		assertEquals(3, cloudstudents);
	}

	@Test
	public void MoveStudentBagToCloud3Players() {
		Match match = new Match(3, 1);
		match.MoveStudentsBagToCloud(1);
		int cloudstudents = match.getClouds().get(1).CloudSize();
		assertEquals(4, cloudstudents);
	}

	@Test
	public void NumberOfClouds() {
		Match match = new Match(2, 1);
		int NumberOfClouds = match.getClouds().size();
		assertEquals(2, NumberOfClouds);
	}

	@Test
	public void PlayAssistantCard() {
		Match match = new Match(3, 1);
		match.PlayAssistantCard(0, 2);
		int OrderValue = match.getPlayers()[2].GetPlayedOrderValue();
		assertEquals(1, OrderValue);
	}

	@Test
	public void MoveMotherNature() {
		Match match = new Match(3, 1);
		match.MoveMotherNature(5);
		int motherNaturePosition = match.getMotherNaturePosition();
		assertEquals(5, motherNaturePosition);
	}

	@Test
	public void MoveMotherNatureBackToStart() {                          //test the movement back to the start of table
		Match match = new Match(3, 1);
		match.MoveMotherNature(6);
		match.MoveMotherNature(7);
		int motherNaturePosition = match.getMotherNaturePosition();
		assertEquals(1, motherNaturePosition);
	}

	@Test
	public void MergeIslandsStudents() {
		Match match = new Match(3, 1);
		boolean hasBlue = false;
		if (match.getTable().get(7).CountStudents(Color.BLUE) == 1) {
			hasBlue = true;
		}
		match.getTable().get(6).AddStudent(Color.YELLOW);
		match.getTable().get(6).AddStudent(Color.PINK);
		match.getTable().get(6).AddStudent(Color.RED);
		match.getTable().get(6).AddStudent(Color.GREEN);
		match.getTable().get(6).AddStudent(Color.BLUE);
		match.getTable().get(6).AddStudent(Color.BLUE);
		match.getTable().get(7).AddStudent(Color.BLUE);
		match.getTable().get(7).AddStudent(Color.BLUE);


		match.MergeIslands(7, 6);
		int BlueStudents = match.getTable().get(6).CountStudents(Color.BLUE);
		if (!hasBlue) {
			assertEquals(4, BlueStudents);
		} else {
			assertEquals(5, BlueStudents);
		}
	}

	@Test
	public void MergeIslandsTowers() {
		Match match = new Match(3, 1);
		match.getTable().get(2).IncreaseTower();
		match.getTable().get(2).IncreaseTower();
		match.getTable().get(3).IncreaseTower();
		match.MergeIslands(2, 3);
		int numberOfTowers = match.getTable().get(2).CountTowers();
		assertEquals(3, numberOfTowers);
	}

	@Test
	public void MergeIslandMotherNaturePosition() {
		Match match = new Match(3, 1);
		match.MoveMotherNature(7);
		match.MergeIslands(5, 6);
		int position = match.getMotherNaturePosition();
		assertEquals(6, position);
	}

	@Test
	public void MergeIslandMotherNaturePositionOnMergedIsland() {
		Match match = new Match(3, 1);
		match.MoveMotherNature(6);
		match.MergeIslands(5, 6);
		int position = match.getMotherNaturePosition();
		assertEquals(5, position);
	}

	@Test
	public void MergeIslandMotherNaturePositionOnIslandToMerge() {
		Match match = new Match(3, 1);
		match.MoveMotherNature(5);
		match.MergeIslands(5, 6);
		int position = match.getMotherNaturePosition();
		assertEquals(5, position);
	}

	@Test
	public void MergeIslandToStart() {
		Match match = new Match(3, 1);
		match.MoveMotherNature(11);
		match.MergeIslands(11, 0);
		int position = match.getMotherNaturePosition();
		assertEquals(10, position);
	}

	@Test
	public void MergeIslandToStart2() {
		Match match = new Match(3, 1);
		match.MoveMotherNature(11);
		match.MergeIslands(0, 11);
		int position = match.getMotherNaturePosition();
		assertEquals(0, position);
	}

	@Test
	public void MergeIslandToStart3() {
		Match match = new Match(3, 1);
		match.MergeIslands(11, 0);
		int position = match.getMotherNaturePosition();
		assertEquals(11, position);
	}

	@Test
	public void MergeIslandToStart4() {
		Match match = new Match(3, 1);
		match.MergeIslands(0, 11);
		int position = match.getMotherNaturePosition();
		assertEquals(0, position);
	}

	@Test
	public void PlanningPhase() {
		Match match = new Match(3, 1);
		match.PlanningPhase();
		int studentsOnCloud3 = match.getClouds().get(2).CloudSize();
		assertEquals(4, studentsOnCloud3);
	}

	@Test
	public void GetStudentsFromCloud() {
		Match match = new Match(3, 1);
		match.PlanningPhase();
		match.MoveStudentsFromCloudToEntrance(0, 0);
		int numberOfEntranceStudents = match.getPlayers()[0].getPlayersSchool().getEntranceStudentsNumber();
		assertEquals(13, numberOfEntranceStudents);
	}

	@Test
	public void MoveStudentFromEntranceToDiningRoom() {
		Match match = new Match(2, 1);
		Color studentColor = match.getPlayers()[1].getPlayersSchool().GetEntranceStudentColor(4);
		match.MoveStudentsFromEntranceToDiningRoom(4, 1);
		int entranceStudents = match.getPlayers()[1].getPlayersSchool().getEntranceStudentsNumber();
		assertEquals(6, entranceStudents);
	}

	@Test
	public void MoveStudentFromEntranceToDiningRoom2() {
		Match match = new Match(2, 1);
		Color studentColor = match.getPlayers()[1].getPlayersSchool().GetEntranceStudentColor(4);
		match.MoveStudentsFromEntranceToDiningRoom(4, 1);
		int Students = match.getPlayers()[1].getPlayersSchool().getStudentNumber(studentColor);
		assertEquals(1, Students);
	}

	@Test
	public void MoveStudentsFromEntranceToIsland() {
		Match match = new Match(2, 1);
		Color studentColor = match.getPlayers()[1].getPlayersSchool().GetEntranceStudentColor(4);
		match.MoveStudentsFromEntranceToIsland(4, 1, 6);
		int Students = match.getTable().get(6).CountStudents(studentColor);
		assertEquals(1, Students);
	}

	@Test
	public void SortPlayersByOrderValue() {
		Match match = new Match(3, 1);
		match.PlayAssistantCard(2, 0);
		match.PlayAssistantCard(0, 1);
		match.PlayAssistantCard(4, 2);
		match.SortPlayersByOrderValue();
		int orderValue = match.getPlayers()[2].GetPlayedOrderValue();
		assertEquals(5, orderValue);
	}

	@Test
	public void getTeacherByColor() {
		Match match = new Match(3, 1);
		Teacher BlueTeacher = match.getTeacherByColor(Color.BLUE);
		Color color = BlueTeacher.getTeacherColor();
		assertEquals(Color.BLUE, color);
	}

	@Test
	public void getTeachers() {
		Match match = new Match(3, 1);
		ArrayList<Teacher> teachers = new ArrayList<>();
		teachers = match.getTeachers();
		int numberOfTeachers = teachers.size();
		assertEquals(5, numberOfTeachers);
	}

	@Test
	public void getBag() {
		Match match = new Match(2, 1);
		Bag students = match.getBag();
		int numberOfStudents = students.BagSize();
		boolean correctStudentInBag = false;
		if (match.isCharacterCardOnTable(1) && match.isCharacterCardOnTable(10)) {
			correctStudentInBag = (numberOfStudents == 98);
		}
		if (match.isCharacterCardOnTable(1) ^ match.isCharacterCardOnTable(10)) {
			correctStudentInBag = (numberOfStudents == 102);
		}
		if (!match.isCharacterCardOnTable(1) && !match.isCharacterCardOnTable(10)) {
			correctStudentInBag = (numberOfStudents == 106);
		}
		assertTrue(correctStudentInBag);
	}

	@Test
	public void getPlayerId() {
		Match match = new Match(3, 1);
		int Id = match.getPlayers()[1].getPlayerId();
		assertEquals(1, Id);
	}

	@Test
	public void getPlayerByTowerColor() {
		Match match = new Match(3, 1);
		Player greyPlayer = match.getPlayerByTowerColor(TowerColor.GREY);
		int greyId = greyPlayer.getPlayerId();
		assertEquals(2, greyId);
	}

	@Test
	public void getNumberOfPlayers() {
		Match match = new Match(3, 1);
		int numberOfPlayers = match.getNumberOfPlayers();
		assertEquals(3, numberOfPlayers);
	}

	@Test
	public void getCharacterCardsOnTable() {
		Match match = new Match(3, 1);
		assertNotNull(match.getCharacterCardsOnTable()[1]);
	}

	@Test
	public void SimpleGame() {
		Match match = new Match(3, 0);
		assertNull(match.getCharacterCardsOnTable()[0]);
	}

	@Test
	public void getPlayerById() {
		Match match = new Match(3, 0);
		int id = match.getPlayers()[1].getPlayerId();
		Player player = match.getPlayers()[1];
		Player foundPlayer = match.getPlayerById(id);
		assertEquals(player, foundPlayer);
	}

	@Test
	public void isCharacterCardOnTable() {
		Match match = new Match(2, 1);
		int id = match.getCharacterCardsOnTable()[1].getIdCharacterCard();
		boolean hasCard = match.isCharacterCardOnTable(id);
		assertTrue(hasCard);
	}

	@Test
	public void isCharacterCardOnTableFalse() {
		Match match = new Match(2, 1);
		boolean hasCard = match.isCharacterCardOnTable(13);
		assertFalse(hasCard);
	}

	@Test
	public void getCharacterCardById() {
		Match match = new Match(2, 1);
		int id = match.getCharacterCardsOnTable()[1].getIdCharacterCard();
		CharacterCard characterCard = match.getCharacterCardsOnTable()[1];
		CharacterCard characterCardById = match.getCharacterCardById(id);
		assertEquals(characterCard, characterCardById);
	}

	@Test
	public void isExpertMode() {
		Match match = new Match(2, 1);
		assertTrue(match.isExpertMode());
	}

	@Test
	public void getPlaysCard6() {
		Match match = new Match(2, 1);
		assertFalse(match.getPlaysCard6());
	}

	@Test
	public void setPlaysCard6() {
		Match match = new Match(2, 1);
		match.setPlaysCard6(true);
		assertTrue(match.getPlaysCard6());
	}

	@Test
	public void AddCoinsWithStudents() {
		Match match = new Match(2, 1);
		for (int i = 0; i < 6; i++) {
			match.getPlayerById(1).getPlayersSchool().RemoveStudentFromEntrance(6 - i);
			match.getPlayerById(1).getPlayersSchool().AddEntranceStudents(Color.BLUE);
			match.MoveStudentsFromEntranceToDiningRoom(6 - i, 1);
		}
		int numberOfMoney = match.getPlayerById(1).getCoins();
		assertEquals(3, numberOfMoney);
	}
}
