package It.polimi.ingsw.Model;


import java.util.ArrayList;
import java.util.Collections;

public class CharacterCardDeck {

	private ArrayList<CharacterCard> Cards;
	private Match match;


	public CharacterCardDeck(Match CurrentMatch) {
		Cards= new ArrayList<CharacterCard>();
		this.match=CurrentMatch;
		Cards.add(new Cards1and10(1,1,this.match));
		Cards.add(new CharacterCard(3,3,this.match));
		Cards.add(new CharacterCard(4,1,this.match));
		Cards.add(new Card5(5,2,this.match));
		Cards.add(new CharacterCard(6,3,this.match));
		Cards.add(new CharacterCard(9,2,this.match));
		Cards.add(new Cards1and10(10,2,this.match));
		Cards.add(new CharacterCard(12,3,this.match));
	}
	public CharacterCard SelectCard(int index) {
		CharacterCard temp=Cards.get(index);
		Cards.remove(index);
		return temp;
	}

	public void ShuffleCharacterCardDeck() {
		Collections.shuffle(Cards);
	}


}
