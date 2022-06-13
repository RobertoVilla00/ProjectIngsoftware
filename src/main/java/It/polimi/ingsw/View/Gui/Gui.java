package It.polimi.ingsw.View.Gui;

import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Message.PlayerIdMessage;
import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.Observer.Observer;
import It.polimi.ingsw.View.View;

public class Gui extends Observable implements View, Observer {

	private ShowMatchInfoMessage msg;
	private int PlayerId;


	public Gui(){
		fxController fxController=new fxController(this);
	}

	@Override
	public void startGame() {

	}

	@Override
	public void showGameInformation() {

	}

	@Override
	public void askInformation() {

	}

	public void askName(){

	}

	public void askPlayers(){
		SceneController.changeScene("fxml/askNameAndPlayers.fxml");
	}

	@Override
	public void update(Message message) {
		switch (message.getMessageContent()) {
			case PLAYERID:
				PlayerIdMessage msg = (PlayerIdMessage) message;
				this.PlayerId = msg.getPlayerId();
				break;
			case NICKNAME:
				askName();
				break;
			case PLAYERS:
				askPlayers();
		}
	}

	public void sendMessage(Message message){
		notifyObserver(message);
	}
}
