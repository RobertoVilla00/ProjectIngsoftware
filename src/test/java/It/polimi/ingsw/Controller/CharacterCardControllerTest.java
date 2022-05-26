package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.NoEntryTilesException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.Cards1and10;
import It.polimi.ingsw.Model.Color;
import It.polimi.ingsw.Model.TowerColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharacterCardControllerTest {
	@Test
	public void PlayCard1CardStudents() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(1)) {
			controller.InitializeGame(startMessage);
		}
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card1Message card1Message = new Card1Message(3, 7);
		controller.getMatch().getPlayerById(0).setActive();
		characterCardController.PlayCard1(card1Message);
		Cards1and10 cards1and10 = (Cards1and10) controller.getMatch().getCharacterCardById(1);
		int studentsNumber = cards1and10.getNumberOfStudents();
		assertEquals(4, studentsNumber);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard1NoCoins() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(1)) {
			controller.InitializeGame(startMessage);
		}
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card1Message card1Message = new Card1Message(3, 7);
		controller.getMatch().getPlayerById(0).setActive();
		controller.getMatch().getPlayerById(controller.getActivePlayer()).RemoveCoins(1);
		characterCardController.PlayCard1(card1Message);
	}

	@Test
	public void PlayCard1Bag() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(1)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		int studentsNumber = controller.getMatch().getBag().BagSize();
		Card1Message card1Message = new Card1Message(3, 7);
		Cards1and10 cards1and10 = (Cards1and10) controller.getMatch().getCharacterCardById(1);
		characterCardController.PlayCard1(card1Message);
		assertEquals(studentsNumber - 1, controller.getMatch().getBag().BagSize());
	}

	@Test
	public void PlayCard1Island() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(1)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card1Message card1Message = new Card1Message(3, 7);
		Cards1and10 cards1and10 = (Cards1and10) controller.getMatch().getCharacterCardById(1);
		Color studentColor = cards1and10.GetStudentColor(2);
		characterCardController.PlayCard1(card1Message);
		int studentsNumber = controller.getMatch().getTable().get(6).CountStudents(studentColor);
		assertEquals(1, studentsNumber);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard1WrongCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (controller.getMatch().isCharacterCardOnTable(1)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card1Message card1Message = new Card1Message(3, 7);
		characterCardController.PlayCard1(card1Message);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard1WrongStudent() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(1)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card1Message card1Message = new Card1Message(5, 7);
		characterCardController.PlayCard1(card1Message);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard1WrongIsland() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(1)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card1Message card1Message = new Card1Message(4, 15);
		characterCardController.PlayCard1(card1Message);
	}

	@Test
	public void PlayCard3() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(3)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3Message = new Card3and5Message(1);
		controller.getMatch().getPlayers()[0].setAdditionalPoints(true);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard3(card3Message);
		assertEquals(TowerColor.WHITE, controller.getMatch().getTable().get(0).getTowersColor());
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard3NoCoins() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(3)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3Message = new Card3and5Message(1);
		controller.getMatch().getPlayers()[0].setAdditionalPoints(true);
		characterCardController.PlayCard3(card3Message);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard3WrongCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (controller.getMatch().isCharacterCardOnTable(3)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3Message = new Card3and5Message(1);
		controller.getMatch().getPlayers()[0].setAdditionalPoints(true);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard3(card3Message);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard3WrongIsland() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(3)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3Message = new Card3and5Message(18);
		controller.getMatch().getPlayers()[0].setAdditionalPoints(true);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard3(card3Message);
	}

	@Test
	public void PlayCard4() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(4)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		characterCardController.PlayCard4();
		int maximumMovements = controller.getMatch().getPlayerById(controller.getActivePlayer()).GetPlayedMovements();
		assertEquals(2, maximumMovements);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard4NoCoins() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(4)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).RemoveCoins(1);
		characterCardController.PlayCard4();
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard4WrongCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (controller.getMatch().isCharacterCardOnTable(4)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		characterCardController.PlayCard4();
	}

	@Test
	public void PlayCard5() throws InvalidInputException, NoEntryTilesException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(5)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3and5Message = new Card3and5Message(1);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard5(card3and5Message);
		assertTrue(controller.getMatch().getTable().get(0).GetNoEntryTile());
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard5NoCoins() throws InvalidInputException, NoEntryTilesException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(5)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3and5Message = new Card3and5Message(1);
		characterCardController.PlayCard5(card3and5Message);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard5WrongCard() throws InvalidInputException, NoEntryTilesException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (controller.getMatch().isCharacterCardOnTable(5)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3and5Message = new Card3and5Message(1);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard5(card3and5Message);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard5WrongIsland() throws InvalidInputException, NoEntryTilesException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(5)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3and5Message = new Card3and5Message(13);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard5(card3and5Message);
	}

	@Test(expected = NoEntryTilesException.class)
	public void PlayCard5NoTilesLeft() throws InvalidInputException, NoEntryTilesException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(5)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card3and5Message card3and5Message = new Card3and5Message(1);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard5(card3and5Message);
		Card3and5Message card3and5Message1 = new Card3and5Message(3);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard5(card3and5Message);
		Card3and5Message card3and5Message2 = new Card3and5Message(5);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard5(card3and5Message);
		Card3and5Message card3and5Message3 = new Card3and5Message(10);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard5(card3and5Message);
		Card3and5Message card3and5Message4 = new Card3and5Message(11);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard5(card3and5Message);
	}

	@Test
	public void PlayCard6() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(6)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard6();
		assertTrue(controller.getMatch().getPlaysCard6());
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard6NoCoins() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(6)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		characterCardController.PlayCard6();
		assertTrue(controller.getMatch().getPlaysCard6());
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard6NoCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (controller.getMatch().isCharacterCardOnTable(6)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard6();
	}

	@Test
	public void PlayCard9() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(9)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard9();
		boolean bonusPoints = controller.getMatch().getPlayerById(controller.getActivePlayer()).getAdditionalPoints();
		assertTrue(bonusPoints);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard9NoCoins() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(9)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		characterCardController.PlayCard9();
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard9NoCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (controller.getMatch().isCharacterCardOnTable(9)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard9();
	}

	@Test
	public void PlayCard10() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(10)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Cards1and10 card10 = (Cards1and10) controller.getMatch().getCharacterCardById(10);
		Color studentColor = card10.GetStudentColor(1);
		Card10Message card10Message = new Card10Message(2);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard10(card10Message);
		int studentsNumber = controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getStudentNumber(studentColor);
		assertEquals(1, studentsNumber);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard10NoCoins() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(10)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Cards1and10 card10 = (Cards1and10) controller.getMatch().getCharacterCardById(10);
		Color studentColor = card10.GetStudentColor(1);
		Card10Message card10Message = new Card10Message(2);
		characterCardController.PlayCard10(card10Message);
		int studentsNumber = controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().getStudentNumber(studentColor);
		assertEquals(1, studentsNumber);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard10FullDiningRoom() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(10)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Cards1and10 card10 = (Cards1and10) controller.getMatch().getCharacterCardById(10);
		for (int i = 0; i < 10; i++) {
			controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
			controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.RED);
			controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.GREEN);
			controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
			controller.getMatch().getPlayerById(controller.getActivePlayer()).getPlayersSchool().AddStudentToDiningRoom(Color.PINK);
		}
		Card10Message card10Message = new Card10Message(2);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard10(card10Message);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard10NoCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (controller.getMatch().isCharacterCardOnTable(10)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card10Message card10Message = new Card10Message(2);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard10(card10Message);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard10WrongStudent() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(10)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card10Message card10Message = new Card10Message(6);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard10(card10Message);
	}

	@Test
	public void PlayCard12() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(12)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card12Message card12Message = new Card12Message("YElloW");
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.PINK);
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard12(card12Message);
		int yellowStudents = controller.getMatch().getPlayers()[0].getPlayersSchool().getStudentNumber(Color.YELLOW);
		assertEquals(0, yellowStudents);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard12NoCoins() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (!controller.getMatch().isCharacterCardOnTable(12)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card12Message card12Message = new Card12Message("YElloW");
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.PINK);
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		characterCardController.PlayCard12(card12Message);
		int yellowStudents = controller.getMatch().getPlayers()[0].getPlayersSchool().getStudentNumber(Color.YELLOW);
		assertEquals(0, yellowStudents);
	}

	@Test(expected = InvalidInputException.class)
	public void PlayCard12NoCard() throws InvalidInputException, NoActivePlayerException {
		GameController controller = new GameController();
		StartMessage startMessage = new StartMessage(3, 1);
		controller.InitializeGame(startMessage);
		while (controller.getMatch().isCharacterCardOnTable(12)) {
			controller.InitializeGame(startMessage);
		}
		controller.getMatch().getPlayerById(0).setActive();
		CharacterCardController characterCardController = new CharacterCardController(controller);
		Card12Message card12Message = new Card12Message("PiNk");
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.YELLOW);
		controller.getMatch().getPlayerById(controller.getActivePlayer()).AddCoin(2);
		characterCardController.PlayCard12(card12Message);
	}
}
