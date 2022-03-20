package Model;

public class CharacterCard {

	private int IdCharacterCard;

	private int CardCost;


	public CharacterCard(int idCharacterCard, int cardCost) {
		IdCharacterCard = idCharacterCard;
		CardCost = cardCost;
	}

	public int getIdCharacterCard() {
		return IdCharacterCard;
	}

	public int getCardCost() {
		return CardCost;
	}

	public void PlayCard() {
	}

	public CharacterCard(){};

	public void SetCardCost(int Cost){
		this.CardCost = Cost;
	}

	public void IncreaseCardCost(){
		CardCost++;
	}
}






