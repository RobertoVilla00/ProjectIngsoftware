package It.polimi.ingsw.Message;

import java.util.ArrayList;

/**
 * The message for the end of the game.
 */
public class EndgameMessage extends  Message{
	private static final long serialVersionUID = 1L;

	private ArrayList<String> winners;

	/**
	 * The constructor of the EndGame message.
	 * @param winners: the ArrayList of nickname of the players.
	 */
	public EndgameMessage(ArrayList<String> winners){
		super(MessageContent.ENDGAME);
		this.winners=winners;
	}

	/**
	 * Return the ArrayList of nickname of the players.
	 * @return the ArrayList of nickname of the players.
	 */
	public ArrayList<String> getWinners(){
		return  this.winners;
	}
}
