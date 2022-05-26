package It.polimi.ingsw.Message;

import java.util.ArrayList;
import java.util.List;

public class EndgameMessage extends  Message{
	private static final long serialVersionUID = 1L;

	private ArrayList<Integer> winners;

	public EndgameMessage(ArrayList<Integer> winners){
		super(MessageContent.ENDGAME);
		this.winners=winners;
	}

	public ArrayList<Integer> getWinners(){
		return  this.winners;
	}
}
