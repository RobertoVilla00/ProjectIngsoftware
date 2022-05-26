package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.NoEntryTilesException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameControllerTest {

	@Test(expected = InvalidInputException.class)
	public void InitializeGame() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(1, 1);
		controller.InitializeGame(startMessage);
	}

	@Test(expected = InvalidInputException.class)
	public void InitializeGame1() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 4);
		controller.InitializeGame(startMessage);
	}

	@Test
	public void CheckIslandMergeWithNoTower() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.CheckIslandMerge(1);
		assertEquals(controller.getMatch().getTable().size(), 12);
	}

	@Test
	public void CheckIslandMergeWithAdjacentIndexAndSameTowerColor() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getTable().get(4).BuildTower(TowerColor.BLACK);
		controller.getMatch().getTable().get(5).BuildTower(TowerColor.BLACK);
		controller.CheckIslandMerge(5);
		assertEquals(controller.getMatch().getTable().size(), 11);
	}

	@Test
	public void CheckIslandMergeWithAdjacentIndexAndNotSameTowerColor() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getTable().get(4).BuildTower(TowerColor.BLACK);
		controller.getMatch().getTable().get(5).BuildTower(TowerColor.WHITE);
		controller.CheckIslandMerge(4);
		assertEquals(controller.getMatch().getTable().size(), 12);
	}

	@Test
	public void CheckIslandMergeWithNotAdjacentIndexAndSameTowerColor() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getTable().get(2).BuildTower(TowerColor.GREY);
		controller.getMatch().getTable().get(8).BuildTower(TowerColor.GREY);
		controller.CheckIslandMerge(2);
		assertEquals(controller.getMatch().getTable().size(), 12);
	}

	@Test
	public void CheckIslandMergeWithFirstAndLastIndexAndSameTowerColor() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getTable().get(0).BuildTower(TowerColor.GREY);
		controller.getMatch().getTable().get(11).BuildTower(TowerColor.GREY);
		controller.CheckIslandMerge(0);
		assertEquals(controller.getMatch().getTable().size(), 11);
	}

	@Test
	public void CheckIslandMergeWithFirstAndLastIndexAndSameTowerColor1() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getTable().get(0).BuildTower(TowerColor.GREY);
		controller.getMatch().getTable().get(11).BuildTower(TowerColor.GREY);
		controller.CheckIslandMerge(11);
		assertEquals(controller.getMatch().getTable().size(), 11);
	}

	@Test
	public void CheckIslandMergeWithNotFirstAndLastIndexAndSameTowerColor2() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getTable().get(10).BuildTower(TowerColor.GREY);
		controller.getMatch().getTable().get(11).BuildTower(TowerColor.GREY);
		controller.CheckIslandMerge(11);
		assertEquals(controller.getMatch().getTable().size(), 11);
	}


	@Test
	public void CheckTeacherControl() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
		controller.CheckTeacherControl(Color.BLUE, controller.getMatch().getPlayers()[0]);
		Teacher teacher = controller.getMatch().getTeacherByColor(Color.BLUE);
		Player controllingPlayer = teacher.getControllingPlayer();
		assertEquals(controller.getMatch().getPlayers()[0], controllingPlayer);
	}

	@Test
	public void CheckTeacherControlWithDraw() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
		controller.CheckTeacherControl(Color.BLUE, controller.getMatch().getPlayers()[0]);
		controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
		controller.CheckTeacherControl(Color.BLUE, controller.getMatch().getPlayers()[1]);
		Teacher teacher = controller.getMatch().getTeacherByColor(Color.BLUE);
		Player controllingPlayer = teacher.getControllingPlayer();
		assertEquals(controller.getMatch().getPlayers()[0], controllingPlayer);
	}

	@Test
	public void CheckTeacherControlWithChange() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
		controller.CheckTeacherControl(Color.BLUE, controller.getMatch().getPlayers()[0]);
		controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
		controller.CheckTeacherControl(Color.BLUE, controller.getMatch().getPlayers()[1]);
		controller.getMatch().getPlayers()[1].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
		controller.CheckTeacherControl(Color.BLUE, controller.getMatch().getPlayers()[1]);
		Teacher teacher = controller.getMatch().getTeacherByColor(Color.BLUE);
		Player controllingPlayer = teacher.getControllingPlayer();
		assertEquals(controller.getMatch().getPlayers()[1], controllingPlayer);
	}

	@Test(expected = InvalidInputException.class)
	public void MoveStudentWithInvalidDestination() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2, -2);
		controller.MoveStudent(moveStudentMessage);
	}

	@Test(expected = InvalidInputException.class)
	public void MoveStudentToDiningRoomFull() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddEntranceStudents(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(10, 0);
		controller.MoveStudent(moveStudentMessage);
	}

	@Test(expected = InvalidInputException.class)
	public void MoveStudentWithInvalidStudentIndex() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(-1, 0);
		controller.MoveStudent(moveStudentMessage);
	}

	@Test
	public void MoveStudent() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		Color studentColor = controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().GetEntranceStudentColor(1);
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2, 0);
		controller.MoveStudent(moveStudentMessage);
		int numberOfStudents = controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getEntranceStudentsNumber();
		assertEquals(8, numberOfStudents);
	}

	@Test
	public void MoveStudentToDiningRoom() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		Color studentColor = controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().GetEntranceStudentColor(1);
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2, 0);
		controller.MoveStudent(moveStudentMessage);
		int numberOfStudents = controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getStudentNumber(studentColor);
		assertEquals(1, numberOfStudents);
	}

	@Test
	public void MoveStudentToIsland() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		Color studentColor = controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().GetEntranceStudentColor(1);
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2, 7);
		controller.MoveStudent(moveStudentMessage);
		int numberOfStudents = controller.getMatch().getTable().get(6).CountStudents(studentColor);
		assertEquals(1, numberOfStudents);
	}

	@Test(expected = InvalidInputException.class)
	public void MoveMotherNatureWith0Steps() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(0);
		controller.MoveMotherNature(motherNatureMessage);
	}

	@Test(expected = InvalidInputException.class)
	public void MoveMotherNatureWithNegativeSteps() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(-1);
		controller.MoveMotherNature(motherNatureMessage);
	}

	@Test(expected = InvalidInputException.class)
	public void MoveMotherNatureWithTooManySteps() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(1);
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(4);
		controller.MoveMotherNature(motherNatureMessage);
	}

	@Test
	public void MoveMotherNatureWithCorrectSteps() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		controller.getMatch().getPlayerById(controller.getActivePlayer()).PlayAssistantCard(6); // 4 movements
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(4);
		controller.MoveMotherNature(motherNatureMessage);
		assertEquals(4, controller.getMatch().getMotherNaturePosition());
	}

	@Test(expected = InvalidInputException.class)
	public void ChooseCloudInvalidCloudIndex() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(4);
		controller.ChooseCloud(cloudChoiceMessage);
	}

	@Test
	public void ChooseCloud() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().PlanningPhase();
		controller.getMatch().getPlayerById(0).setActive();
		CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(3);
		controller.ChooseCloud(cloudChoiceMessage);
		int studentsOnCloud = controller.getMatch().getClouds().get(2).CloudSize();
		assertEquals(0, studentsOnCloud);
	}

	@Test
	public void ChooseCloud2() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().PlanningPhase();
		controller.getMatch().getPlayerById(0).setActive();
		CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(3);
		controller.ChooseCloud(cloudChoiceMessage);
		int entranceStudents = controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getEntranceStudentsNumber();
		assertEquals(13, entranceStudents);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayAssistantCardNegativeIndex() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(-1);
		controller.PlayAssistantCard(assistantCardMessage);

	}

	@Test(expected = InvalidInputException.class)
	public void PlayAssistantCardIndexGreaterThenCardCount() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(11);
		controller.PlayAssistantCard(assistantCardMessage);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayAssistantCardTwoPlayerWithSameValue() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage1 = new AssistantCardMessage(6);
		controller.PlayAssistantCard(assistantCardMessage1);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(6);
		controller.PlayAssistantCard(assistantCardMessage);
	}

	@Test
	public void PlayAssistantCardTwoPlayerWithDifferentValue() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage1 = new AssistantCardMessage(6);
		controller.PlayAssistantCard(assistantCardMessage1);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(7);
		controller.PlayAssistantCard(assistantCardMessage);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayAssistantCardTwoPlayerWithDifferentValue1() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage1 = new AssistantCardMessage(10);
		controller.PlayAssistantCard(assistantCardMessage1);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(7);
		controller.PlayAssistantCard(assistantCardMessage);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage2 = new AssistantCardMessage(9);
		controller.PlayAssistantCard(assistantCardMessage2);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage3 = new AssistantCardMessage(8);
		controller.PlayAssistantCard(assistantCardMessage3);
		controller.getMatch().getPlayerById(1).setInactive();
	}

	@Test(expected = InvalidInputException.class)
	public void PlayAssistantCardThreePlayerSameValue() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(6);
		controller.PlayAssistantCard(assistantCardMessage);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage1 = new AssistantCardMessage(7);
		controller.PlayAssistantCard(assistantCardMessage1);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(2).setActive();
		AssistantCardMessage assistantCardMessage2 = new AssistantCardMessage(6);
		controller.PlayAssistantCard(assistantCardMessage2);
	}

	@Test
	public void PlayAssistantCardTwoPlayerWithValue() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(2);
		controller.PlayAssistantCard(assistantCardMessage);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage1 = new AssistantCardMessage(3);
		controller.PlayAssistantCard(assistantCardMessage1);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage2 = new AssistantCardMessage(3);
		controller.PlayAssistantCard(assistantCardMessage2);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage3 = new AssistantCardMessage(4);
		controller.PlayAssistantCard(assistantCardMessage3);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage4 = new AssistantCardMessage(4);
		controller.PlayAssistantCard(assistantCardMessage4);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage5 = new AssistantCardMessage(5);
		controller.PlayAssistantCard(assistantCardMessage5);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage6 = new AssistantCardMessage(5);
		controller.PlayAssistantCard(assistantCardMessage6);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage7 = new AssistantCardMessage(6);
		controller.PlayAssistantCard(assistantCardMessage7);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage8 = new AssistantCardMessage(5);
		controller.PlayAssistantCard(assistantCardMessage8);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage9 = new AssistantCardMessage(4);
		controller.PlayAssistantCard(assistantCardMessage9);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage10 = new AssistantCardMessage(4);
		controller.PlayAssistantCard(assistantCardMessage10);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage11 = new AssistantCardMessage(3);
		controller.PlayAssistantCard(assistantCardMessage11);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage12 = new AssistantCardMessage(3);
		controller.PlayAssistantCard(assistantCardMessage12);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage13 = new AssistantCardMessage(2);
		controller.PlayAssistantCard(assistantCardMessage13);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage14 = new AssistantCardMessage(1);
		controller.PlayAssistantCard(assistantCardMessage14);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage15 = new AssistantCardMessage(2);
		controller.PlayAssistantCard(assistantCardMessage15);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage16 = new AssistantCardMessage(1);
		controller.PlayAssistantCard(assistantCardMessage16);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage17 = new AssistantCardMessage(1);
		controller.PlayAssistantCard(assistantCardMessage17);
		controller.getMatch().getPlayerById(1).setInactive();
		controller.getMatch().getPlayerById(0).setActive();
		AssistantCardMessage assistantCardMessage18 = new AssistantCardMessage(1);
		controller.PlayAssistantCard(assistantCardMessage18);
		controller.getMatch().getPlayerById(0).setInactive();
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage19 = new AssistantCardMessage(1);
		controller.PlayAssistantCard(assistantCardMessage19);
		controller.getMatch().getPlayerById(1).setInactive();
	}

	@Test
	public void getActivePlayer() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(1).setActive();
		assertEquals(1, controller.getActivePlayer());
	}

	@Test(expected = NoActivePlayerException.class)
	public void getActivePlayerWithNoPlayer() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 1);
		controller.InitializeGame(startMessage);
		controller.getActivePlayer();
	}

	@Test
	public void getActivePlayerPosition() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(1).setActive();
		assertEquals(1, controller.getActivePlayerPosition());
	}

	@Test(expected = NoActivePlayerException.class)
	public void getActivePlayerPositionWithNoPlayer() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 1);
		controller.InitializeGame(startMessage);
		controller.getActivePlayerPosition();
	}

	@Test
	public void FillClouds() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.FillClouds();
		int numberOfStudents = controller.getMatch().getClouds().get(2).CloudSize();
		assertEquals(4, numberOfStudents);
	}

	@Test
	public void PlayCharacterCard4() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(4)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 4) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardMessage cardMessage = new CharacterCardMessage(position + 1);
		controller.PlayCharacterCard(cardMessage);
		int maximumMovements = controller.getMatch().getPlayerById(controller.getActivePlayer()).GetPlayedMovements();
		assertEquals(2, maximumMovements);
	}

	@Test
	public void PlayCharacterCard6() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(6)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 6) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardMessage cardMessage = new CharacterCardMessage(position + 1);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		controller.PlayCharacterCard(cardMessage);
		assertTrue(controller.getMatch().getPlaysCard6());
	}

	@Test
	public void PlayCharacterCard9() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(9)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 9) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardMessage cardMessage = new CharacterCardMessage(position + 1);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		controller.PlayCharacterCard(cardMessage);
		boolean bonusPoints = controller.getMatch().getPlayerById(controller.getActivePlayer()).getAdditionalPoints();
		assertTrue(bonusPoints);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayInvalidCharacterCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardMessage cardMessage = new CharacterCardMessage(-2);
		controller.PlayCharacterCard(cardMessage);
	}

	@Test
	public void EndGame() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayers()[0].IncreaseTowersPlaced(3);
		controller.getMatch().getPlayers()[1].IncreaseTowersPlaced(5);
		controller.getMatch().getPlayers()[2].IncreaseTowersPlaced(4);
		Integer winnerId = controller.EndGame().get(0);
		int winner=winnerId;
		assertEquals(1, winner);
	}

	@Test
	public void EndGameZeroStudent() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 0);
		controller.InitializeGame(startMessage);
		assertEquals(106, controller.getMatch().getBag().BagSize());
		controller.getMatch().getPlayers()[0].IncreaseTowersPlaced(2);
		int size = controller.getMatch().getBag().BagSize();
		for (int i = 0; i < size; i++) {
			controller.getMatch().getBag().RemoveFirstElement();
		}
		//assertEquals(0,controller.getMatch().getBag().BagSize());
		controller.checkEndGame();
		int winner=controller.EndGame().get(0);
		assertEquals(0, winner);
	}

	@Test
	public void EndGameFinishedAssistantCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(1).setActive();
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(10);
		controller.PlayAssistantCard(assistantCardMessage);
		AssistantCardMessage assistantCardMessage1 = new AssistantCardMessage(9);
		controller.PlayAssistantCard(assistantCardMessage1);
		AssistantCardMessage assistantCardMessage2 = new AssistantCardMessage(8);
		controller.PlayAssistantCard(assistantCardMessage2);
		AssistantCardMessage assistantCardMessage3 = new AssistantCardMessage(7);
		controller.PlayAssistantCard(assistantCardMessage3);
		AssistantCardMessage assistantCardMessage4 = new AssistantCardMessage(6);
		controller.PlayAssistantCard(assistantCardMessage4);
		AssistantCardMessage assistantCardMessage5 = new AssistantCardMessage(5);
		controller.PlayAssistantCard(assistantCardMessage5);
		AssistantCardMessage assistantCardMessage6 = new AssistantCardMessage(4);
		controller.PlayAssistantCard(assistantCardMessage6);
		AssistantCardMessage assistantCardMessage7 = new AssistantCardMessage(3);
		controller.PlayAssistantCard(assistantCardMessage7);
		AssistantCardMessage assistantCardMessage8 = new AssistantCardMessage(2);
		controller.PlayAssistantCard(assistantCardMessage8);
		AssistantCardMessage assistantCardMessage9 = new AssistantCardMessage(1);
		controller.PlayAssistantCard(assistantCardMessage9);
		controller.getMatch().getPlayers()[0].IncreaseTowersPlaced(2);
		controller.checkEndGame();
		int winner=controller.EndGame().get(0);
		assertEquals(0, winner);
	}

	@Test
	public void EndGameEightTowerPlaced() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(2, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayers()[0].IncreaseTowersPlaced(8);
		controller.checkEndGame();
		int winner=controller.EndGame().get(0);
		assertEquals(0, winner);
	}

	@Test
	public void EndGameSixTowerPlaced() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayers()[0].IncreaseTowersPlaced(6);
		controller.checkEndGame();
		int winner=controller.EndGame().get(0);
		assertEquals(0, winner);
	}

	@Test
	public void PlayDefaultCharacterCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(5)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 5) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardMessage cardMessage = new CharacterCardMessage(position + 1);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		controller.PlayCharacterCard(cardMessage);
		assertEquals(3, controller.getMatch().getPlayerById(controller.getActivePlayer()).getCoins());
	}

	@Test
	public void PlayCharacterCardId1() throws InvalidInputException, NoActivePlayerException, NoEntryTilesException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(1)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 1) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		Card1Message card1Message = new Card1Message(1, 2);
		controller.PlayCharacterCardById(1, card1Message);
	}

	@Test
	public void PlayCharacterCardId3() throws InvalidInputException, NoActivePlayerException, NoEntryTilesException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(3)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 3) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		Card3and5Message card3Message = new Card3and5Message(2);
		controller.PlayCharacterCardById(3, card3Message);
		assertEquals(0, controller.getMatch().getPlayerById(controller.getActivePlayer()).getCoins());
	}

	@Test
	public void PlayCharacterCardId5() throws InvalidInputException, NoActivePlayerException, NoEntryTilesException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(5)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 5) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		Card3and5Message card5Message = new Card3and5Message(2);
		controller.PlayCharacterCardById(5, card5Message);
		assertEquals(1, controller.getMatch().getPlayerById(controller.getActivePlayer()).getCoins());
	}

	@Test
	public void PlayCharacterCardId10() throws InvalidInputException, NoActivePlayerException, NoEntryTilesException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(10)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 10) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		Card10Message card10Message = new Card10Message(2);
		controller.PlayCharacterCardById(10, card10Message);
		assertEquals(1, controller.getMatch().getPlayerById(controller.getActivePlayer()).getCoins());
	}

	@Test
	public void PlayCharacterCardId12() throws InvalidInputException, NoActivePlayerException, NoEntryTilesException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(12)) {
			controller.InitializeGame(startMessage);
		}
		int position = 0;
		for (int i = 0; i < 3; i++) {
			if (controller.getMatch().getCharacterCardsOnTable()[i].getIdCharacterCard() == 12) {
				position = i;
				break;
			}
		}
		controller.getMatch().getPlayerById(0).setActive();
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		Card12Message card12Message = new Card12Message("Blue");
		controller.PlayCharacterCardById(12, card12Message);
		assertEquals(0, controller.getMatch().getPlayerById(controller.getActivePlayer()).getCoins());
	}

	@Test
	public void getTeachersOfPlayer() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		MoveStudentMessage moveStudentMessage= new MoveStudentMessage(1,0);
		controller.getMatch().getPlayerById(0).setActive();
		controller.MoveStudent(moveStudentMessage);
		Player startingPlayer=controller.getMatch().getPlayerById(controller.getActivePlayer());
		int numberOfTeachers=controller.getMatch().getTeachersOfPlayer(startingPlayer);
		assertEquals(numberOfTeachers,1);
	}

	@Test
	public void EndGameTie() throws InvalidInputException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		controller.getMatch().getPlayerById(0).IncreaseTowersPlaced(2);
		controller.getMatch().getPlayerById(1).IncreaseTowersPlaced(2);
		controller.getMatch().getPlayerById(2).IncreaseTowersPlaced(2);
		controller.getMatch().getTeachers().get(0).ChangeController(controller.getMatch().getPlayerById(0));
		controller.getMatch().getTeachers().get(1).ChangeController(controller.getMatch().getPlayerById(1));
		Integer winner2 = controller.EndGame().get(1);
		int winner = winner2;
		assertEquals(0, winner);
	}

		@Test
		public void EndGameTie2() throws InvalidInputException {
			GameController controller = new GameController();
			StartMessage startMessage = new StartMessage(2, 1);
			controller.InitializeGame(startMessage);
			controller.getMatch().getPlayerById(0).IncreaseTowersPlaced(2);
			controller.getMatch().getPlayerById(1).IncreaseTowersPlaced(2);
			controller.getMatch().getTeachers().get(0).ChangeController(controller.getMatch().getPlayerById(0));
			Integer winner2=controller.EndGame().get(0);
			int winner= winner2;
			assertEquals(0,winner);
	}
}
