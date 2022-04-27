package It.polimi.ingsw.Controller;

import It.polimi.ingsw.Exceptions.InvalidInputException;
import It.polimi.ingsw.Exceptions.NoActivePlayerException;
import It.polimi.ingsw.Exceptions.WrongMessageException;
import It.polimi.ingsw.Message.AssistantCardMessage;
import It.polimi.ingsw.Message.StartMessage;
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

	/*@Test
	public void ReceiveMessage() throws NoActivePlayerException, InvalidInputException, WrongMessageException {
		RoundController roundController=new RoundController();
		StartMessage startMessage=new StartMessage(2,1);
		roundController.ReceiveMessage(startMessage);
		roundController.GamePhaseHandler(GamePhase.GAME_INIT);
		boolean active=roundController.getGameController().getMatch().getPlayers()[0].IsActive();
		assertTrue(active);
	}
*/
}
