package It.polimi.ingsw.Message;

public abstract class Message {
    private MessageContent Content;

    public Message(MessageContent Content){
        this.Content = Content;
    }

    public MessageContent getMessageContent(){
        return this.Content;
    }
}
