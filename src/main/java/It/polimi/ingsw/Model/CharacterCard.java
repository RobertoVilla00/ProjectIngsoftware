package It.polimi.ingsw.Model;


public class CharacterCard {

	private int IdCharacterCard;

	private int CardCost;

	private Match match;

	public CharacterCard(int idCharacterCard, int cardCost, Match currentMatch) {
		this.IdCharacterCard = idCharacterCard;
		this.CardCost = cardCost;
		this.match=currentMatch;
	}

	public int getIdCharacterCard() {
		return this.IdCharacterCard;
	}

	public int getCardCost() {
		return this.CardCost;
	}

	public void IncreaseCardCost(){
		CardCost++;
	}

	public Match getMatch(){
		return this.match;
	}

}






