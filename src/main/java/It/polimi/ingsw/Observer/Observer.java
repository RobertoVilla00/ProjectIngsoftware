package It.polimi.ingsw.Observer;

import It.polimi.ingsw.Message.Message;

/**
 * This is the Observer interface. It supports the method update.
 */
public interface Observer {
	void update(Message message);

}
