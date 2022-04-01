package It.polimi.ingsw.Model;


import java.util.ArrayList;
import java.util.Collections;

public class CharacterCardDeck {

	private ArrayList<CharacterCard> Cards;
	private Match match;


	public CharacterCardDeck(Match CurrentMatch) {
		Cards= new ArrayList<CharacterCard>();
		Cards.add(new CharacterCard(1,0,this.match));
		Cards.add(new CharacterCard(2,0,this.match));
		Cards.add(new CharacterCard(3,0,this.match));
		Cards.add(new CharacterCard(4,0,this.match));
		Cards.add(new CharacterCard(5,0,this.match));
		Cards.add(new CharacterCard(6,0,this.match));
		Cards.add(new CharacterCard(7,0,this.match));
		Cards.add(new CharacterCard(8,0,this.match));
		this.match=CurrentMatch;
	}
	public CharacterCard SelectCard() {
		CharacterCard temp=Cards.get(0);
		Cards.remove(0);
		return temp;
	}

	public void ShuffleCharacterCardDeck() {
		Collections.shuffle(Cards);
	}


}