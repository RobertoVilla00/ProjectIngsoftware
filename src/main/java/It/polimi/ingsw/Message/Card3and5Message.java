package It.polimi.ingsw.Message;

/**
 * The message for Character Card with id 3 and 5.
 */
public class Card3and5Message extends Message{
    private static final long serialVersionUID=1L;

    private int IslandIndex;

    /**
     * The constructor of the message for Character Card with id 3 and 5.
     * @param islandIndex: the index of an island.
     */
    public Card3and5Message(int islandIndex){
        super(MessageContent.CARD3AND5);

        this.IslandIndex = islandIndex -1;
    }

    /**
     * Return the index of the island.
     * @return the index of the island.
     */
    public int getIslandIndex() {
        return IslandIndex;
    }
}
