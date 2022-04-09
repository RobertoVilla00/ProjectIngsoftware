package It.polimi.ingsw.Message;

public class Card10Message extends Message{

    private int StudentIndex;

    public Card10Message(int studentIndex) {
        super(MessageContent.CARD10);
        StudentIndex = studentIndex-1;
    }

    public int getStudentIndex() {
        return StudentIndex;
    }
}
