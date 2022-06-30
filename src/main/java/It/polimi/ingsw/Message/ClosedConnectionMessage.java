package It.polimi.ingsw.Message;

/**
 * The message for the closure of the connection.
 */
public class ClosedConnectionMessage extends Message{
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor of the message.
	 */
	public ClosedConnectionMessage() {
		super(MessageContent.CLOSEDCONNECTION);
	}
}
