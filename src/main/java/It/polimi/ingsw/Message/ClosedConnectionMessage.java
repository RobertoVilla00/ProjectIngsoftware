package It.polimi.ingsw.Message;

public class ClosedConnectionMessage extends Message{
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor of the message.
	 */

	public ClosedConnectionMessage() {
		super(MessageContent.CLOSEDCONNECTION);
	}
}
