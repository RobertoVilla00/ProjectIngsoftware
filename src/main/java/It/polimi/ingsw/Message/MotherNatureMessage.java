package It.polimi.ingsw.Message;

public class MotherNatureMessage extends Message{
    private int Steps;

    public MotherNatureMessage(int steps){
        super(MessageContent.MOTHERNATURE);
        this.Steps = steps;
    }

    public int getSteps() {
        return Steps;
    }
}
