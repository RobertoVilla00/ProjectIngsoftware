package It.polimi.ingsw.Message;
import It.polimi.ingsw.Model.*;
import jdk.javadoc.internal.doclets.formats.html.Table;

import java.util.ArrayList;

public class showTableMessage extends Message{
    private ArrayList<Island> table;

    public showTableMessage(ArrayList<Island> table){
        super(MessageContent.SHOWTABLE);
        this.table = table;
    }
}
