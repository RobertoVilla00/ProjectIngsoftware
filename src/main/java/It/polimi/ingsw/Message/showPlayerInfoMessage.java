package It.polimi.ingsw.Message;
import It.polimi.ingsw.Model.*;

public class showPlayerInfoMessage extends Message{
    private static final long serialVersionUID=1L;
    private Player player;


    public showPlayerInfoMessage(Player player) {
        super(MessageContent.SHOWPLAYERINFO);
        this.player = player;
    }
}
