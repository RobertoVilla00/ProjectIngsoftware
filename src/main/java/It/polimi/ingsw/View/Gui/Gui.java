package It.polimi.ingsw.View.Gui;

import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Message.PlayerIdMessage;
import It.polimi.ingsw.Message.ShowMatchInfoMessage;
import It.polimi.ingsw.Observer.Observable;
import It.polimi.ingsw.Observer.Observer;
import It.polimi.ingsw.View.Gui.GuiController.StartSceneController;
import It.polimi.ingsw.View.View;

public class Gui extends Observable implements View, Observer {

	private ShowMatchInfoMessage msg;
	private int PlayerId;

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
		}
	}

	public void sendMessage(Message message){
		notifyObserver(message);
	}
}
