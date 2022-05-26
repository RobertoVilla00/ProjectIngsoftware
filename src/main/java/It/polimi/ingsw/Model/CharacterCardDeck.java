package It.polimi.ingsw.Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the deck of Character Cards.
 */
public class CharacterCardDeck implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<CharacterCard> Cards;
	private Match match;


	/**
	 * Constructor of the Deck of Character Card. It initializes the deck as an ArrayList of Character Card, sets the current match.
	 * Add 8 Character Card to the deck.
	 *
	 * @param CurrentMatch: the current match.
	 */
	public CharacterCardDeck(Match CurrentMatch) {
		Cards = new ArrayList<CharacterCard>();
		this.match = CurrentMatch;
		Cards.add(new Cards1and10(1, 1, this.match));
		Cards.add(new CharacterCard(3, 3, this.match));
		Cards.add(new CharacterCard(4, 1, this.match));
		Cards.add(new Card5(5, 2, this.match));
		Cards.add(new CharacterCard(6, 3, this.match));
		Cards.add(new CharacterCard(9, 2, this.match));
		Cards.add(new Cards1and10(10, 2, this.match));
		Cards.add(new CharacterCard(12, 3, this.match));
	}

	/**
	 * Select the Character Card at the index received by parameter, removes it from the deck and returns it.
	 *
	 * @param index: the index of a Character Card.
	 * @return the Character Card at the index received by parameter.
	 */
	public CharacterCard SelectCard(int index) {
		CharacterCard temp = Cards.get(index);
		Cards.remove(index);
		return temp;
	}

	/**
	 * Shuffle the deck of Character.
	 */
	public void ShuffleCharacterCardDeck() {
		Collections.shuffle(Cards);
	}


}
