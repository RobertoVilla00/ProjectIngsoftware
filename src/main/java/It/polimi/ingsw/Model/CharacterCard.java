package It.polimi.ingsw.Model;


import java.io.Serializable;

/**
 * This class represents the Character Card.
 */
public class CharacterCard implements Serializable {

	private static final long serialVersionUID = 1L;

	private int IdCharacterCard;

	private int CardCost;

	private Match match;

	/**
	 * Constructor of the class Character Card.
	 *
	 * @param idCharacterCard: the id of the Character Card.
	 * @param cardCost:        the cost of the Character Card.
	 * @param currentMatch:    the current match.
	 */
	public CharacterCard(int idCharacterCard, int cardCost, Match currentMatch) {
		this.IdCharacterCard = idCharacterCard;
		this.CardCost = cardCost;
		this.match = currentMatch;
	}

	/**
	 * Return the id of the Character Card.
	 *
	 * @return the id of the Character Card.
	 */
	public int getIdCharacterCard() {
		return this.IdCharacterCard;
	}

	/**
	 * Return the cost of the Character Card.
	 *
	 * @return the cost of the Character Card.
	 */
	public int getCardCost() {
		return this.CardCost;
	}

	/**
	 * Increase the cost of the Character Card by one after the card is played.
	 */
	public void IncreaseCardCost() {
		CardCost++;
	}

	/**
	 * Return the current match.
	 *
	 * @return the current match.
	 */
	public Match getMatch() {
		return this.match;
	}

}






