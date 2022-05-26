package It.polimi.ingsw.Message;

/**
 * The message for the id of the player.
 */
public class PlayerIdMessage extends Message {
	private static final long serialVersionUID = 1L;
	private int playerId;

	/**
	 * The constructor of the message for the player id.
	 * @param playerId: the id of the player.
	 */
	public PlayerIdMessage(int playerId) {
		super(MessageContent.PLAYERID);
		this.playerId = playerId;
	}

	/**
	 * Return the id of the player.
	 * @return the id of the player.
	 */
	public int getPlayerId() {
		return this.playerId;
	}
}
