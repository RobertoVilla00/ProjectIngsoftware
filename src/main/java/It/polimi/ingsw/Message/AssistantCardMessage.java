package It.polimi.ingsw.Message;

public class AssistantCardMessage extends Message{
    private int CardIndex;

    public AssistantCardMessage(int cardIndex){
        super(MessageContent.ASSISTANTCARD);
        this.CardIndex = cardIndex-1;
    }

    public int getCardIndex() {
        return CardIndex;
    }
}
