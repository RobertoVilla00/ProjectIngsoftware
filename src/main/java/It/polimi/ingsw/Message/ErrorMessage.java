package It.polimi.ingsw.Message;

/**
 * The message for an error.
 */
public class ErrorMessage extends Message{
	private static final long serialVersionUID=1L;
	private String error;

	/**
	 * The constructor for the error message.
	 * @param error: the string corresponding to an error.
	 */
	public ErrorMessage(String error){
		super(MessageContent.ERROR);
		this.error=error;
	}

	/**
	 * Return the string of the error.
	 * @return the string of the error.
	 */
	public String getError(){
		return error;
	}
}
