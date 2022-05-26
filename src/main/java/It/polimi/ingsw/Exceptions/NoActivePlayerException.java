package It.polimi.ingsw.Exceptions;

/**
 * The NoActivePlayerException class, which extends the Exception class.
 */
public class NoActivePlayerException extends Exception {

	/**
	 * Constructor of the class.
	 */
	public NoActivePlayerException() {
		super("no active players found in this game");
	}
}
