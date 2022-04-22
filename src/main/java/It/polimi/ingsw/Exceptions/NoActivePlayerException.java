package It.polimi.ingsw.Exceptions;

public class NoActivePlayerException extends Exception{
    public NoActivePlayerException(){super("no active players found in this game");}
}
