package It.polimi.ingsw.Model;


import java.io.Serializable;

/**
 * This class represents the Assistant Card.
 */
public class AssistantCard implements Serializable {

	private static final long serialVersionUID=1L;

	private int OrderValue;

	private int Movements;

	/**
	 * Constructor of the class Assistant Card.
	 * @param orderValue: the value of the card.
	 * @param movements: the number of steps that mother nature can at most perform.
	 */
	public AssistantCard(int orderValue, int movements) {
		OrderValue = orderValue;
		Movements = movements;
	}

	/**
	 * Return the value of the card.
	 * @return the value of the card.
	 */
	public int getOrderValue() {
		return OrderValue;
	}

	/**
	 * Return the attribute Movements.
	 * @return the attribute Movements of the card.
	 */
	public int getMovement() {
		return Movements;
	}

}
