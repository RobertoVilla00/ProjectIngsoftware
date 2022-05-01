package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Model.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundControllerTest {
	@Test
	public void setFirstActivePlayer() throws InvalidInputException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(3,1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		assertTrue(roundController.getGameController().getMatch().getPlayers()[0].IsActive());
	}

	@Test
	public void setNextActivePlayer() throws InvalidInputException, NoActivePlayerException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(3,1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		roundController.setNextActivePlayer();
		assertTrue(roundController.getGameController().getMatch().getPlayers()[1].IsActive());
	}

	@Test
	public void setNextActivePlayerResetter() throws InvalidInputException, NoActivePlayerException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(3,1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		roundController.setNextActivePlayer();
		assertFalse(roundController.getGameController().getMatch().getPlayers()[0].IsActive());
	}

	@Test
	public void FinishedPlayers() throws InvalidInputException, NoActivePlayerException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(2,1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		roundController.setNextActivePlayer();
		assertTrue(roundController.FinishedPlayers());
	}
	@Test
	public void FinishedPlayersFalse() throws InvalidInputException, NoActivePlayerException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(2,1);
		roundController.getGameController().InitializeGame(startMessage);
		roundController.setFirstActivePlayer();
		assertFalse(roundController.FinishedPlayers());
	}

	@Test
	public void StartMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException, WrongMessageException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(2,1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		assertTrue(roundController.getGameController().getMatch().getPlayers()[0].IsActive());
	}

	@Test
	public void AssistantCardMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(2,1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		AssistantCardMessage assistantCardMessage= new AssistantCardMessage(4);
		roundController.setGamePhase(GamePhase.ASSISTANT_CARD);
		roundController.MessageHandler(assistantCardMessage);
		int orderValue =roundController.getGameController().getMatch().getPlayers()[0].GetPlayedOrderValue();
		assertEquals(4,orderValue);
	}

	@Test
	public void MoveStudentMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(2,1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.MOVE_STUDENT);
		MoveStudentMessage moveStudentMessage=new MoveStudentMessage(2,7);
		Color studentColor=roundController.getGameController().getMatch().getPlayers()[0].getPlayersSchool().GetEntranceStudentColor(1);
		roundController.MessageHandler(moveStudentMessage);
		int numberOfStudents=roundController.getGameController().getMatch().getTable().get(6).CountStudents(studentColor);
		assertEquals(1,numberOfStudents);
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
		int position= roundController.getGameController().getMatch().getMotherNaturePosition();
		assertEquals(3,position);
	}

	@Test
	public void ChoseCloudMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController = new RoundController();
		StartMessage startMessage = new StartMessage(2, 1);
		roundController.setGamePhase(GamePhase.GAME_INIT);
		roundController.MessageHandler(startMessage);
		roundController.setGamePhase(GamePhase.CHOOSE_CLOUD);
		CloudChoiceMessage cloudChoiceMessage=new CloudChoiceMessage(1);
		roundController.MessageHandler(cloudChoiceMessage);
		int numberOfStudent=roundController.getGameController().getMatch().getClouds().get(0	).CloudSize();
		assertEquals(0,numberOfStudent);
	}
}
