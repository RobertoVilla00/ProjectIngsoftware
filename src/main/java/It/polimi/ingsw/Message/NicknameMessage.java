package It.polimi.ingsw.Message;

/**
 * The message for the nicknames of players.
 */
public class NicknameMessage extends Message {
	private static final long serialVersionUID = 1L;
	private String Nickname;
	private int PlayerIndex;

    /**
     * The constructor of the names of players.
     * @param Name: the name of the player.
     */
	public NicknameMessage(String Name) {
		super(MessageContent.NICKNAME);
		this.Nickname = Name;
	}

	/**
	 * The constructor of the names of players.
	 * @param Name: the name of the player.
	 * @param PlayerIndex: the index of the player.
	 */
	public NicknameMessage(String Name,int PlayerIndex){
		super(MessageContent.NICKNAME);
		this.Nickname=Name;
		this.PlayerIndex=PlayerIndex;
	}

    /**
     * Return the nickname of the player.
     * @return the nickname of the player.
     */
	public String getNickname() {
		return Nickname;
	}


	/**
	 * Return the index of the player.
	 * @return the index of the player.
	 */
	public int getPlayerIndex() {
		return PlayerIndex;
	}
}
