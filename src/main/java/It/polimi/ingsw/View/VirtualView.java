package It.polimi.ingsw.View;

import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Network.Connection;
import It.polimi.ingsw.Observer.Observer;

public class VirtualView implements View, Observer {
	private Connection connection;

	public VirtualView(Connection connection){
		this.connection=connection;
	}


	@Override
	public void update(Message message) {
		connection.AsyncSend(message);
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
}
