package It.polimi.ingsw.Exceptions;

/**
 * The WrongMessageException class, which extends the Exception class.
 */
public class WrongMessageException extends Exception {

	/**
	 * Constructor of the class.
	 */
	public WrongMessageException() {
		super("Wrong Message Content");
	}
}
