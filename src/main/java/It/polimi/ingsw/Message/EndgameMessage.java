package It.polimi.ingsw.Message;

import java.util.ArrayList;
import java.util.List;

public class EndgameMessage extends  Message{
	private static final long serialVersionUID = 1L;

	private ArrayList<String> winners;

	public EndgameMessage(ArrayList<String> winners){
		super(MessageContent.ENDGAME);
		this.winners=winners;
	}

	public ArrayList<String> getWinners(){
		return  this.winners;
	}
}
