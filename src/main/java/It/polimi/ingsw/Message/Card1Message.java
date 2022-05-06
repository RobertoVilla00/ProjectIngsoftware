package It.polimi.ingsw.Message;

public class Card1Message extends Message{
    private static final long serialVersionUID=1L;
    private int StudentOnCardIndex;
    private int IslandIndex;

    public Card1Message(int studentOnCardIndex, int islandIndex){
        super(MessageContent.CARD1);
        this.StudentOnCardIndex = studentOnCardIndex -1;
        this.IslandIndex = islandIndex -1;
    }

    public int getStudentOnCardIndex() {
        return StudentOnCardIndex;
    }

    public int getIslandIndex() {
        return IslandIndex;
    }
}
