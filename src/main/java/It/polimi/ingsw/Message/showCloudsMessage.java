package It.polimi.ingsw.Message;
import It.polimi.ingsw.Model.*;

import java.util.ArrayList;

public class showCloudsMessage extends Message{
    private ArrayList<Cloud> clouds;


    public showCloudsMessage(ArrayList<Cloud> clouds){
        super(MessageContent.SHOWCLOUDS);
        this.clouds = clouds;
    }
}
