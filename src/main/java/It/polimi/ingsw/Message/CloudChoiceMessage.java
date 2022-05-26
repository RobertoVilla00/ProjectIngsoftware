package It.polimi.ingsw.Message;

/**
 * The message for the cloud.
 */
public class CloudChoiceMessage extends Message {
	private static final long serialVersionUID = 1L;

	private int CloudIndex;

    /**
     * The constructor of the message for the cloud.
     * @param cloudIndex: the index of a cloud.
     */
	public CloudChoiceMessage(int cloudIndex) {
		super(MessageContent.CLOUDCHOICE);
		this.CloudIndex = cloudIndex - 1;
	}

    /**
     * Return the index of a cloud.
     * @return the index of a cloud.
     */
	public int getCloudIndex() {
		return CloudIndex;
	}
}
