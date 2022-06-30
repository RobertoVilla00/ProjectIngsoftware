package It.polimi.ingsw.View.Gui;

import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.Observer.Observer;
import It.polimi.ingsw.View.Gui.GuiController.AskNameController;
import It.polimi.ingsw.View.Gui.GuiController.BoardController;
import It.polimi.ingsw.View.Gui.GuiController.EndGameController;
import It.polimi.ingsw.View.View;
import javafx.application.Platform;

/**
 * The main class of the Gui.
 */
public class Gui extends Observable implements View, Observer {

	private ShowMatchInfoMessage msg;
	private int PlayerId;
	private boolean gameStarted=false;
	private BoardController boardController;
	private AskNameController askNameController;
	private EndGameController endGameController;
	private boolean wrongName=false;

	/**
	 * It is the constructor of the class Gui which is used to create the fxController.
	 */
	public Gui(){
		fxController fxController=new fxController(this);
	}

	/**
	 * It used to set the Board controller.
	 * @param boardController: it receives the Board controller.
	 */
	public void setBoardController(BoardController boardController){
		this.boardController=boardController;
	}

	/**
	 * It used to set the nickname controller.
	 * @param askNameController: it receives the ask name controller.
	 */
	public void setNicknameController(AskNameController askNameController){
		this.askNameController=askNameController;
	}

	/**
	 * It returns the player id.
	 * @return the player id.
	 */
	public int getPlayerId(){
		return this.PlayerId;
	}

	/**
	 * It used to show the information about the game.
	 */
	@Override
	public void showGameInformation() {
		if(!gameStarted) {
			Platform.runLater(()->{
				SceneController.changeScene("fxml/boardScene.fxml");
			});
			gameStarted=true;
		}
		Platform.runLater(()->{
			boardController.showGameInformation(msg, PlayerId);
		});
	}

	/**
	 * It used to ask information about the game.
	 */
	@Override
	public void askInformation() {
		Platform.runLater(()->{
			boardController.askInformation(msg,PlayerId);
		});
	}

	/**
	 * It used to create the scene which ask the name of the first player and the number of players.
	 */
	public void askPlayers(){
		Platform.runLater(()->{
			SceneController.changeScene("fxml/askNameAndPlayers.fxml");
		});

	}

	/**
	 * It used to create the scene which ask the name of the players.
	 */
	public void askNickname(){
		Platform.runLater(()->{
			SceneController.changeScene("fxml/askName.fxml");
			askNameController.askCorrectName();
		});

	}

	/**
	 * It used to send a message containing the error generated.
	 * @param error: it receives the error to show.
	 */
	public void showError(String error){
		if(gameStarted) {
			Platform.runLater(() -> {
				boardController.showError(error);
			});
		}
	}

	/**
	 * It used to set the end game controller.
	 * @param endGameController: it receives the end game controller.
	 */
	public void setEndGameController(EndGameController endGameController) {
		this.endGameController=endGameController;
	}

	/**
	 * It used to show the final scene who display the winner.
	 * @param endgameMessage: it receives the endgame message.
	 */
	public void setWinner(EndgameMessage endgameMessage){
		Platform.runLater(()->{
			SceneController.changeScene("fxml/endGameScene.fxml");
			endGameController.displayWinner(endgameMessage);
		});
	}

	/**
	 * The method update which invoke the correct method depending on the content of the message received.
	 * @param message: it receives a message.
	 */
	@Override
	public void update(Message message) {
		switch (message.getMessageContent()) {
			case SHOWMATCHINFO:
				msg=(ShowMatchInfoMessage) message;
				showGameInformation();
				askInformation();
				break;
			case PLAYERID:
				PlayerIdMessage msg = (PlayerIdMessage) message;
				this.PlayerId = msg.getPlayerId();
				break;
			case NICKNAME:
				askNickname();
				break;
			case PLAYERS:
				askPlayers();
				break;
			case ERROR:
				ErrorMessage errorMessage = (ErrorMessage) message;
				String error = errorMessage.getError();
				showError(error);
				break;
			case ENDGAME:
				EndgameMessage endgameMessage=(EndgameMessage) message;
				setWinner(endgameMessage);
				break;
		}
	}

	/**
	 * It used to send message through notify observer.
	 * @param message: it receives a message.
	 */
	public void sendMessage(Message message){
		notifyObserver(message);
	}
}
