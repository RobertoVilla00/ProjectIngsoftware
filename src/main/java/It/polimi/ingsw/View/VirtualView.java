package It.polimi.ingsw.View;

import It.polimi.ingsw.Message.Message;
import It.polimi.ingsw.Network.Connection;
import It.polimi.ingsw.Observer.Observer;

/**
 * The class virtual view which implements the interface View.
 */
public class VirtualView implements View, Observer {
	private Connection connection;

	/**
	 * The constructor of the Virtual View.
	 * @param connection: the connection.
	 */
	public VirtualView(Connection connection) {
		this.connection = connection;
	}


	/**
	 * The update method of the interface Observer.
	 * @param message: the message to be sent.
	 */
	@Override
	public void update(Message message) {
		connection.AsyncSend(message);
	}

	public void startGame() {

	}

	@Override
	public void showGameInformation() {

	}

	@Override
	public void askInformation() {

	}
}
