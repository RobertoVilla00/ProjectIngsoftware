package It.polimi.ingsw.Exceptions;

/**
 * The InvalidInputException class, which extends the Exception class.
 */
public class InvalidInputException extends Exception{

    /**
     * Constructor of the class.
     * @param message: it is a string that reports an error message.
     */
    public InvalidInputException(String message){
        super(message);
    }
}
