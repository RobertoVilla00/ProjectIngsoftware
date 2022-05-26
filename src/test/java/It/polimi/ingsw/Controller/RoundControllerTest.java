package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.*;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundControllerTest {
	@Test
	public void setFirstActivePlayer() throws InvalidInputException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(3, 1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		assertTrue(roundController.getGameController().getMatch().getPlayers()[0].IsActive());
	}

	@Test
	public void setNextActivePlayer() throws InvalidInputException, NoActivePlayerException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(3, 1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		roundController.setNextActivePlayer();
		assertTrue(roundController.getGameController().getMatch().getPlayers()[1].IsActive());
	}

	@Test
	public void setNextActivePlayerResetter() throws InvalidInputException, NoActivePlayerException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(3, 1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		roundController.setNextActivePlayer();
		assertFalse(roundController.getGameController().getMatch().getPlayers()[0].IsActive());
	}

	@Test
	public void FinishedPlayers() throws InvalidInputException, NoActivePlayerException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		roundController.setNextActivePlayer();
		assertTrue(roundController.FinishedPlayers());
	}

	@Test
	public void FinishedPlayersFalse() throws InvalidInputException, NoActivePlayerException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		assertFalse(roundController.FinishedPlayers());
	}

	@Test
	public void StartMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		assertTrue(roundController.getGameController().getMatch().getPlayers()[0].IsActive());
	}

	@Test
	public void StartMessageWrongInput() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 2);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		assertEquals(GamePhase.GAME_INIT, roundController.getGamePhase());
	}

	@Test(expected = WrongMessageException.class)
	public void StartMessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.MOVE_MN);
		roundController.MessageHandler(startMessage);
	}

	@Test
	public void AssistantCardMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(4);
		roundController.setGamePhase(GamePhase.ASSISTANT_CARD);
		roundController.MessageHandler(assistantCardMessage);
		int orderValue = roundController.getGameController().getMatch().getPlayers()[0].GetPlayedOrderValue();
		assertEquals(4, orderValue);
	}

	@Test
	public void AssistantCardMessageWrongCard() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(-2);
		roundController.setGamePhase(GamePhase.ASSISTANT_CARD);
		roundController.MessageHandler(assistantCardMessage);
		int orderValue = roundController.getGameController().getMatch().getPlayers()[0].GetPlayedOrderValue();
		assertEquals(0, orderValue);
	}

	@Test
	public void AssistantCardLastPlayer() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(4);
		roundController.setGamePhase(GamePhase.ASSISTANT_CARD);
		roundController.setNextActivePlayer();
		roundController.MessageHandler(assistantCardMessage);
		assertTrue(roundController.getGameController().getMatch().getPlayers()[0].IsActive());
	}

	@Test(expected = WrongMessageException.class)
	public void AssistantCardMessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		AssistantCardMessage assistantCardMessage = new AssistantCardMessage(4);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(assistantCardMessage);
	}

	@Test
	public void MoveStudentMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.MOVE_STUDENT);
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2, 7);
		Color studentColor = roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().GetEntranceStudentColor(1);
		roundController.MessageHandler(moveStudentMessage);
		int numberOfStudents = roundController.getGameController().getMatch().getTable().get(6).CountStudents(studentColor);
		assertEquals(1, numberOfStudents);
	}

	@Test
	public void MoveStudentMessageWrongDestination() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.MOVE_STUDENT);
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2, 15);
		Color studentColor = roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().GetEntranceStudentColor(1);
		roundController.MessageHandler(moveStudentMessage);
		int NumberOfStudents = roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().getEntranceStudentsNumber();
		assertEquals(7, NumberOfStudents);
	}

	@Test(expected = WrongMessageException.class)
	public void MoveStudentMessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.CHOOSE_CLOUD);
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2, 7);
		roundController.MessageHandler(moveStudentMessage);
	}

	@Test
	public void MoveLastStudentOfPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.MOVE_STUDENT);
		MoveStudentMessage moveStudentMessage = new MoveStudentMessage(2, 7);
		roundController.MessageHandler(moveStudentMessage);
		roundController.MessageHandler(moveStudentMessage);
		roundController.MessageHandler(moveStudentMessage);
		GamePhase gamePhase = roundController.getGamePhase();
		assertEquals(GamePhase.MOVE_MN, gamePhase);
	}

	@Test
	public void MotherNatureMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.MOVE_MN);
		roundController.getGameController().getMatch().getPlayers()[0].PlayAssistantCard(8);
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(3);
		roundController.MessageHandler(motherNatureMessage);
		int position = roundController.getGameController().getMatch().getMotherNaturePosition();
		assertEquals(3, position);
	}

	@Test
	public void MotherNatureMessageWrongNumberOfSteps() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.MOVE_MN);
		roundController.getGameController().getMatch().getPlayers()[0].PlayAssistantCard(3);
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(10);
		roundController.MessageHandler(motherNatureMessage);
		int position = roundController.getGameController().getMatch().getMotherNaturePosition();
		assertEquals(0, position);
	}

	@Test(expected = WrongMessageException.class)
	public void MotherNatureMessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.MOVE_STUDENT);
		MotherNatureMessage motherNatureMessage = new MotherNatureMessage(3);
		roundController.MessageHandler(motherNatureMessage);
	}

	@Test
	public void ChoseCloudMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.CHOOSE_CLOUD);
		CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(1);
		roundController.MessageHandler(cloudChoiceMessage);
		int numberOfStudent = roundController.getGameController().getMatch().getClouds().get(0).CloudSize();
		assertEquals(0, numberOfStudent);
	}

	@Test
	public void ChooseCloudMessageWrongCloudIndex() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.CHOOSE_CLOUD);
		CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(0);
		roundController.MessageHandler(cloudChoiceMessage);
		int numberOfStudent = roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().getEntranceStudentsNumber();
		assertEquals(7, numberOfStudent);
	}

	@Test
	public void ChooseCloudLastPlayer() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.CHOOSE_CLOUD);
		roundController.setNextActivePlayer();
		CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(1);
		roundController.MessageHandler(cloudChoiceMessage);
		assertTrue(roundController.getGameController().getMatch().getPlayers()[0].IsActive());
	}

	@Test(expected = WrongMessageException.class)
	public void ChoseCloudMessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.CHARACTER_CARD);
		CloudChoiceMessage cloudChoiceMessage = new CloudChoiceMessage(1);
		roundController.MessageHandler(cloudChoiceMessage);
	}

	@Test
	public void CharacterCardMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (roundController.getGameController().getMatch().getCharacterCardsOnTable()[1].getIdCharacterCard() != 10) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayers()[0].setActive();
		roundController.getGameController().getMatch().getPlayers()[0].AddCoin(2);
		roundController.setGamePhase(GamePhase.MOVE_MN);
		CharacterCardMessage characterCardMessage = new CharacterCardMessage(2);
		roundController.MessageHandler(characterCardMessage);
		GamePhase phase = roundController.getGamePhase();
		assertEquals(GamePhase.CHARACTER_CARD, phase);
	}

	@Test
	public void CharacterCardMessageNoEntryTiles() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (roundController.getGameController().getMatch().getCharacterCardsOnTable()[1].getIdCharacterCard() != 5) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayers()[0].setActive();
		roundController.getGameController().getMatch().getPlayers()[0].AddCoin(2);
		Card5 card = (Card5) roundController.getGameController().getMatch().getCharacterCardById(5);
		for (int i = 0; i < 4; i++) {
			card.RemoveNoEntryTile();
		}
		roundController.setGamePhase(GamePhase.MOVE_MN);
		CharacterCardMessage characterCardMessage = new CharacterCardMessage(2);
		roundController.MessageHandler(characterCardMessage);
		Card3and5Message card3and5Message = new Card3and5Message(4);
		roundController.MessageHandler(card3and5Message);
		int numberOfTiles = card.getNoEntryTilesOnCard();
		assertEquals(0, numberOfTiles);
	}

	@Test(expected = WrongMessageException.class)
	public void CharacterCardMessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (roundController.getGameController().getMatch().getCharacterCardsOnTable()[1].getIdCharacterCard() != 10) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayers()[0].AddCoin(2);
		roundController.setGamePhase(GamePhase.ASSISTANT_CARD);
		CharacterCardMessage cardMessage = new CharacterCardMessage(1);
		roundController.MessageHandler(cardMessage);
	}

	@Test
	public void CharacterCardMessageWrongMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.getGameController().getMatch().getPlayers()[0].AddCoin(2);
		roundController.setGamePhase(GamePhase.MOVE_MN);
		CharacterCardMessage cardMessage = new CharacterCardMessage(15);
		roundController.MessageHandler(cardMessage);
		assertEquals(GamePhase.MOVE_MN, roundController.getGamePhase());
	}

	@Test
	public void CharacterCardMessageSecondCard() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(1)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.setGamePhase(GamePhase.CHARACTER_CARD);
		Card1Message card1Message = new Card1Message(2, 7);
		Cards1and10 card = (Cards1and10) roundController.getGameController().getMatch().getCharacterCardById(1);
		Color studentColor = card.GetStudentColor(1);
		roundController.setExpectedCardMsg(1);
		roundController.MessageHandler(card1Message);
		roundController.setGamePhase(GamePhase.MOVE_STUDENT);
		CharacterCardMessage cardMessage = new CharacterCardMessage(1);
		roundController.MessageHandler(cardMessage);
		GamePhase phase = roundController.getGamePhase();
		assertNotEquals(GamePhase.CHARACTER_CARD, phase);
	}

	@Test
	public void Card1Message() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(1)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.setGamePhase(GamePhase.CHARACTER_CARD);
		Card1Message card1Message = new Card1Message(2, 7);
		Cards1and10 card = (Cards1and10) roundController.getGameController().getMatch().getCharacterCardById(1);
		Color studentColor = card.GetStudentColor(1);
		roundController.setExpectedCardMsg(1);
		roundController.MessageHandler(card1Message);
		int NumberOfStudents = roundController.getGameController().getMatch().getTable().get(6).CountStudents(studentColor);
		assertEquals(1, NumberOfStudents);
	}

	@Test(expected = WrongMessageException.class)
	public void card1MessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(1)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.setGamePhase(GamePhase.MOVE_MN);
		Card1Message card1Message = new Card1Message(2, 7);
		roundController.MessageHandler(card1Message);
	}

	@Test
	public void card3Message() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(3)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.setExpectedCardMsg(3);
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.setGamePhase(GamePhase.CHARACTER_CARD);
		Card3and5Message card3and5Message = new Card3and5Message(7);
		roundController.getGameController().getMatch().getPlayerById(roundController.getGameController().getActivePlayer()).AddCoin(2);
		roundController.getGameController().getMatch().getPlayers()[0].setAdditionalPoints(true);
		roundController.MessageHandler(card3and5Message);
		assertEquals(TowerColor.WHITE, roundController.getGameController().getMatch().getTable().get(6).getTowersColor());
	}

	@Test(expected = WrongMessageException.class)
	public void card3MessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(3)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.setExpectedCardMsg(3);
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.setGamePhase(GamePhase.MOVE_STUDENT);
		Card3and5Message card3and5Message = new Card3and5Message(7);
		roundController.MessageHandler(card3and5Message);
	}

	@Test
	public void card5Message() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(5)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.setExpectedCardMsg(5);
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.setGamePhase(GamePhase.CHARACTER_CARD);
		Card3and5Message card3and5Message = new Card3and5Message(7);
		roundController.getGameController().getMatch().getPlayerById(roundController.getGameController().getActivePlayer()).AddCoin(2);
		roundController.MessageHandler(card3and5Message);
		assertTrue(roundController.getGameController().getMatch().getTable().get(6).GetNoEntryTile());
	}

	@Test
	public void Card10Message() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(10)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.getGameController().getMatch().getPlayerById(roundController.getGameController().getActivePlayer()).AddCoin(2);
		roundController.setExpectedCardMsg(10);
		roundController.setGamePhase(GamePhase.CHARACTER_CARD);
		Card10Message card10Message = new Card10Message(2);
		Cards1and10 cards1and10 = (Cards1and10) roundController.getGameController().getMatch().getCharacterCardById(10);
		Color studentColor = cards1and10.GetStudentColor(1);
		roundController.MessageHandler(card10Message);
		int numberOfStudents = roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().getStudentNumber(studentColor);
		assertEquals(1, numberOfStudents);
	}

	@Test(expected = WrongMessageException.class)
	public void Card10MessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(10)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.getGameController().getMatch().getPlayerById(roundController.getGameController().getActivePlayer()).AddCoin(2);
		roundController.setExpectedCardMsg(10);
		roundController.setGamePhase(GamePhase.CHOOSE_CLOUD);
		Card10Message card10Message = new Card10Message(2);
		roundController.MessageHandler(card10Message);
	}

	@Test
	public void card12Message() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(12)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.getGameController().getMatch().getPlayerById(roundController.getGameController().getActivePlayer()).AddCoin(2);
		roundController.setExpectedCardMsg(12);
		roundController.setGamePhase(GamePhase.CHARACTER_CARD);
		Card12Message card12Message = new Card12Message("BLUE");
		roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
		roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().AddStudentToDiningRoom(Color.BLUE);
		roundController.MessageHandler(card12Message);
		int numberOfStudents = roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().getStudentNumber(Color.BLUE);
		assertEquals(0, numberOfStudents);
	}

	@Test(expected = WrongMessageException.class)
	public void card12MessageWrongPhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		while (!roundController.getGameController().getMatch().isCharacterCardOnTable(12)) {
			roundController.getGameController().InitializeGame(startMessage);
		}
		roundController.getGameController().getMatch().getPlayerById(0).setActive();
		roundController.getGameController().getMatch().getPlayerById(roundController.getGameController().getActivePlayer()).AddCoin(2);
		roundController.setExpectedCardMsg(12);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		Card12Message card12Message = new Card12Message("BLUE");
		roundController.MessageHandler(card12Message);
	}

	@Test
	public void GamePhase() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		GamePhase phase = roundController.getGameController().getMatch().getGamePhase();
		assertEquals(phase, GamePhase.ASSISTANT_CARD);
	}
}
