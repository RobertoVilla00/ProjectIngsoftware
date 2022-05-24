package It.polimi.ingsw.Message;

/**
 * The message for the nicknames of players.
 */
public class NicknameMessage extends Message {
    private static final long serialVersionUID=1L;
    private String Nickname;

    /**
     * The constructor of the names of players.
     * @param Name: the name of the player.
     */
    public NicknameMessage(String Name) {
        super(MessageContent.NICKNAME);
        this.Nickname = Name;
    }

    /**
     * Return the nickname of the player.
     * @return the nickname of the player.
     */
    public String getNickname() {
        return Nickname;
    }
}
