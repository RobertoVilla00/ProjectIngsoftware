package It.polimi.ingsw.Message;

/**
 * The message for players.
 */
public class PlayersMessage extends Message {
	private static final long serialVersionUID = 1L;

    /**
     * The constructor of the message for players.
     */
	public PlayersMessage() {
		super(MessageContent.PLAYERS);
	}
}
