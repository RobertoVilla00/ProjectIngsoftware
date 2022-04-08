package Exceptions;

public class NoEntryTilesException extends Exception{

    public NoEntryTilesException(){
        super("Impossible to play this card, no tiles left");
    }
}
