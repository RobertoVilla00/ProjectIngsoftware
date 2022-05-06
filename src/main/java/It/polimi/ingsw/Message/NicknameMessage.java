package It.polimi.ingsw.Message;

public class NicknameMessage extends Message {
    private static final long serialVersionUID=1L;
    private String Nickname;

    public NicknameMessage(String Name) {
        super(MessageContent.NICKNAME);
        this.Nickname = Name;
    }

    public String getNickname() {
        return Nickname;
    }
}
