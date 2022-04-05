package It.polimi.ingsw.Model;


import java.util.ArrayList;
import java.util.Collections;

public class CharacterCardDeck {

	private ArrayList<CharacterCard> Cards;
	private Match match;


	public CharacterCardDeck(Match CurrentMatch) {
		Cards= new ArrayList<CharacterCard>();
		Cards.add(new Cards1and7and10(1,1,this.match));
		Cards.add(new CharacterCard(3,3,this.match));
		Cards.add(new CharacterCard(4,1,this.match));
		Cards.add(new Card5(5,2,this.match));
		Cards.add(new Cards1and7and10(7,1,this.match));
		Cards.add(new CharacterCard(8,1,this.match));
		Cards.add(new Cards1and7and10(10,2,this.match));
		Cards.add(new CharacterCard(12,3,this.match));
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
