package It.polimi.ingsw.Message;

import It.polimi.ingsw.Model.CharacterCard;

public class showCharacterCardsMessage extends Message{
    private static final long serialVersionUID=1L;
    private CharacterCard[] characterCards;

    public showCharacterCardsMessage(CharacterCard[] characterCards){
        super(MessageContent.SHOWCHARACTERCARDS);
        this.characterCards = characterCards;
    }
}
