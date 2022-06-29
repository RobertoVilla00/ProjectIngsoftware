package It.polimi.ingsw.View.Gui;

import It.polimi.ingsw.Message.*;
import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.Observer.Observer;
import It.polimi.ingsw.View.Gui.GuiController.AskNameController;
import It.polimi.ingsw.View.Gui.GuiController.BoardController;
import It.polimi.ingsw.View.Gui.GuiController.EndGameController;
import It.polimi.ingsw.View.View;
import javafx.application.Platform;

public class Gui extends Observable implements View, Observer {

	private ShowMatchInfoMessage msg;
	private int PlayerId;
	private boolean gameStarted=false;
	private BoardController boardController;
	private AskNameController askNameController;
	private EndGameController endGameController;
	private boolean wrongName=false;

	public Gui(){
		fxController fxController=new fxController(this);
	}

	public void setBoardController(BoardController boardController){
		this.boardController=boardController;
	}

	public void setNicknameController(AskNameController askNameController){
		this.askNameController=askNameController;
	}

	public int getPlayerId(){
		return this.PlayerId;
	}

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

	@Override
	public void askInformation() {
		Platform.runLater(()->{
			boardController.askInformation(msg,PlayerId);
		});
	}

	public void askPlayers(){
		Platform.runLater(()->{
			SceneController.changeScene("fxml/askNameAndPlayers.fxml");
		});

	}

	public void askNickname(){
		Platform.runLater(()->{
			SceneController.changeScene("fxml/askName.fxml");
			askNameController.askCorrectName();
		});

	}
	public void showError(String error){
		if(gameStarted) {
			Platform.runLater(() -> {
				boardController.showError(error);
			});
		}
	}

	public void setEndGameController(EndGameController endGameController) {
		this.endGameController=endGameController;
	}

	public void setWinner(EndgameMessage endgameMessage){
		Platform.runLater(()->{
			SceneController.changeScene("fxml/endGameScene.fxml");
			endGameController.displayWinner(endgameMessage);
		});
	}

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

	public void sendMessage(Message message){
		notifyObserver(message);
	}
}
