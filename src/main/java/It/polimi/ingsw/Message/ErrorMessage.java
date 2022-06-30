package It.polimi.ingsw.Message;

/**
 * The message for an error.
 */
public class ErrorMessage extends Message {
	private static final long serialVersionUID = 1L;
	private String error;
	private int PlayerId;

	/**
	 * The constructor for the error message.
	 * @param error: the string corresponding to an error.
	 */
	public ErrorMessage(String error) {
		super(MessageContent.ERROR);
		this.error = error;
	}

	/**
	 * The constructor for the error message.
	 * @param error: the string corresponding to an error.
	 * @param PlayerId: the id of the player.
	 */
	public ErrorMessage(String error, int PlayerId) {
		super(MessageContent.ERROR);
		this.error = error;
		this.PlayerId = PlayerId;
	}
	/**
	 * Return the string of the error.
	 * @return the string of the error.
	 */
	public String getError() {
		return error;
	}
}
