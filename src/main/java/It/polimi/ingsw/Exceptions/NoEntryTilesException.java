package It.polimi.ingsw.Exceptions;

/**
 * The NoEntryTilesException class, which extends the Exception class.
 */
public class NoEntryTilesException extends Exception {

	/**
	 * Constructor of the class.
	 */
	public NoEntryTilesException() {
		super("Impossible to play this card, no tiles left");
	}
}
