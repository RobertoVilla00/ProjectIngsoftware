package It.polimi.ingsw.Message;

public class CloudChoiceMessage extends Message{
    private int CloudIndex;

    public CloudChoiceMessage(int cloudIndex){
        super(MessageContent.CLOUDCHOICE);
        this.CloudIndex = cloudIndex;
    }

    public int getCloudIndex() {
        return CloudIndex;
    }
}
