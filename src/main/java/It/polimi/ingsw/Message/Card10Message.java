package It.polimi.ingsw.Message;

public class Card10Message extends Message{
    private static final long serialVersionUID=1L;

    private int StudentIndex;

    public Card10Message(int studentIndex) {
        super(MessageContent.CARD10);
        StudentIndex = studentIndex-1;
    }

    public int getStudentIndex() {
        return StudentIndex;
    }
}
