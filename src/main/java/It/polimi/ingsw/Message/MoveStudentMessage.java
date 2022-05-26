package It.polimi.ingsw.Message;

/**
 * The message for the movement of student.
 */
public class MoveStudentMessage extends Message {
	private static final long serialVersionUID = 1L;

	private int EntrancePosition;
	private int Destination;   //0 -> move to dining groom, 1 to num of islands -> move to an island (destination -1)

    /**
     * Constructor of the message for the movement of the student.
     * @param entrancePosition: the position of the student in the Entrance.
     * @param destination: the index of the destination of the student.
     */
	public MoveStudentMessage(int entrancePosition, int destination) {
		super(MessageContent.MOVESTUDENT);
		this.EntrancePosition = entrancePosition - 1;
		this.Destination = destination - 1;    //negative -> dining room
	}

    /**
     * Return the position of the student in the Entrance.
     * @return the position of the student in the Entrance.
     */
	public int getEntrancePosition() {
		return EntrancePosition;
	}

    /**
     * Return the index of the destination of the student.
     * @return the index of the destination of the student.
     */
	public int getDestination() {
		return Destination;
	}
}