package It.polimi.ingsw.Message;

public class Card3and5Message extends Message{
    private static final long serialVersionUID=1L;

    private int IslandIndex;

    public Card3and5Message(int islandIndex){
        super(MessageContent.CARD3AND5);

        this.IslandIndex = islandIndex -1;
    }

    public int getIslandIndex() {
        return IslandIndex;
    }
}
