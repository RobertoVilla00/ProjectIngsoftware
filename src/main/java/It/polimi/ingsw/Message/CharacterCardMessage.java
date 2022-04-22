package It.polimi.ingsw.Message;

import It.polimi.ingsw.Model.CharacterCard;

public class CharacterCardMessage extends Message{
    private int CardIndex;

    public CharacterCardMessage(int cardIndex){
        super(MessageContent.CHARACTERCARD);
        this.CardIndex = cardIndex;
    }

    public int getCardIndex() {
        return CardIndex;
    }
}
