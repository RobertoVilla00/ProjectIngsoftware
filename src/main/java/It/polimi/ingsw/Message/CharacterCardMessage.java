package It.polimi.ingsw.Message;

import It.polimi.ingsw.Model.CharacterCard;

/**
 * The message for Character Card.
 */
public class CharacterCardMessage extends Message{
    private static final long serialVersionUID=1L;
    private int CardIndex;

    /**
     * The constructor of the Character Card.
     * @param cardIndex: the index of the Character Card.
     */
    public CharacterCardMessage(int cardIndex){
        super(MessageContent.CHARACTERCARD);
        this.CardIndex = cardIndex-1;
    }

    /**
     * Return the index of the Character Card.
     * @return the index of the Character Card.
     */
    public int getCardIndex() {
        return CardIndex;
    }
}
