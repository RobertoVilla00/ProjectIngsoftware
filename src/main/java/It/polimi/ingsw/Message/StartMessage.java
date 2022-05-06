package It.polimi.ingsw.Message;

public class StartMessage extends Message{
    private static final long serialVersionUID=1L;
    private int NumberOfPlayers;
    private int GameMode;

    public StartMessage(int Number, int Mode){
        super(MessageContent.START);
        this.NumberOfPlayers = Number;
        this.GameMode = Mode;
    }

    public int getNumberOfPlayers() {
        return NumberOfPlayers;
    }

    public int getGameMode() {
        return GameMode;
    }


}
