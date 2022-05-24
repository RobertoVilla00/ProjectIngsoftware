package It.polimi.ingsw.Message;

/**
 * The message for the start of the game.
 */
public class StartMessage extends Message{
    private static final long serialVersionUID=1L;
    private int NumberOfPlayers;
    private int GameMode;

    /**
     * The constructor of the message for the start of the game.
     * @param Number: the number of players.
     * @param Mode: the mode of the game.
     */
    public StartMessage(int Number, int Mode){
        super(MessageContent.START);
        this.NumberOfPlayers = Number;
        this.GameMode = Mode;
    }

    /**
     * Return the number of players in the game.
     * @return the number of players in the game.
     */
    public int getNumberOfPlayers() {
        return NumberOfPlayers;
    }

    /**
     * Return the mode of the game.
     * @return the mode of the game.
     */
    public int getGameMode() {
        return GameMode;
    }


}
