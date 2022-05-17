package It.polimi.ingsw.Message;

public class PlayerIdMessage extends Message{
	private static final long serialVersionUID=1L;
	private int playerId;

	public PlayerIdMessage(int playerId) {
		super(MessageContent.PLAYERID);
		this.playerId = playerId;
	}

	public int getPlayerId(){
		return this.playerId;
	}
}
