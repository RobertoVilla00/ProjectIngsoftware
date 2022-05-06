package It.polimi.ingsw.Message;

public class MoveStudentMessage extends Message {
    private static final long serialVersionUID=1L;

    private int EntrancePosition;
    private int Destination;   //0 -> move to dining groom, 1 to num of islands -> move to an island (destination -1)

    public MoveStudentMessage(int entrancePosition, int destination){
        super(MessageContent.MOVESTUDENT);
        this.EntrancePosition = entrancePosition -1;
        this.Destination = destination-1;    //negative -> dining room
    }

    public int getEntrancePosition() {
        return EntrancePosition;
    }

    public int getDestination() {
        return Destination;
    }
}