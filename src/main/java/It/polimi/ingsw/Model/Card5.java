package It.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the class of the Character Card with Id 5, which extends the Character Card class.
 */
public class Card5 extends CharacterCard implements Serializable {

	private static final long serialVersionUID = 1L;

	private int NoEntryTilesOnCard;

	/**
	 * Constructor of the class Card5. It sets the number of prohibition cards to 4.
	 *
	 * @param idCharacterCard: the id of Character Card.
	 * @param cardCost:        the cost of Character Card.
	 * @param currentMatch:    the current match.
	 */
	public Card5(int idCharacterCard, int cardCost, Match currentMatch) {
		super(idCharacterCard, cardCost, currentMatch);
		NoEntryTilesOnCard = 4;
	}

	/**
	 * Return the number of prohibition cards on Card5.
	 *
	 * @return the number of prohibition cards.
	 */
	public int getNoEntryTilesOnCard() {
		return this.NoEntryTilesOnCard;
	}

	/**
	 * Increase by one the number of prohibition cards.
	 */
	public void AddNoEntryTile() {
		NoEntryTilesOnCard++;
	}

	/**
	 * Decrease by one the number of prohibition cards.
	 */
	public void RemoveNoEntryTile() {
		NoEntryTilesOnCard--;
	}
}
