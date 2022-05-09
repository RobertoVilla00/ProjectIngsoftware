package It.polimi.ingsw.Message;
import It.polimi.ingsw.Model.*;

import java.util.ArrayList;

public class showTableMessage extends Message{
    private static final long serialVersionUID=1L;
    private ArrayList<Island> table;

    public showTableMessage(ArrayList<Island> table){
        super(MessageContent.SHOWTABLE);
        this.table = table;
    }
}
