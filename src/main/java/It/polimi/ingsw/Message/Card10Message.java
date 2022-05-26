package It.polimi.ingsw.Message;

/**
 * The message for Character Card with id 10.
 */
public class Card10Message extends Message {
	private static final long serialVersionUID = 1L;

	private int StudentIndex;

    /**
     * The constructor of the message for Character Card with id 10.
     * @param studentIndex: the index of a student.
     */
	public Card10Message(int studentIndex) {
		super(MessageContent.CARD10);
		StudentIndex = studentIndex - 1;
	}

    /**
     * Return the index of the student.
     * @return the index of the student.
     */
	public int getStudentIndex() {
		return StudentIndex;
	}
}
