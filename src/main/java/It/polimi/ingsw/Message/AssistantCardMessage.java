package It.polimi.ingsw.Message;

/**
 * The message for an Assistant Card.
 */
public class AssistantCardMessage extends Message {
	private static final long serialVersionUID = 1L;
	private int CardIndex;

    /**
     * The constructor of the Assistant Card Message.
     * @param cardIndex: the index of an Assistant Card.
     */
	public AssistantCardMessage(int cardIndex) {
		super(MessageContent.ASSISTANTCARD);
		this.CardIndex = cardIndex - 1;
	}

    /**
     * Return the index of the Assistant Card.
     * @return the index of the Assistant Card.
     */
	public int getCardIndex() {
		return CardIndex;
	}
}
