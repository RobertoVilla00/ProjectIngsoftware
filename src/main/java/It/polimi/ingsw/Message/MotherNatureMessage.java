package It.polimi.ingsw.Message;

/**
 * The message for Mother Nature.
 */
public class MotherNatureMessage extends Message{
    private static final long serialVersionUID=1L;
    private int Steps;

    /**
     * The constructor of the message for mother nature.
     * @param steps: the number of steps that mother nature do.
     */
    public MotherNatureMessage(int steps){
        super(MessageContent.MOTHERNATURE);
        this.Steps = steps;
    }

    /**
     * Return the steps of mother nature.
     * @return the steps of mother nature.
     */
    public int getSteps() {
        return Steps;
    }
}
