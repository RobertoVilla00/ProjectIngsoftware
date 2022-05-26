package It.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the deck of Assistant Card.
 */
public class AssistantCardDeck implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<AssistantCard> Cards;

	/**
	 * Constructor of the deck of Assistant Card. Add 10 Assistant Card to the deck with their respective values.
	 */
	public AssistantCardDeck() {
		Cards = new ArrayList<AssistantCard>();
		Cards.add(new AssistantCard(1, 1));
		Cards.add(new AssistantCard(2, 1));
		Cards.add(new AssistantCard(3, 2));
		Cards.add(new AssistantCard(4, 2));
		Cards.add(new AssistantCard(5, 3));
		Cards.add(new AssistantCard(6, 3));
		Cards.add(new AssistantCard(7, 4));
		Cards.add(new AssistantCard(8, 4));
		Cards.add(new AssistantCard(9, 5));
		Cards.add(new AssistantCard(10, 5));
	}

	/**
	 * Removes the Assistant card at the index received by parameter from the deck.
	 *
	 * @param index: the index of an Assistant Card.
	 */
	public void RemoveCard(int index) {
		Cards.remove(index);
	}

	/**
	 * Return the Assistant Card at the index received by parameter.
	 *
	 * @param index: the index of an Assistant Card.
	 * @return the Assistant Card at the index received by parameter.
	 */
	public AssistantCard GetCard(int index) {
		return Cards.get(index);
	}

	/**
	 * Return the deck of Assistant Card.
	 *
	 * @return the deck of Assistant Card.
	 */
	public ArrayList<AssistantCard> getCards() {
		return Cards;
	}

	/**
	 * @return the number of Assistant Card in the deck.
	 */
	public int CardCount() {
		return Cards.size();
	}
}
