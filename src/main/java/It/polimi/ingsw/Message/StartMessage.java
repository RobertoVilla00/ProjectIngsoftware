package It.polimi.ingsw.Message;

public class StartMessage extends Message{
    private int NumberOfPlayers;
    private char GameMode;

    public StartMessage(int Number, char Mode){
        super(MessageContent.START);
        this.NumberOfPlayers = Number;
        this.GameMode = Mode;
    }

    public int getNumberOfPlayers() {
        return NumberOfPlayers;
    }

    public char getGameMode() {
        return GameMode;
    }


}
