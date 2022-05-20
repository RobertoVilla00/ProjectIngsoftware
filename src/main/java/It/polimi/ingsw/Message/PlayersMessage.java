package It.polimi.ingsw.Message;

public class PlayersMessage extends Message {
    private static final long serialVersionUID = 1L;

    public PlayersMessage(){
        super(MessageContent.PLAYERS);
    }
}
