package It.polimi.ingsw.Message;

public class NicknameMessage extends Message {
    private String Nickname;

    public NicknameMessage(String Name) {
        super(MessageContent.NICKNAME);
        this.Nickname = Name;
    }

    public String getNickname() {
        return Nickname;
    }
}
