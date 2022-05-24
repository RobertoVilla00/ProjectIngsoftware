package It.polimi.ingsw.Message;


import java.io.Serializable;

/**
 * The class for the message.
 */
public abstract class Message implements Serializable {
    private static final long serialVersionUID=1L;
    private MessageContent Content;

    /**
     * The constructor of the message.
     * @param Content: the content of the message.
     */
    public Message(MessageContent Content){
        this.Content = Content;
    }

    /**
     * Return the content of the message.
     * @return the content of the error.
     */
    public MessageContent getMessageContent(){
        return this.Content;
    }
}
