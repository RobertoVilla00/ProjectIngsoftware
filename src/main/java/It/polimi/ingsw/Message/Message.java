package It.polimi.ingsw.Message;


import java.io.Serializable;

public abstract class Message implements Serializable {
    private static final long serialVersionUID=1L;
    private MessageContent Content;

    public Message(MessageContent Content){
        this.Content = Content;
    }

    public MessageContent getMessageContent(){
        return this.Content;
    }
}
