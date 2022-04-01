package It.polimi.ingsw.Model;


public class CharacterCard {

	private int IdCharacterCard;

	private int CardCost;

	private Match match;

	public CharacterCard(int idCharacterCard, int cardCost, Match currentMatch) {
		this.IdCharacterCard = idCharacterCard;
		this.CardCost = cardCost;
	}

	public int getIdCharacterCard() {
		return this.IdCharacterCard;
	}

	public int getCardCost() {
		return this.CardCost;
	}

	public void PlayCard(Player CardPlayer) {  				//identify the player who played the card
	}

	public CharacterCard(){}

	public void SetCardCost(int Cost){
		this.CardCost = Cost;
	}

	public void IncreaseCardCost(){
		CardCost++;
	}

	public Match getMatch(){
		return this.match;
	}
}






