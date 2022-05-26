package It.polimi.ingsw.Message;

/**
 * The message for Character Card with id 1.
 */
public class Card1Message extends Message {
	private static final long serialVersionUID = 1L;
	private int StudentOnCardIndex;
	private int IslandIndex;

    /**
     * The constructor of the message for Character Card with id 1.
     * @param studentOnCardIndex: the index of a student.
     * @param islandIndex: the index of an island.
     */
	public Card1Message(int studentOnCardIndex, int islandIndex) {
		super(MessageContent.CARD1);
		this.StudentOnCardIndex = studentOnCardIndex - 1;
		this.IslandIndex = islandIndex - 1;
	}

    /**
     * Return the index of the student.
     * @return the index of the student.
     */
	public int getStudentOnCardIndex() {
		return StudentOnCardIndex;
	}

    /**
     * Return the index of the island.
     * @return the index of the island.
     */
	public int getIslandIndex() {
		return IslandIndex;
	}
}
